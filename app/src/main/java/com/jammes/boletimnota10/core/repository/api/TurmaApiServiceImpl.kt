package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.TurmaBody
import com.jammes.boletimnota10.core.repository.api.responses.TurmaResponse
import retrofit2.Response
import javax.inject.Inject

class TurmaApiServiceImpl @Inject constructor(
    private val turmaApi: TurmaApiService
) {
    suspend fun criarTurma(
        turma: TurmaBody
    ): Response<TurmaResponse?> {
        val response = turmaApi.criarTurma(turma)

        return try {
            if (response.isSuccessful)
                Response.success(response.body()!!)
            else
                Response.error(response.code(), response.errorBody()!!)

        } catch (e: Exception) {
            Response.error(response.code(), response.errorBody()!!)
        }
    }
}