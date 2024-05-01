package com.jammes.boletimnota10.collections.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.boletim.BuscarBoletimDoPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.periodo.BuscarPeriodosDaTurmaUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaAtualUseCase
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
    private val buscarTurmaAtualUseCase: BuscarTurmaAtualUseCase,
    private val existeTurmaCadastradaUseCase: ExisteTurmaCadastradaUseCase,
    private val buscarBoletimDoPeriodoUseCase: BuscarBoletimDoPeriodoUseCase,
    private val buscarPeriodosDaTurmaUseCase: BuscarPeriodosDaTurmaUseCase,
    private val loginDoUsuarioUseCase: LoginUseCase
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

    private val existeTurmaAtiva: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
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

    private suspend fun verificarTurma() {
        existeTurmaAtiva.postValue(existeTurmaCadastradaUseCase())
    }

    fun existeTurma(): LiveData<Boolean> {
        return existeTurmaAtiva
    }

    private suspend fun obterTurmaAtual() {
        withContext(Dispatchers.Main) {

            val turma = buscarTurmaAtualUseCase()?.let { TurmaUiState(it) }

            if (turma != null) {
                uiStateTurma.postValue(turma)

                obterPeriodos(turma.turmaItem.id)
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

            verificarTurma()

            if (existeTurmaAtiva.value == true) {
                obterTurmaAtual()
            }
        }
    }

    fun listarBoletimDoPeriodo(periodoId: String) {
        viewModelScope.launch {
            obterBoletim(periodoId)
        }
    }

    fun turmaAtual() = uiStateTurma.value?.turmaItem?.id

    fun login() {
        viewModelScope.launch {
            val user = loginDoUsuarioUseCase("james", "ABC123")
        }
    }

    data class TurmaUiState(val turmaItem: TurmaItem)
    data class BoletimUiState(val boletimItem: List<BoletimItem>)
    data class PeriodoUiState(val periodoItem: List<PeriodoItem>)
}