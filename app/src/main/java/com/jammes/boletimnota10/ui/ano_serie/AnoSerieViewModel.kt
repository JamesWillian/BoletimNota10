package com.jammes.boletimnota10.ui.ano_serie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.ano_serie.GetAllAnoSeriesUseCase
import com.jammes.boletimnota10.ui.domain.ano_serie.InsertAnoSerieUseCase
import com.jammes.boletimnota10.ui.model.AnoSerieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnoSerieViewModel @Inject constructor(
    private val getAllAnoSeriesUseCase: GetAllAnoSeriesUseCase,
    private val insertAnoSerieUseCase: InsertAnoSerieUseCase
): ViewModel()  {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(anoSerieItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun saveAnoSerie(descricao: String) {
        viewModelScope.launch {
            insertAnoSerieUseCase(descricao)
            refreshAnoSerieList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshAnoSerieList()
        }
    }

    private suspend fun refreshAnoSerieList() {
        uiState.postValue(UiState(getAllAnoSeriesUseCase()))
    }

    data class UiState(val anoSerieItemList: List<AnoSerieItem>)
}