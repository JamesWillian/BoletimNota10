package com.jammes.boletimnota10.core.repository.api.params

import java.util.Date

data class AvaliacaoBody (
    val descricao: String,
    val nota: Double,
    val data: Date,
    val recuperacao: Boolean,
    val moduloId: String
)
