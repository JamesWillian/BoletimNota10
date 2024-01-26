package com.jammes.boletimnota10.collections.domain.modulo

interface InserirModuloUseCase {

    suspend operator fun invoke(periodoId: String, disciplinaId: String): Boolean
}