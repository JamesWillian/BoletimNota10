package com.jammes.boletimnota10.collections.domain.aluno

import android.content.Context
import android.util.Log
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import com.jammes.boletimnota10.core.repository.api.params.UsuarioBody
import javax.inject.Inject

class CriarAlunoUseCaseImpl @Inject constructor(
    private val usuarioApiService: UsuarioApiService,
    private val inserirAlunoUseCase: InsertAlunoUseCase,
    private val context: Context
): CriarAlunoUseCase {
    override suspend fun invoke(usuario: String, senha: String, email: String): Boolean {
        Log.d(TAG, "Criando aluno com usuario $usuario")

        val usuarioBody = UsuarioBody(usuario, senha, email)

        return try {

            val response = usuarioApiService.criarUsuario(usuarioBody)
            val token = response.body()?.result?.token ?: ""
            val alunoId = response.body()?.result?.alunoId ?: ""

            if (response.isSuccessful) {
                EncryptedSharedPreferencesUtil.saveSessionToken(context, token)

                val idAluno = inserirAlunoUseCase.invoke(alunoId)
                if (idAluno.isNotEmpty())
                    Log.d(TAG, "Sucesso ao inserir aluno $idAluno")

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
        private const val TAG = "CriarAlunoUseCase"
    }
}