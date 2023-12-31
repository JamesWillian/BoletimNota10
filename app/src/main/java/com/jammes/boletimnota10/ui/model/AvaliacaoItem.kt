package com.jammes.boletimnota10.ui.model

data class AvaliacaoItem(
    val id: String,
    val turmaId: String,
    val disciplinaId: String,
    val descricao: String,
    val nota: String,
    val data: String,
    val recuperacao: Boolean
)
