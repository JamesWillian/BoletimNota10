package com.jammes.boletimnota10.collections.domain.disciplina

import com.jammes.boletimnota10.collections.model.DisciplinaItem

interface GetAllDisciplinasUseCase {

    suspend operator fun invoke(): List<DisciplinaItem>
}