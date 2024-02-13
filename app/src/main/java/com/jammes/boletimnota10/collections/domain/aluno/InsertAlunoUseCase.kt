package com.jammes.boletimnota10.collections.domain.aluno

interface InsertAlunoUseCase {

    suspend operator fun invoke(nome: String): String
}