package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.BoletimDomain

interface BoletimRepository {

    suspend fun buscarBoletimDoPeriodo(periodoId: String): List<BoletimDomain>
}