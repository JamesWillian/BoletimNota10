package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.ModuloDomain

interface ModuloRepository {

    suspend fun fetchAll(periodoId: String): List<ModuloDomain>

    suspend fun add(periodoId: String, disciplinaId: String)

    suspend fun delete(moduloId: String)

}