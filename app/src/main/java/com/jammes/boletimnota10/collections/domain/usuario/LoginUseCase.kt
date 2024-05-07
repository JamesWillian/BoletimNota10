package com.jammes.boletimnota10.collections.domain.usuario

interface LoginUseCase {

    suspend operator fun invoke(usuario: String,
                                senha: String): Boolean
}