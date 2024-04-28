package com.jammes.boletimnota10.core.repository.api

import android.util.Log
import com.google.gson.JsonObject
import com.jammes.boletimnota10.core.model.LoginResponse
import com.jammes.boletimnota10.core.model.UsuarioDomain
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
    ): Result<String> {
        return try {
            val response = usuarioApi.criarUsuarioAnonimo(
                usuario = usuario,
                senha = senha
            )

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    val sessionToken = response.body() ?: ""
                    Result.success(sessionToken)
                } else {
                    Log.e("UsuarioApiService","Não foi possivel obter resposta da API. Error: ${response.message()}")
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            Log.e("UsuarioApiService","error: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun criarUsuario(
        id: String,
        email: String,
        senha: String
    ): Result<String> {
        return try {
            val response = usuarioApi.criarUsuario(
                id = id,
                email = email,
                senha = senha
            )

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    val sessionToken = response.body() ?: ""
                    Result.success(sessionToken)
                } else {
                    Log.e("UsuarioApiService","Não foi possivel obter resposta da API. Error: ${response.message()}")
                    Result.failure(Exception("Error: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            Log.e("UsuarioApiService","error: ${e.message}")
            Result.failure(e)
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
            if (response.isSuccessful)
                Log.i("UsuarioAPI", "${response.body()!!.result.sessionToken} - ${response.body()!!.result.username} - ${response.body()!!.result.email}")
            else
                Log.i("UsuarioAPI", "Vazio :(")

            Response.success(response.body())
        } catch (e: Exception) {
            Response.error<LoginResponse>(response.code(), response.errorBody()!!)
        }

    }
}