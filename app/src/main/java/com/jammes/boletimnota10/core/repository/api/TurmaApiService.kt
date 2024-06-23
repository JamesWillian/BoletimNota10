package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.TurmaBody
import com.jammes.boletimnota10.core.repository.api.responses.TurmaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TurmaApiService {

    @POST("criar-turma")
    suspend fun criarTurma(
        @Body turma: TurmaBody
    ): Response<TurmaResponse?>
}