package com.jammes.boletimnota10.collections.domain.usuario

import android.content.Context
import android.util.Log
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import javax.inject.Inject

class CriarUsuarioAnonimoUseCaseImpl @Inject constructor(
    private val usuarioApiService: UsuarioApiService,
    private val context: Context
): CriarUsuarioAnonimoUseCase {

    override suspend fun invoke(usuario: String, senha: String): Boolean {
        Log.d(TAG, "Criando usuário anonimo $usuario")
        return try {

            val response = usuarioApiService.criarUsuarioAnonimo(usuario, senha)

            val user = response.body()

            if (response.isSuccessful) {
                EncryptedSharedPreferencesUtil.saveSessionToken(context, user!!)

                true
            } else {
                Log.d(TAG, "Sem Sucesso: ${response.message()} - errorBody: ${response.errorBody().toString()} - code: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception ${e.message}")
            false
        }
    }

    companion object {
        private const val TAG = "CriarUserAnonimoUseCase"
    }
}