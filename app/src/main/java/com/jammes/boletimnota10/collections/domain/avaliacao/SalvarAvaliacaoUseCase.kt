package com.jammes.boletimnota10.collections.domain.avaliacao

interface SalvarAvaliacaoUseCase {

    suspend operator fun invoke(
        avaliacaoId: String?,
        moduloId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ): Boolean
}