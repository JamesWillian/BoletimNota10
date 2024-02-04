package com.jammes.boletimnota10.collections.avaliacao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.avaliacao.BuscarTodasAvaliacoesUseCase
import com.jammes.boletimnota10.collections.domain.avaliacao.InserirAvaliacaoUseCase
import com.jammes.boletimnota10.collections.domain.boletim.BuscarBoletimDoModuloUseCase
import com.jammes.boletimnota10.collections.model.AvaliacaoItem
import com.jammes.boletimnota10.collections.model.BoletimItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvaliacaoViewModel @Inject constructor(
    private val buscarTodasAvaliacoesUseCase: BuscarTodasAvaliacoesUseCase,
    private val inserirAvaliacaoUseCase: InserirAvaliacaoUseCase,
    private val buscarBoletimDoModuloUseCase: BuscarBoletimDoModuloUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(
            UiState(
                boletimItem = null,
                avaliacaoItemList = emptyList()
            )
        )
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun atualizarAvaliacoesList(moduloId: String) {
        uiState.postValue(UiState(
            boletimItem = buscarBoletimDoModuloUseCase(moduloId),
            avaliacaoItemList = buscarTodasAvaliacoesUseCase(moduloId))
        )
    }

    fun salvarAvaliacao(
        moduloId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ) {
        viewModelScope.launch {
            inserirAvaliacaoUseCase(moduloId, descricao, nota, data, recuperacao)
            atualizarAvaliacoesList(moduloId)
        }
    }

    fun onResume(moduloId: String) {
        viewModelScope.launch {
            atualizarAvaliacoesList(moduloId)
        }
    }

    data class UiState(
        val boletimItem: BoletimItem?,
        val avaliacaoItemList: List<AvaliacaoItem>
    )
}