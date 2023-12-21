package com.jammes.boletimnota10.ui.domain.disciplina

interface InsertDisciplinaUseCase {

    suspend operator fun invoke(descricao: String): Boolean
}