package com.jammes.boletimnota10.collections.domain.aluno

import android.util.Log
import com.jammes.boletimnota10.core.repository.AlunoRepository
import javax.inject.Inject

class UpdateAlunoUseCaseImpl @Inject constructor(
    private val alunoRepository: AlunoRepository
): UpdateAlunoUseCase {
    override suspend fun invoke(alunoId: String, nome: String): Boolean {

        Log.d(TAG, "Atualizando nome do Aluno $alunoId para $nome")

        val alunoInvalido = (alunoId.isEmpty() || nome.isEmpty())

        return if (alunoInvalido) false else alunoRepository.post(alunoId, nome)

    }

    companion object {
        const val TAG = "UpdateAlunoUseCase"
    }
}