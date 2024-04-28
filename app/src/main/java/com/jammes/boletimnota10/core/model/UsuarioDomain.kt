package com.jammes.boletimnota10.core.model

data class UsuarioDomain(
    val username: String,
    val email: String,
    val emailVerified: Boolean,
    val sessionToken: String,
)
