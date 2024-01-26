package com.jammes.boletimnota10.collections.turma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.turma.BuscarTodasTurmasUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaPorIdUseCase
import com.jammes.boletimnota10.collections.domain.turma.InserirTurmaUseCase
import com.jammes.boletimnota10.collections.domain.modulo.InserirModuloUseCase
import com.jammes.boletimnota10.collections.model.ModuloItem
import com.jammes.boletimnota10.collections.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurmaViewModel @Inject constructor(
    private val inserirTurmaUseCase: InserirTurmaUseCase,
    private val inserirModuloUseCase: InserirModuloUseCase,
    private val buscarTodasTurmasUseCase: BuscarTodasTurmasUseCase,
    private val buscarTurmaPorIdUseCase: BuscarTurmaPorIdUseCase
) : ViewModel() {

//    val disciplinasSelecionadas: MutableList<ModuloItem> = mutableListOf()

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

    fun saveTurma(nome: String, escola: String, turno: String, ano: String, dataInicio: String, dataFinal: String) {
        viewModelScope.launch {
            val turmaId = inserirTurmaUseCase(nome, escola, turno, ano, dataInicio, dataFinal)

//            for (disciplina in disciplinasSelecionadas) {
//                inserirModuloUseCase(turmaId, disciplina.id)
//            }
        }
    }

    data class UiStateTurmaList(val turmaItemList: List<TurmaItem>)
    data class UiStateTurmaItem(val turmaItem: TurmaItem)

}