package com.jammes.boletimnota10.ui.domain.turma

interface InsertTurmaUseCase {

    suspend operator fun invoke(
        nome: String,
        escola: String,
        periodo: String,
        turno: String,
        ano: Int
    ): String
}