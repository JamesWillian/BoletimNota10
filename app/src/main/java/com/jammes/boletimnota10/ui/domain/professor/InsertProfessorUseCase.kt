package com.jammes.boletimnota10.ui.domain.professor

interface InsertProfessorUseCase {

    suspend operator fun invoke(nome: String, disciplinaId: String): Boolean
}