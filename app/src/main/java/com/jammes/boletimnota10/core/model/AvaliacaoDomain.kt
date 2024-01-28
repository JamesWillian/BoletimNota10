package com.jammes.boletimnota10.core.model

data class AvaliacaoDomain(
    val id: String,
    val moduloId: String,
    val descricao: String,
    val nota: Float,
    val data: String,
    val recuperacao: Boolean
)
