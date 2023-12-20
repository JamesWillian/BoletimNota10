package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.ProfessorDomain

interface ProfessorRepository {

    suspend fun fetchAll(): List<ProfessorDomain>

    suspend fun add(nome: String, disciplinaId: String)

    suspend fun post(professorId: String, nome: String, disciplinaId: String)

    suspend fun delete(professorId: String)

}