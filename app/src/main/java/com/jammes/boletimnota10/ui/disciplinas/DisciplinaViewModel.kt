package com.jammes.boletimnota10.ui.disciplinas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.model.DisciplinaItem
import kotlinx.coroutines.launch

class DisciplinaViewModel(
    private val getAllDisciplinasUseCase: GetAllDisciplinasUseCase,
    private val insertDisciplinaUseCase: InsertDisciplinaUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(disciplinaItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun refreshDisciplinaList() {
        uiState.postValue(UiState(getAllDisciplinasUseCase()))
    }

    fun saveDisciplina(descricao: String) {
        viewModelScope.launch {
            insertDisciplinaUseCase(descricao)
            refreshDisciplinaList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshDisciplinaList()
        }
    }

    data class UiState(val disciplinaItemList: List<DisciplinaItem>)

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val getAllDisciplinasUseCase: GetAllDisciplinasUseCase,
        private val insertDisciplinaUseCase: InsertDisciplinaUseCase
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DisciplinaViewModel(getAllDisciplinasUseCase, insertDisciplinaUseCase) as T
        }
    }
}