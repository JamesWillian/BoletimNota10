package com.jammes.boletimnota10.ui.turma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCase
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TurmaViewModel @Inject constructor(
    private val insertTurmaUseCase: InsertTurmaUseCase,
    private val insertTurmaDisciplinaUseCase: InsertTurmaDisciplinaUseCase
) : ViewModel() {

    val disciplinasSelecionadas: MutableList<TurmaDisciplinaItem> = mutableListOf()

    fun saveTurma(nome: String, escola: String, periodo: String, turno: String, ano: Int) {
        viewModelScope.launch {
            val turmaId = insertTurmaUseCase(nome, escola, periodo, turno, ano)

            for (disciplina in disciplinasSelecionadas) {
                insertTurmaDisciplinaUseCase(turmaId, disciplina.id)
            }
        }
    }

}