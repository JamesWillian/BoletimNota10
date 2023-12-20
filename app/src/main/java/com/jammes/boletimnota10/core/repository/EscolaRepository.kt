package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.EscolaDomain

interface EscolaRepository {

    suspend fun fetchAll(): List<EscolaDomain>

    suspend fun add(nome: String)

    suspend fun post(escolaId: String, nome: String)

    suspend fun delete(escolaId: String)

}