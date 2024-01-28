package com.jammes.boletimnota10.collections.domain.modulo

interface AlternarModuloUseCase {

    suspend operator fun invoke(periodoId: String, disciplinaId: String): Boolean
}