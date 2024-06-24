package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.ModuloBody
import com.jammes.boletimnota10.core.repository.api.responses.ModuloResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ModuloApiService {

    @POST("criar-modulo")
    suspend fun criarModulo(
        @Body modulo: ModuloBody
    ): Response<ModuloResponse?>

    @POST("deletar-modulo")
    suspend fun deletarModulo(
        @Query("moduloId") moduloId: String
    ): Response<String>

    @POST("buscar-modulos")
    suspend fun buscarModulos(
        @Query("periodoId") periodoId: String
    ): Response<List<ModuloResponse?>>

}