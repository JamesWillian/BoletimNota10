package com.jammes.boletimnota10.core.repository.api

import android.util.Log
import com.jammes.boletimnota10.core.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UsuarioApiServiceImpl @Inject constructor(
    private val usuarioApi: UsuarioApiService
) {

    suspend fun criarUsuarioAnonimo(
        usuario: String,
        senha: String
    ): Response<String> {
        val response = usuarioApi.criarUsuarioAnonimo(
            usuario = usuario,
            senha = senha
        )
        return try {

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    Log.d("UsuarioApiService", "Response: ${response.body()}")

                    Response.success(response.body())
                } else {
                    Log.e(
                        "UsuarioApiService",
                        "Não foi possivel obter resposta da API. Error: ${response.message()}"
                    )
                    Response.error(response.code(), response.errorBody()!!)
                }
            }
        } catch (e: Exception) {
            Log.e("UsuarioApiService", "error: ${e.message}")
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    suspend fun criarUsuario(
        id: String,
        email: String,
        senha: String
    ): Response<String> {
        val response = usuarioApi.criarUsuario(
            id = id,
            email = email,
            senha = senha
        )
        return try {

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    Response.success(response.body())
                } else {
                    Log.e(
                        "UsuarioApiService",
                        "Não foi possivel obter resposta da API. Error: ${response.message()}"
                    )
                    Response.error(response.code(), response.errorBody()!!)
                }
            }
        } catch (e: Exception) {
            Log.e("UsuarioApiService", "error: ${e.message}")
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    suspend fun login(
        usuario: String,
        senha: String
    ): Response<LoginResponse?> {
        val response = usuarioApi.login(
            usuario = usuario,
            senha = senha
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