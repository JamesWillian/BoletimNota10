package com.jammes.boletimnota10.core.repository.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuarioApiService {

    @POST("criar-usuario-anonimo")
    suspend fun criarUsuarioAnonimo(
        @Query("username") usuario: String,
        @Query("password") senha: String
    ): Response<String>

    @POST("criar-usuario")
    suspend fun criarUsuario(
        @Query("id") id: String,
        @Query("email") email: String,
        @Query("password") senha: String
    ): Response<String>

    @POST("login")
    suspend fun login(
        @Query("username") usuario: String,
        @Query("password") senha: String
    ): Response<String>
}