package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.TipoAtividadeDomain

interface TipoAtividadeRepository {

    suspend fun fetchAll(): List<TipoAtividadeDomain>

    suspend fun add(descricao: String)

    suspend fun post(tipoAtividadeId: String, descricao: String)

    suspend fun delete(tipoAtividadeId: String)

}