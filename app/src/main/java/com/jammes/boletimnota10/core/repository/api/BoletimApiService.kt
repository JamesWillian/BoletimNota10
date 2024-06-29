package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.responses.BoletimModuloResponse
import com.jammes.boletimnota10.core.repository.api.responses.BoletimPeriodoResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface BoletimApiService {

    @POST("buscar-boletim-periodo")
    suspend fun buscarBoletimPeriodo(
        @Query("periodoId") periodoId: String
    ): Response<List<BoletimPeriodoResponse?>>

    @POST("buscar-boletim-modulo")
    suspend fun buscarBoletimModulo(
        @Query("moduloId") moduloId: String
    ): Response<List<BoletimModuloResponse?>>
}