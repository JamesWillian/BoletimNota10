package com.jammes.boletimnota10.core.repository.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AlunoApiServiceImpl @Inject constructor(
    private val alunoApi: AlunoApiService
) {
    suspend fun criarUsuarioAnonimo(): Response<String> {
        val response = alunoApi.criarAluno()
        return try {

            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    Log.d("AlunoApiService", "Response: ${response.body()}")

                    Response.success(response.body())
                } else {
                    Log.e(
                        "AlunoApiService",
                        "Não foi possivel obter resposta da API. Error: ${response.message()}"
                    )
                    Response.error(response.code(), response.errorBody()!!)
                }
            }
        } catch (e: Exception) {
            Log.e("AlunoApiService", "error: ${e.message}")
            Response.error(response.code(), response.errorBody()!!)
        }
    }
}