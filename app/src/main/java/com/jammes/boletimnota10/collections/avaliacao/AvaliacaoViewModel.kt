package com.jammes.boletimnota10.collections.avaliacao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.avaliacao.BuscarTodasAvaliacoesUseCase
import com.jammes.boletimnota10.collections.domain.avaliacao.InserirAvaliacaoUseCase
import com.jammes.boletimnota10.collections.model.AvaliacaoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvaliacaoViewModel @Inject constructor(
    private val buscarTodasAvaliacoesUseCase: BuscarTodasAvaliacoesUseCase,
    private val inserirAvaliacaoUseCase: InserirAvaliacaoUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(avaliacaoItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun atualizarAvaliacoesList(turmaId: String, disciplinaId: String) {
        uiState.postValue(UiState(buscarTodasAvaliacoesUseCase(turmaId, disciplinaId)))
    }

    fun salvarAvaliacao(turmaId: String, disciplinaId: String, descricao: String, nota: Float, data: String, recuperacao: Boolean) {
        viewModelScope.launch {
            inserirAvaliacaoUseCase(turmaId, disciplinaId, descricao, nota, data, recuperacao)
            atualizarAvaliacoesList(turmaId, disciplinaId)
        }
    }

    fun onResume(turmaId: String, disciplinaId: String) {
        viewModelScope.launch {
            atualizarAvaliacoesList(turmaId, disciplinaId)
        }
    }

    data class UiState(val avaliacaoItemList: List<AvaliacaoItem>)
}