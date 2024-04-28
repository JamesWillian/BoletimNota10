package com.jammes.boletimnota10.collections.domain.usuario

import android.util.Log
import com.google.gson.JsonObject
import com.jammes.boletimnota10.core.model.UsuarioDomain
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val usuarioApiService: UsuarioApiService
) : LoginUseCase {

    override suspend fun invoke(usuario: String, senha: String): UsuarioDomain {
        Log.d(TAG, "Fazendo login do usuário $usuario")
        val response = usuarioApiService.login(usuario, senha)
        val emptyUser = UsuarioDomain("","",false,"")

        val user = response.body()

        return if (response.isSuccessful) {
            Log.d(TAG, "${user!!.result.sessionToken} - ${user.result.username} - ${user.result.email}")
            user.result
        } else emptyUser
    }

    companion object {
        private const val TAG = "login"
    }
}