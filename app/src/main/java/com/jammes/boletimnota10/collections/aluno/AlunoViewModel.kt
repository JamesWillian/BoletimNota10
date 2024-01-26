package com.jammes.boletimnota10.collections.aluno

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.aluno.GetAlunoUseCase
import com.jammes.boletimnota10.collections.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.collections.model.AlunoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlunoViewModel @Inject constructor(
    private val getAlunoUseCase: GetAlunoUseCase,
    private val insertAlunoUseCase: InsertAlunoUseCase
): ViewModel()  {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(alunoItemList = AlunoItem("","")))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun saveAluno(nome: String) {
        viewModelScope.launch {
            insertAlunoUseCase(nome)
            refreshAlunoList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshAlunoList()
        }
    }

    private suspend fun refreshAlunoList() {
        uiState.postValue(UiState(getAlunoUseCase()))
    }

    data class UiState(val alunoItemList: AlunoItem)
}