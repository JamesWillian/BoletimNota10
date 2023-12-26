package com.jammes.boletimnota10.ui.domain.turma_disciplina

interface InsertTurmaDisciplinaUseCase {

    suspend operator fun invoke(turmaId: String, disciplinaId: String): Boolean
}