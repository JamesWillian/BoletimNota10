package com.jammes.boletimnota10.collections.model

data class TurmaItem(
    val id: String,
    val nome: String,
    val escola: String,
    val turno: String,
    val ano: String,
    val dataInicio: String,
    val dataFinal: String,
    val concluido: Boolean = false
)
