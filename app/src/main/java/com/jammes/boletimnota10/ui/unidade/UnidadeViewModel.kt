package com.jammes.boletimnota10.ui.unidade

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.unidade.GetAllUnidadesUseCase
import com.jammes.boletimnota10.ui.domain.unidade.InsertUnidadeUseCase
import com.jammes.boletimnota10.ui.model.DisciplinaItem
import com.jammes.boletimnota10.ui.model.UnidadeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnidadeViewModel@Inject constructor(
    private val getAllUnidadesUseCase: GetAllUnidadesUseCase,
    private val insertUnidadeUseCase: InsertUnidadeUseCase
): ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(unidadeItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    private suspend fun refreshUnidadeList() {
        uiState.postValue(UiState(getAllUnidadesUseCase()))
    }

    fun saveUnidade(descricao: String) {
        viewModelScope.launch {
            insertUnidadeUseCase(descricao)
            refreshUnidadeList()
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshUnidadeList()
        }
    }

    data class UiState(val unidadeItemList: List<UnidadeItem>)
}