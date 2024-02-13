package com.jammes.boletimnota10.collections.domain.aluno

interface UpdateAlunoUseCase {

    suspend operator fun invoke(alunoId: String, nome: String): Boolean
}