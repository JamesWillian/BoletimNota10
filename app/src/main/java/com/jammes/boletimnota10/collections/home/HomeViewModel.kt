package com.jammes.boletimnota10.collections.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.aluno.GetAlunoUseCase
import com.jammes.boletimnota10.collections.domain.boletim.BuscarBoletimDoPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.periodo.BuscarPeriodosDaTurmaUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaPorIdUseCase
import com.jammes.boletimnota10.collections.domain.turma.ExisteTurmaCadastradaUseCase
import com.jammes.boletimnota10.collections.domain.usuario.LoginUseCase
import com.jammes.boletimnota10.collections.model.BoletimItem
import com.jammes.boletimnota10.collections.model.PeriodoItem
import com.jammes.boletimnota10.collections.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val existeTurmaCadastradaUseCase: ExisteTurmaCadastradaUseCase,
    private val buscarTurmaPorIdUseCase: BuscarTurmaPorIdUseCase,
    private val buscarBoletimDoPeriodoUseCase: BuscarBoletimDoPeriodoUseCase,
    private val buscarPeriodosDaTurmaUseCase: BuscarPeriodosDaTurmaUseCase,
    private val buscarAlunoUseCase: GetAlunoUseCase,
) : ViewModel() {

    private val uiStateTurma: MutableLiveData<TurmaUiState> by lazy {
        MutableLiveData<TurmaUiState>()
    }

    private val uiStateBoletim: MutableLiveData<BoletimUiState> by lazy {
        MutableLiveData<BoletimUiState>(BoletimUiState(emptyList()))
    }

    private val uiStatePeriodo: MutableLiveData<PeriodoUiState> by lazy {
        MutableLiveData<PeriodoUiState>(PeriodoUiState(emptyList()))
    }

    fun stateTurmaUiState(): LiveData<TurmaUiState> {
        return uiStateTurma
    }

    fun statePeriodoUiState(): LiveData<PeriodoUiState> {
        return uiStatePeriodo
    }

    fun stateBoletimUiStateOnce(): LiveData<BoletimUiState> {
        return uiStateBoletim
    }

    private suspend fun verificarTurma() = existeTurmaCadastradaUseCase()

    private suspend fun obterTurmaAtual() {
        withContext(Dispatchers.Main) {

            val turmaId = buscarAlunoUseCase().turmaId

            if (turmaId.isNotEmpty()) {
                val turma = buscarTurmaPorIdUseCase.invoke(turmaId)

                uiStateTurma.postValue(TurmaUiState(turma))

                obterPeriodos(turmaId)
            }
        }
    }

    private suspend fun obterPeriodos(turmaId: String) {
        val periodos = buscarPeriodosDaTurmaUseCase(turmaId)
        uiStatePeriodo.postValue(PeriodoUiState(periodos))

        if (periodos.isNotEmpty()) {
            val periodoId = periodos[0].id
            obterBoletim(periodoId)
        }
    }

    private suspend fun obterBoletim(periodoId: String?) {
        if (!periodoId.isNullOrEmpty())
            uiStateBoletim.postValue(BoletimUiState(buscarBoletimDoPeriodoUseCase(periodoId)))
    }

    fun listarBoletim() {
        viewModelScope.launch {

            val existeTurma = verificarTurma()

            if (existeTurma) {
                obterTurmaAtual()
            } else {
                uiStateTurma.postValue(TurmaUiState(TurmaItem("", "", "", "", "", "", "")))
            }

        }
    }

    fun listarBoletimDoPeriodo(periodoId: String) {
        viewModelScope.launch {
            obterBoletim(periodoId)
        }
    }

    fun turmaAtual() = uiStateTurma.value?.turmaItem?.id

    data class TurmaUiState(val turmaItem: TurmaItem)
    data class BoletimUiState(val boletimItem: List<BoletimItem>)
    data class PeriodoUiState(val periodoItem: List<PeriodoItem>)
}