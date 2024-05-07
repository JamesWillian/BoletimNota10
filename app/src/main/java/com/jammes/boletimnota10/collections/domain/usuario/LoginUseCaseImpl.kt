package com.jammes.boletimnota10.collections.domain.usuario

import android.content.Context
import android.util.Log
import com.jammes.boletimnota10.core.model.UsuarioDomain
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val usuarioApiService: UsuarioApiService,
    private val context: Context
) : LoginUseCase {

    override suspend fun invoke(usuario: String, senha: String): Boolean {
        Log.d(TAG, "Fazendo login do usuário $usuario")
        val emptyUser = UsuarioDomain("","",false,"")

        return try {

            val response = usuarioApiService.login(usuario, senha)
            val user = response.body()?.result ?: emptyUser

            Log.d(TAG, "Response: ${response.message()} - code: ${response.code()}")

            EncryptedSharedPreferencesUtil.saveUserPreference(context, user)
            response.isSuccessful

        } catch (e: Exception) {
            Log.d(TAG, "Exception ${e.message}")
            false
        }
    }

    companion object {
        private const val TAG = "LoginUseCase"
    }
}