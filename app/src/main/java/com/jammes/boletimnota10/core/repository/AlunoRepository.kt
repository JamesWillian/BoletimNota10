package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.AlunoDomain

interface AlunoRepository {

    suspend fun fetch(): AlunoDomain

    suspend fun add(nome: String): String

    suspend fun post(alunoId: String, nome: String): Boolean

}