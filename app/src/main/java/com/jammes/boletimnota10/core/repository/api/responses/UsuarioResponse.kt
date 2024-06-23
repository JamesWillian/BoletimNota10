package com.jammes.boletimnota10.core.repository.api.responses

data class UsuarioResponse(
    val result: UsuarioResult
)

data class UsuarioResult(
    val alunoId: String,
    val token: String
)
