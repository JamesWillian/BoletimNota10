package com.jammes.boletimnota10.ui.domain.turma_disciplina

import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem

interface GetAllTurmaDisciplinasUseCase {

    suspend operator fun invoke(turmaId: String): List<TurmaDisciplinaItem>
}