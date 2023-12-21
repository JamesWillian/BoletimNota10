package com.jammes.boletimnota10.ui.tipo_atividade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.tipo_atividade.GetAllTiposAtividadesUseCase
import com.jammes.boletimnota10.ui.domain.tipo_atividade.InsertTipoAtividadeUseCase
import com.jammes.boletimnota10.ui.model.DisciplinaItem
import com.jammes.boletimnota10.ui.model.TipoAtividadeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipoAtividadeViewModel@Inject constructor(
    private val getAllTiposAtividadesUseCase: GetAllTiposAtividadesUseCase,
    private val insertTipoAtividadeUseCase: InsertTipoAtividadeUseCase
): ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(tipoAtividadeItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun refreshTipoAtividadeList() {
        uiState.postValue(UiState(getAllTiposAtividadesUseCase()))
    }

    fun saveTipoAtividade(descricao: String) {
        viewModelScope.launch {
            insertTipoAtividadeUseCase(descricao)
            refreshTipoAtividadeList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshTipoAtividadeList()
        }
    }

    data class UiState(val tipoAtividadeItemList: List<TipoAtividadeItem>)
}