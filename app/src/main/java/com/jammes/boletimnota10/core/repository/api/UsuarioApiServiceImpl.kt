package com.jammes.boletimnota10.core.repository.api

import android.util.Log
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

        return try {
            val response = usuarioApi.criarUsuarioAnonimo(usuario)
            if (response.isSuccessful) {
                Log.d("UsuarioApiServiceImpl", "Usuário criado com sucesso: ${response.body()}")
                Response.success(response.body())
            } else {
                Log.e("UsuarioApiServiceImpl", "Erro ao criar usuário: ${response.code()} - ${response.errorBody()?.string()}")
                Response.error(response.code(), response.errorBody()!!)
            }
        } catch (e: Exception) {
            Log.e("UsuarioApiServiceImpl", "Erro na requisição: ${e.message}", e)
            throw e
//            Response.error(response.code(), response.errorBody()!!)
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