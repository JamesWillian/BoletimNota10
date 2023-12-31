package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.TurmaDomain

interface TurmaRepository {

    suspend fun buscarTurmaAtiva(): TurmaDomain

    suspend fun existeTurmaCadastrada(): Boolean

    suspend fun add(nome: String, escola: String, periodo: String, turno: String, ano: Int): String

    suspend fun post(turmaId: String, nome: String, escola: String, periodo: String, turno: String, ano: Int)

    suspend fun delete(turmaId: String)

}