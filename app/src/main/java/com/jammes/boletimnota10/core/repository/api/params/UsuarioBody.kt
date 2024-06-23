package com.jammes.boletimnota10.core.repository.api.params

data class UsuarioBody(
    val username: String,
    val password: String,
    val email: String? = ""
)
