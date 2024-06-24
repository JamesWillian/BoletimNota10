package com.jammes.boletimnota10.core.repository.api

import com.jammes.boletimnota10.core.repository.api.params.UsuarioBody
import com.jammes.boletimnota10.core.repository.api.responses.LoginResponse
import com.jammes.boletimnota10.core.repository.api.responses.UsuarioResponse
import retrofit2.Response
import javax.inject.Inject

class UsuarioApiServiceImpl @Inject constructor(
    private val usuarioApi: UsuarioApiService,
): UsuarioApiService {

    override suspend fun criarUsuarioAnonimo(
        usuario: UsuarioBody
    ): Response<UsuarioResponse?> {
        val response = usuarioApi.criarUsuarioAnonimo(
            usuario = usuario
        )

        return try {
            if (response.isSuccessful)
                Response.success(response.body())
            else
                Response.error(response.code(), response.errorBody()!!)

        } catch (e: Exception) {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun criarUsuario(
        usuario: UsuarioBody
    ): Response<UsuarioResponse?> {
        val response = usuarioApi.criarUsuario(
            usuario = usuario
        )

        return try {
            if (response.isSuccessful) {
                Response.success(response.body())
            } else {
                Response.error(response.code(), response.errorBody()!!)
            }
        } catch (e: Exception) {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun login(
        usuario: UsuarioBody
    ): Response<LoginResponse?> {
        val response = usuarioApi.login(
            usuario = usuario
        )

        return try {
            if (response.isSuccessful) {
                Response.success(response.body())
            } else {
                Response.error(response.code(), response.errorBody()!!)
            }

        } catch (e: Exception) {
            Response.error(response.code(), response.errorBody()!!)
        }

    }
}