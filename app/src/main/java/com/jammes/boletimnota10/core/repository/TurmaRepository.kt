package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.TurmaDomain

interface TurmaRepository {

    suspend fun fetchAll(): List<TurmaDomain>

    suspend fun add(nome: String, periodo: String)

    suspend fun post(turmaId: String, nome: String, periodo: String)

    suspend fun delete(turmaId: String)

}