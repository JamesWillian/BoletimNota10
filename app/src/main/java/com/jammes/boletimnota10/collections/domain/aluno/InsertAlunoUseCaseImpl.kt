package com.jammes.boletimnota10.collections.domain.aluno

import android.util.Log
import com.jammes.boletimnota10.core.repository.AlunoRepository
import javax.inject.Inject

class InsertAlunoUseCaseImpl @Inject constructor(
    private val alunoRepository: AlunoRepository
): InsertAlunoUseCase {

    override suspend fun invoke(nome: String): Boolean {

        Log.d(TAG, "Gravando novo Aluno: $nome")

        alunoRepository.add(nome)

        return true
    }

    companion object {
        private const val TAG = "InsertAluno"
    }
}