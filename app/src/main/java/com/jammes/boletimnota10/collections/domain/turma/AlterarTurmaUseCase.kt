package com.jammes.boletimnota10.collections.domain.turma

interface AlterarTurmaUseCase {

    suspend operator fun invoke(
        turmaId: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String,
        alunoId: String
    )
}