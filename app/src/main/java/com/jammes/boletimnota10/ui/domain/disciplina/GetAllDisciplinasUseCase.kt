package com.jammes.boletimnota10.ui.domain.disciplina

import com.jammes.boletimnota10.ui.model.DisciplinaItem

interface GetAllDisciplinasUseCase {

    suspend operator fun invoke(): List<DisciplinaItem>
}