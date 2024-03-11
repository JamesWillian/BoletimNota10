package com.jammes.boletimnota10.core.repository.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
    ): Result<String> {
        return try {
            val response = usuarioApi.login(
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
}