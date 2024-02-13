package com.jammes.boletimnota10.collections.domain.aluno

import android.util.Log
import com.jammes.boletimnota10.core.repository.AlunoRepository
import javax.inject.Inject

class InsertAlunoUseCaseImpl @Inject constructor(
    private val alunoRepository: AlunoRepository
): InsertAlunoUseCase {

    override suspend fun invoke(nome: String): String {

        Log.d(TAG, "Gravando novo Aluno: $nome")

        return alunoRepository.add(nome)
    }

    companion object {
        private const val TAG = "InsertAluno"
    }
}