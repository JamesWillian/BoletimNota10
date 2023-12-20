package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.AnoSerieDomain
import com.jammes.boletimnota10.core.model.EscolaDomain

interface AnoSerieRepository {

    suspend fun fetchAll(): List<AnoSerieDomain>

    suspend fun add(descricao: String)

    suspend fun post(anoSerieId: String, descricao: String)

    suspend fun delete(anoSerieId: String)

}