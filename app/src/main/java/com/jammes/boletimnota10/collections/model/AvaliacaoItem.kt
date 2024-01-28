package com.jammes.boletimnota10.collections.model

data class AvaliacaoItem(
    val id: String,
    val moduloId: String,
    val descricao: String,
    val nota: String,
    val data: String,
    val recuperacao: Boolean
)
