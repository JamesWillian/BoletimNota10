package com.jammes.boletimnota10.ui.professor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.professor.GetAllProfessoresUseCase
import com.jammes.boletimnota10.ui.domain.professor.InsertProfessorUseCase
import com.jammes.boletimnota10.ui.model.ProfessorItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessorViewModel @Inject constructor(
    private val getAllProfessoresUseCase: GetAllProfessoresUseCase,
    private val insertProfessorUseCase: InsertProfessorUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(professorItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun refreshProfessorList() {
        uiState.postValue(UiState(getAllProfessoresUseCase()))
    }

    fun saveProfessor(nome: String, professorId: String) {
        viewModelScope.launch {
            insertProfessorUseCase(nome, professorId)
            refreshProfessorList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshProfessorList()
        }
    }

    data class UiState(val professorItemList: List<ProfessorItem>)
}