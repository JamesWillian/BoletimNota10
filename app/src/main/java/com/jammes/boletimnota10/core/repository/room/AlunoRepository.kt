package com.jammes.boletimnota10.core.repository.room

import com.jammes.boletimnota10.core.model.AlunoDomain

interface AlunoRepository {

    suspend fun fetch(): AlunoDomain

    suspend fun add(idAluno: String): String

    suspend fun post(alunoId: String, nome: String): Boolean

}