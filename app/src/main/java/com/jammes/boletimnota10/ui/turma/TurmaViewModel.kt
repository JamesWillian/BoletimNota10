package com.jammes.boletimnota10.ui.turma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.turma.GetAllTurmasUseCase
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.GetAllTurmaDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCase
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import com.jammes.boletimnota10.ui.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurmaViewModel @Inject constructor(
    private val getAllTurmasUseCase: GetAllTurmasUseCase,
    private val insertTurmaUseCase: InsertTurmaUseCase,
    private val getAllTurmaDisciplinasUseCase: GetAllTurmaDisciplinasUseCase,
    private val insertTurmaDisciplinaUseCase: InsertTurmaDisciplinaUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(turmaItemList = emptyList()))
    }

    private val uiStateDisciplinas: MutableLiveData<DisciplinasUiState> by lazy {
        MutableLiveData<DisciplinasUiState>(DisciplinasUiState(turmaDisciplinaItemList = emptyList()))
    }

    val disciplinasSelecionadas: MutableList<TurmaDisciplinaItem> = mutableListOf()

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun stateDisciplinasOnceAndStream(): LiveData<DisciplinasUiState> {
        return uiStateDisciplinas
    }

    private suspend fun refreshTurmaList() {
        uiState.postValue(UiState(getAllTurmasUseCase()))
    }

    private suspend fun getAllTurmaDisciplinaList(turmaId: String) {
        uiStateDisciplinas.postValue(DisciplinasUiState(getAllTurmaDisciplinasUseCase(turmaId)))
    }

    fun saveTurma(nome: String, escola: String, periodo: String, turno: String, ano: Int) {
        viewModelScope.launch {
            val turmaId = insertTurmaUseCase(nome, escola, periodo, turno, ano)

            for (disciplina in disciplinasSelecionadas) {
                insertTurmaDisciplinaUseCase(turmaId, disciplina.id)
            }
        }
    }

    fun onResume() {
        viewModelScope.launch {
            refreshTurmaList()
        }
    }

    fun fectchDisciplinasDaTurma(turmaId: String) {
        viewModelScope.launch {
            getAllTurmaDisciplinaList(turmaId)
        }
    }

    data class UiState(val turmaItemList: List<TurmaItem>)
    data class DisciplinasUiState(val turmaDisciplinaItemList: List<TurmaDisciplinaItem>)

}