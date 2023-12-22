package com.jammes.boletimnota10.ui.domain.aluno

interface InsertAlunoUseCase {

    suspend operator fun invoke(nome: String): Boolean
}