package com.jammes.boletimnota10.ui.disciplina

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.model.DisciplinaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisciplinaViewModel @Inject constructor(
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

}