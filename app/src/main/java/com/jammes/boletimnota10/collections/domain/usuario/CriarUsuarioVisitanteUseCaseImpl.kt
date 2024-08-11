package com.jammes.boletimnota10.collections.domain.usuario

import android.content.Context
import android.util.Log
import com.jammes.boletimnota10.collections.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.core.repository.api.UsuarioApiService
import com.jammes.boletimnota10.core.repository.api.params.UsuarioBody
import javax.inject.Inject

class CriarUsuarioVisitanteUseCaseImpl @Inject constructor(
    private val usuarioApiService: UsuarioApiService,
    private val inserirAlunoUseCase: InsertAlunoUseCase,
    private val context: Context
): CriarUsuarioVisitanteUseCase {

    override suspend fun invoke(usuario: String, senha: String): Boolean {
        Log.d(TAG, "Debug:: Criando usuário anonimo $usuario")

//        val idAluno = inserirAlunoUseCase.invoke("1")
//        if (idAluno.isNotEmpty())
//            Log.d(TAG, "Sucesso ao inserir aluno $idAluno")

        val usuarioBody = UsuarioBody(usuario, senha, "")

        val response = usuarioApiService.criarUsuarioAnonimo(usuarioBody)
        val token = response.body()?.result?.token ?: ""
        val alunoId = response.body()?.result?.alunoId ?: ""

        Log.d(TAG, "Result:: $alunoId :: ${response.message()} - errorBody: ${response.errorBody().toString()} - code: ${response.code()}")

        return try {


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
        private const val TAG = "CriarUserVisitUseCase"
    }
}