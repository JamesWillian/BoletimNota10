package com.jammes.boletimnota10.collections.domain.usuario

import android.content.Context
import android.util.Log
import com.jammes.boletimnota10.collections.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.core.repository.api.AlunoApiService
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import javax.inject.Inject

class CriarUsuarioAnonimoUseCaseImpl @Inject constructor(
    private val usuarioApiService: UsuarioApiService,
    private val alunoApiService: AlunoApiService,
    private val inserirAlunoUseCase: InsertAlunoUseCase,
    private val context: Context
): CriarUsuarioAnonimoUseCase {

    override suspend fun invoke(usuario: String, senha: String): Boolean {
        Log.d(TAG, "Criando usuário anonimo $usuario")
        return try {

            val response = usuarioApiService.criarUsuarioAnonimo(usuario, senha)

            val user = response.body()

            if (response.isSuccessful) {
                EncryptedSharedPreferencesUtil.saveSessionToken(context, user!!)

                val aluno = alunoApiService.criarAluno()
                if (aluno.isSuccessful) {
                    val idAluno = inserirAlunoUseCase.invoke(aluno.body()!!)
                    if (idAluno.isNotEmpty()) {
                        Log.d(TAG, "Sucesso ao inserir aluno $idAluno")
                    }
                }

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