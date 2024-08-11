package com.jammes.boletimnota10.collections.aluno

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.aluno.GetAlunoUseCase
import com.jammes.boletimnota10.collections.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.collections.domain.aluno.UpdateAlunoUseCase
import com.jammes.boletimnota10.collections.model.AlunoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlunoViewModel @Inject constructor(
    private val getAlunoUseCase: GetAlunoUseCase,
    private val insertAlunoUseCase: InsertAlunoUseCase,
    private val updateAlunoUseCase: UpdateAlunoUseCase,
): ViewModel()  {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(alunoItem = AlunoItem("","", "", "")))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun saveAluno(nome: String) {
        viewModelScope.launch {
            val alunoAtual = uiState.value?.alunoItem?.id ?: ""

            updateAlunoUseCase(alunoAtual, nome)
            refreshAluno()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshAluno()
        }
    }

    private suspend fun refreshAluno() {
        uiState.postValue(UiState(getAlunoUseCase()))
    }

    data class UiState(val alunoItem: AlunoItem)
}