package com.jammes.boletimnota10.collections.turma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.collections.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.collections.domain.modulo.AlternarModuloUseCase
import com.jammes.boletimnota10.collections.domain.modulo.BuscarModulosDoPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.periodo.BuscarPeriodosDaTurmaUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaPorIdUseCase
import com.jammes.boletimnota10.collections.domain.turma.InserirTurmaUseCase
import com.jammes.boletimnota10.collections.domain.periodo.InserirPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.turma.AlterarTurmaUseCase
import com.jammes.boletimnota10.collections.model.ModuloItem
import com.jammes.boletimnota10.collections.model.PeriodoItem
import com.jammes.boletimnota10.collections.model.TurmaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurmaViewModel @Inject constructor(
    private val inserirTurmaUseCase: InserirTurmaUseCase,
    private val alterarTurmaUseCase: AlterarTurmaUseCase,
    private val buscarTurmaPorIdUseCase: BuscarTurmaPorIdUseCase,
    private val inserirPeriodoUseCase: InserirPeriodoUseCase,
    private val buscarPeriodosDaTurmaUseCase: BuscarPeriodosDaTurmaUseCase,
    private val buscarModulosDoPeriodoUseCase: BuscarModulosDoPeriodoUseCase,
    private val alternarModuloUseCase: AlternarModuloUseCase
) : ViewModel() {

    private val uiStateTurma: MutableLiveData<UiStateTurma> by lazy {
        MutableLiveData<UiStateTurma>()
    }

    private val uiStatePeriodo: MutableLiveData<UiStatePeriodo> by lazy {
        MutableLiveData<UiStatePeriodo>(UiStatePeriodo(emptyList()))
    }

    private val uiStateModulo: MutableLiveData<UiStateModulo> by lazy {
        MutableLiveData<UiStateModulo>(UiStateModulo(emptyList()))
    }

    fun stateTurmaOnce(): LiveData<UiStateTurma> {
        return uiStateTurma
    }

    fun statePeriodoOnce(): LiveData<UiStatePeriodo> {
        return uiStatePeriodo
    }

    fun stateModuloOnce(): LiveData<UiStateModulo> {
        return uiStateModulo
    }

    private suspend fun buscarTurmaPorId(turmaId: String) {
        uiStateTurma.postValue(UiStateTurma(buscarTurmaPorIdUseCase(turmaId)))
    }

    private suspend fun buscarPeriodosDaTurma(turmaId: String) {
        uiStatePeriodo.postValue(UiStatePeriodo(buscarPeriodosDaTurmaUseCase(turmaId)))
    }

    private suspend fun buscarModulos(periodoId: String) {
        uiStateModulo.postValue(UiStateModulo(buscarModulosDoPeriodoUseCase(periodoId)))
    }

    fun buscarTurma(turmaId: String) {
        viewModelScope.launch {
            buscarTurmaPorId(turmaId)
            buscarPeriodosDaTurma(turmaId)
        }
    }

    fun onResume() {
        val turmaId = turmaAtual()

        if (turmaId != null) {
            viewModelScope.launch {
                buscarPeriodosDaTurma(turmaId)
            }
        }
    }

    fun onResumeModulos(periodoId: String) {
        viewModelScope.launch {
            buscarModulos(periodoId)
        }
    }

    fun salvarTurma(nome: String, escola: String, turno: String, ano: String, dataInicio: String, dataFinal: String) {
        viewModelScope.launch {
            val turmaId = inserirTurmaUseCase(nome, escola, turno, ano, dataInicio, dataFinal)

            uiStateTurma.postValue(
                UiStateTurma(
                    TurmaItem(
                        id = turmaId,
                        nome = nome,
                        escola = escola,
                        turno = turno,
                        ano = ano,
                        dataInicio = dataInicio,
                        dataFinal = dataFinal
                    )
                )
            )
        }
    }

    fun alterarTurma(turmaId: String, nome: String, escola: String, turno: String, ano: String, dataInicio: String, dataFinal: String) {
        viewModelScope.launch {
            alterarTurmaUseCase(
                turmaId,
                nome,
                escola,
                turno,
                ano,
                dataInicio,
                dataFinal
            )
        }
    }

    fun salvarPeriodo(periodo: String) {
        viewModelScope.launch {
            val turmaId = turmaAtual()
            val periodoId = inserirPeriodoUseCase(periodo, turmaId!!)
            buscarPeriodosDaTurma(turmaId)
        }
    }

    fun turmaAtual() = uiStateTurma.value?.turmaItem?.id
    fun alternarModulo(moduloItem: ModuloItem) {
        viewModelScope.launch {
            alternarModuloUseCase(moduloItem.periodoId, moduloItem.disciplinaId)
            buscarModulos(moduloItem.periodoId)
        }
    }

    data class UiStateTurma(val turmaItem: TurmaItem)
    data class UiStatePeriodo(val periodoItem: List<PeriodoItem>)
    data class UiStateModulo(val moduloItem: List<ModuloItem>)

}