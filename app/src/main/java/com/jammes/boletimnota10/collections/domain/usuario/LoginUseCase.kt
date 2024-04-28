package com.jammes.boletimnota10.collections.domain.usuario

import com.jammes.boletimnota10.core.model.UsuarioDomain

interface LoginUseCase {

    suspend operator fun invoke(usuario: String,
                                senha: String): UsuarioDomain
}