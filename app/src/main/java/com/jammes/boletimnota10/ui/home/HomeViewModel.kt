package com.jammes.boletimnota10.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.turma.BuscarTurmaAtualUseCase
import com.jammes.boletimnota10.ui.domain.turma.ExisteTurmaCadastradaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.BuscarDisciplinasDaTurmaUseCase
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import com.jammes.boletimnota10.ui.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val buscarTurmaAtualUseCase: BuscarTurmaAtualUseCase,
    private val buscarDisciplinasDaTurmaUseCase: BuscarDisciplinasDaTurmaUseCase,
    private val existeTurmaCadastradaUseCase: ExisteTurmaCadastradaUseCase
) : ViewModel() {

    private val uiStateTurma: MutableLiveData<TurmaUiState> by lazy {
        MutableLiveData<TurmaUiState>()
    }

    private val uiStateBoletim: MutableLiveData<BoletimUiState> by lazy {
        MutableLiveData<BoletimUiState>(BoletimUiState(emptyList()))
    }

    fun stateTurmaUiState(): LiveData<TurmaUiState> {
        return uiStateTurma
    }

    fun stateBoletimUiStateOnce(): LiveData<BoletimUiState> {
        return uiStateBoletim
    }

    private suspend fun obterTurmaAtual() {
        withContext(Dispatchers.Main) {

            val turma = TurmaUiState(buscarTurmaAtualUseCase())

            uiStateTurma.postValue(turma)

            uiStateBoletim.postValue(BoletimUiState(buscarDisciplinasDaTurmaUseCase(turma.turmaItem.id)))
        }
    }

    fun listarBoletim() {
        viewModelScope.launch {
            if (existeTurmaCadastradaUseCase())
                obterTurmaAtual()
        }
    }

    data class TurmaUiState(val turmaItem: TurmaItem)
    data class BoletimUiState(val boletimItem: List<TurmaDisciplinaItem>)
}