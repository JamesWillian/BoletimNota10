package com.jammes.boletimnota10.collections.domain.avaliacao

interface InserirAvaliacaoUseCase {

    suspend operator fun invoke(
        moduloId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ): Boolean
}