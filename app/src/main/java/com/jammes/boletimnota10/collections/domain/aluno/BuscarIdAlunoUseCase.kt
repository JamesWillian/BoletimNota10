package com.jammes.boletimnota10.collections.domain.aluno

interface BuscarIdAlunoUseCase {

    suspend operator fun invoke(): String

}