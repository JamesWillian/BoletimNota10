package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.TurmaDomain

interface TurmaRepository {

    suspend fun existeTurmaCadastrada(): Boolean

    suspend fun buscarTurmaAtiva(): TurmaDomain?

    suspend fun buscarTurmaPorId(turmaId: String): TurmaDomain

    suspend fun buscarTodasTurmas(): List<TurmaDomain>

    suspend fun add(
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    ): String

    suspend fun post(
        turmaId: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    )

    suspend fun delete(turmaId: String)

}