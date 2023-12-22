package com.jammes.boletimnota10.ui.domain.turma

interface InsertTurmaUseCase {

    suspend operator fun invoke(nome: String, periodo: String): Boolean
}