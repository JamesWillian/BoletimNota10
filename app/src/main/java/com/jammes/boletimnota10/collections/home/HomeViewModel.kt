package com.jammes.boletimnota10.collections.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaAtualUseCase
import com.jammes.boletimnota10.collections.domain.turma.ExisteTurmaCadastradaUseCase
import com.jammes.boletimnota10.collections.domain.modulo.BuscarModulosDoPeriodoUseCase
import com.jammes.boletimnota10.collections.model.ModuloItem
import com.jammes.boletimnota10.collections.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val buscarTurmaAtualUseCase: BuscarTurmaAtualUseCase,
    private val buscarModulosDoPeriodoUseCase: BuscarModulosDoPeriodoUseCase,
    private val existeTurmaCadastradaUseCase: ExisteTurmaCadastradaUseCase
) : ViewModel() {

    private val uiStateTurma: MutableLiveData<TurmaUiState> by lazy {
        MutableLiveData<TurmaUiState>()
    }

    private val uiStateBoletim: MutableLiveData<BoletimUiState> by lazy {
        MutableLiveData<BoletimUiState>(BoletimUiState(emptyList()))
    }

    private val existeTurmaAtiva: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }

    fun stateTurmaUiState(): LiveData<TurmaUiState> {
        return uiStateTurma
    }

    private suspend fun verificarTurma() {
        existeTurmaAtiva.postValue(existeTurmaCadastradaUseCase())
    }

    fun existeTurma(): LiveData<Boolean> {
        return existeTurmaAtiva
    }

    fun stateBoletimUiStateOnce(): LiveData<BoletimUiState> {
        return uiStateBoletim
    }

    private suspend fun obterTurmaAtual() {
        withContext(Dispatchers.Main) {

            val turma = TurmaUiState(buscarTurmaAtualUseCase())

            uiStateTurma.postValue(turma)

            uiStateBoletim.postValue(BoletimUiState(buscarModulosDoPeriodoUseCase(turma.turmaItem.id)))
        }
    }

    fun listarBoletim() {
        viewModelScope.launch {
            verificarTurma()
            if (existeTurmaAtiva.value == true)
                obterTurmaAtual()
        }
    }

    fun turmaAtual() = uiStateTurma.value?.turmaItem?.id

    data class TurmaUiState(val turmaItem: TurmaItem)
    data class BoletimUiState(val boletimItem: List<ModuloItem>)
}