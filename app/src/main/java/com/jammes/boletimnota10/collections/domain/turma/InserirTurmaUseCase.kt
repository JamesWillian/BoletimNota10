package com.jammes.boletimnota10.collections.domain.turma

interface InserirTurmaUseCase {

    suspend operator fun invoke(
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    ): String
}