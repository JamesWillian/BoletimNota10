package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.UnidadeDomain

interface UnidadeRepository {

    suspend fun fetchAll(): List<UnidadeDomain>

    suspend fun add(descricao: String)

    suspend fun post(unidadeId: String, descricao: String)

    suspend fun delete(unidadeId: String)

}