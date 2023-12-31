package com.jammes.boletimnota10.ui.domain.turma

interface ExisteTurmaCadastradaUseCase {

    suspend operator fun invoke(): Boolean
}