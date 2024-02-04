package com.jammes.boletimnota10.collections.domain.boletim

import com.jammes.boletimnota10.collections.model.BoletimItem

interface BuscarBoletimDoModuloUseCase {

    suspend operator fun invoke(moduloId: String): BoletimItem
}