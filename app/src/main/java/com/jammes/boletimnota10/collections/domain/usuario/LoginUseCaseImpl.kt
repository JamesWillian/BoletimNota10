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

    override suspend fun invoke(usuario: String, senha: String): UsuarioDomain {
        Log.d(TAG, "Fazendo login do usuário $usuario")
        val emptyUser = UsuarioDomain("","",false,"")
        return try {

            val response = usuarioApiService.login(usuario, senha)

            val user = response.body()

            if (response.isSuccessful) {
                EncryptedSharedPreferencesUtil.saveSessionToken(context, user!!.result.sessionToken)

                user.result
            } else {
                Log.d(TAG, "Sem Sucesso: ${response.message()} - errorBody: ${response.errorBody().toString()} - code: ${response.code()}")
                emptyUser
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception ${e.message}")
            emptyUser
        }
    }

    companion object {
        private const val TAG = "LoginUseCase"
    }
}