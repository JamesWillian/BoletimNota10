package com.jammes.boletimnota10.collections.domain.modulo

import com.jammes.boletimnota10.collections.model.ModuloItem

interface BuscarModulosDoPeriodoUseCase {

    suspend operator fun invoke(periodoId: String): List<ModuloItem>
}