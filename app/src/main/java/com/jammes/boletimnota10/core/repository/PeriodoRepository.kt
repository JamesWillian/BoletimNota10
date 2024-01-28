package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.PeriodoDomain

interface PeriodoRepository {

    suspend fun add(periodo: String, turmaId: String): String

    suspend fun fetch(turmaId: String): List<PeriodoDomain>
}