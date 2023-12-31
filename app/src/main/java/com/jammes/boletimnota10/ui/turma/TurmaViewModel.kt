package com.jammes.boletimnota10.ui.turma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.turma.BuscarTodasTurmasUseCase
import com.jammes.boletimnota10.ui.domain.turma.BuscarTurmaPorIdUseCase
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCase
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import com.jammes.boletimnota10.ui.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurmaViewModel @Inject constructor(
    private val insertTurmaUseCase: InsertTurmaUseCase,
    private val insertTurmaDisciplinaUseCase: InsertTurmaDisciplinaUseCase,
    private val buscarTodasTurmasUseCase: BuscarTodasTurmasUseCase,
    private val buscarTurmaPorIdUseCase: BuscarTurmaPorIdUseCase
) : ViewModel() {

    val disciplinasSelecionadas: MutableList<TurmaDisciplinaItem> = mutableListOf()

    private val uiStateTurmaList: MutableLiveData<UiStateTurmaList> by lazy {
        MutableLiveData<UiStateTurmaList>(UiStateTurmaList(emptyList()))
    }

    private val uiStateTurma: MutableLiveData<UiStateTurmaItem> by lazy {
        MutableLiveData<UiStateTurmaItem>()
    }

    fun stateTurmaListOnce(): LiveData<UiStateTurmaList> {
        return uiStateTurmaList
    }

    fun stateTurmaItemOnce(): LiveData<UiStateTurmaItem> {
        return uiStateTurma
    }

    private suspend fun buscarTurmaList() {
        uiStateTurmaList.postValue(UiStateTurmaList(buscarTodasTurmasUseCase()))
    }

    private suspend fun buscarTurmaPorId(turmaId: String) {
        uiStateTurma.postValue(UiStateTurmaItem(buscarTurmaPorIdUseCase(turmaId)))
    }

    fun buscarTurma(turmaId: String) {
        viewModelScope.launch {
            buscarTurmaPorId(turmaId)
        }
    }

    fun onResumeTurma() {
        viewModelScope.launch {
            buscarTurmaList()
        }
    }

    fun saveTurma(nome: String, escola: String, periodo: String, turno: String, ano: Int) {
        viewModelScope.launch {
            val turmaId = insertTurmaUseCase(nome, escola, periodo, turno, ano)

            for (disciplina in disciplinasSelecionadas) {
                insertTurmaDisciplinaUseCase(turmaId, disciplina.id)
            }
        }
    }

    data class UiStateTurmaList(val turmaItemList: List<TurmaItem>)
    data class UiStateTurmaItem(val turmaItem: TurmaItem)

}