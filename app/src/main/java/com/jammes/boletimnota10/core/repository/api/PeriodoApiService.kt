package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.PeriodoBody
import com.jammes.boletimnota10.core.repository.api.responses.PeriodoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PeriodoApiService {

    @POST("criar-periodo")
    suspend fun criarPeriodo(
        @Body periodo: PeriodoBody
    ): Response<PeriodoResponse?>

    @POST("buscar-periodos")
    suspend fun buscarPeriodo(
        @Query("turmaId") turmaId: String
    ): Response<List<PeriodoResponse?>>

}