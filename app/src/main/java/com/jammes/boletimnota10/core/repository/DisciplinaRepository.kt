package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.DisciplinaDomain

interface DisciplinaRepository {

    suspend fun fetchAll(): List<DisciplinaDomain>

    suspend fun fetchById(disciplinaId: String): DisciplinaDomain

    suspend fun add(descricao: String)

    suspend fun post(disciplinaId: String, descricao: String)

    suspend fun delete(disciplinaId: String)

}