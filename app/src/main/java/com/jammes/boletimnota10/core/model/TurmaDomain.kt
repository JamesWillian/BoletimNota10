package com.jammes.boletimnota10.core.model

data class TurmaDomain(
    val id: String,
    val nome: String,
    val escola: String,
    val turno: String,
    val ano: String,
    val dataInicio: String,
    val dataFinal: String,
    val concluido: Boolean = false
)
