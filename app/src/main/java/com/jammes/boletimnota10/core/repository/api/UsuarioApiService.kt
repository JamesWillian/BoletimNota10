package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.UsuarioBody
import com.jammes.boletimnota10.core.repository.api.responses.LoginResponse
import com.jammes.boletimnota10.core.repository.api.responses.UsuarioResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioApiService {

    @POST("criar-aluno-visitante")
    suspend fun criarUsuarioAnonimo(
        @Body usuario: UsuarioBody
    ): Response<UsuarioResponse?>

    @POST("criar-aluno")
    suspend fun criarUsuario(
        @Body usuario: UsuarioBody
    ): Response<UsuarioResponse?>

    @POST("login")
    suspend fun login(
        @Body usuario: UsuarioBody
    ): Response<LoginResponse?>
}