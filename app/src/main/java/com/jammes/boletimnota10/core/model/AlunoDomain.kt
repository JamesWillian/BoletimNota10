package com.jammes.boletimnota10.core.model

data class AlunoDomain(
    val id: String,
    val nome: String,
    val matricula: String,
    val turmaId: String,
    val usuarioId: String
)
