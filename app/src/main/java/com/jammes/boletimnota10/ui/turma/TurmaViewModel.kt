package com.jammes.boletimnota10.ui.turma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.turma.GetAllTurmasUseCase
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCase
import com.jammes.boletimnota10.ui.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurmaViewModel @Inject constructor(
    private val getAllTurmasUseCase: GetAllTurmasUseCase,
    private val insertTurmaUseCase: InsertTurmaUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(turmaItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun refreshTurmaList() {
        uiState.postValue(UiState(getAllTurmasUseCase()))
    }

    fun saveTurma(nome: String, escola: String, periodo: String, turno: String, ano: Int) {
        viewModelScope.launch {
            insertTurmaUseCase(nome, escola, periodo, turno, ano)
            refreshTurmaList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshTurmaList()
        }
    }

    data class UiState(val turmaItemList: List<TurmaItem>)

}