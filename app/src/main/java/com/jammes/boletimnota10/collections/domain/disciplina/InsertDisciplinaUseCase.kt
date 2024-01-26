package com.jammes.boletimnota10.collections.domain.disciplina

interface InsertDisciplinaUseCase {

    suspend operator fun invoke(descricao: String): Boolean
}