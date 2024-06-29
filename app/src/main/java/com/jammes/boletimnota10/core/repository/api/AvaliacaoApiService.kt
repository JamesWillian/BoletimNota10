package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.AvaliacaoBody
import com.jammes.boletimnota10.core.repository.api.responses.AvaliacaoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AvaliacaoApiService {

    @POST("criar-avaliacao")
    suspend fun criarAvaliacao(
        @Body avaliacao: AvaliacaoBody
    ): Response<AvaliacaoResponse?>

    @POST("alterar-avaliacao")
    suspend fun alterarAvaliacao(
        @Body avaliacao: AvaliacaoBody
    ): Response<AvaliacaoResponse?>

    @POST("deletar-avaliacao")
    suspend fun deletarAvaliacao(
        @Query("avaliacaoId") avaliacaoId: String
    ): Response<AvaliacaoResponse?>

    @POST("buscar-avaliacoes")
    suspend fun buscarAvaliacoes(
        @Query("moduloId") moduloId: String
    ): Response<List<AvaliacaoResponse?>>
}