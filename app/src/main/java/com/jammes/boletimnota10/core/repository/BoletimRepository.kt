package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.database.entity.Boletim
import com.jammes.boletimnota10.core.model.BoletimDomain

interface BoletimRepository {

    suspend fun buscarBoletimDoPeriodo(periodoId: String): List<BoletimDomain>

    suspend fun buscarBoletimDoModulo(moduloId: String): BoletimDomain?
}