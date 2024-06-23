package com.jammes.boletimnota10.collections.domain.aluno

interface CriarAlunoUseCase {

    suspend operator fun invoke(usuario: String,
                                senha: String,
                                email: String): Boolean
}