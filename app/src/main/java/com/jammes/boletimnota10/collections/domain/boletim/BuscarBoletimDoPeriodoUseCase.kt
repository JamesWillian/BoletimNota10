package com.jammes.boletimnota10.collections.domain.boletim

import com.jammes.boletimnota10.collections.model.BoletimItem

interface BuscarBoletimDoPeriodoUseCase {

    suspend operator fun invoke(periodoId: String): List<BoletimItem>
}