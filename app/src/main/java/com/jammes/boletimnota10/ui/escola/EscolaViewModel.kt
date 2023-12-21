package com.jammes.boletimnota10.ui.escola

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.escola.GetAllEscolasUseCase
import com.jammes.boletimnota10.ui.domain.escola.InsertEscolaUseCase
import com.jammes.boletimnota10.ui.model.EscolaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EscolaViewModel @Inject constructor(
    private val getAllEscolasUseCase: GetAllEscolasUseCase,
    private val insertEscolaUseCase: InsertEscolaUseCase
): ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(escolaItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun refreshEscolaList() {
        uiState.postValue(UiState(getAllEscolasUseCase()))
    }

    fun saveEscola(nome: String) {
        viewModelScope.launch {
            insertEscolaUseCase(nome)
            refreshEscolaList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshEscolaList()
        }
    }

    data class UiState(val escolaItemList: List<EscolaItem>)

}