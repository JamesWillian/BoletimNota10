package com.jammes.boletimnota10.collections.domain.turma

interface ExisteTurmaCadastradaUseCase {

    suspend operator fun invoke(): Boolean
}