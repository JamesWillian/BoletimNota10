package com.jammes.boletimnota10.core.repository.api.responses

import java.util.Date

data class AvaliacaoResponse (
    val id: String,
    val descricao: String,
    val nota: Double,
    val data: Date,
    val recuperacao: Boolean,
    val moduloId: String
)
