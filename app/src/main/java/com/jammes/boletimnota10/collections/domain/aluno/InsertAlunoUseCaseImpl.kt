package com.jammes.boletimnota10.collections.domain.aluno

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.AlunoRepository
import javax.inject.Inject

class InsertAlunoUseCaseImpl @Inject constructor(
    private val alunoRepository: AlunoRepository
): InsertAlunoUseCase {

    override suspend fun invoke(idAluno: String): String {

        Log.d(TAG, "Gravando novo Aluno: $idAluno")

        return alunoRepository.add(idAluno)
    }

    companion object {
        private const val TAG = "InsertAluno"
    }
}