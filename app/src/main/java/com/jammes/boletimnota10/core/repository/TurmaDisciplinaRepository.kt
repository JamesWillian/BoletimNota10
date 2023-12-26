package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.TurmaDisciplinaDomain

interface TurmaDisciplinaRepository {

    suspend fun fetchAll(turmaId: String): List<TurmaDisciplinaDomain>

    suspend fun add(turmaId: String, disciplinaId: String)

    suspend fun delete(turmaDisciplinaId: String)

}