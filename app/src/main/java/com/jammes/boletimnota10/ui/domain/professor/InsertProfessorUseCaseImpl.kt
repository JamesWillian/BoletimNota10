package com.jammes.boletimnota10.ui.domain.professor

import android.util.Log
import com.jammes.boletimnota10.core.repository.ProfessorRepository
import javax.inject.Inject

class InsertProfessorUseCaseImpl @Inject constructor(
    private val professorRepository: ProfessorRepository
): InsertProfessorUseCase {

    override suspend fun invoke(nome: String, disciplinaId: String): Boolean {
        Log.d(TAG, "Gravando novo Professor: $nome")

        professorRepository.add(nome, disciplinaId)

        return true
    }

    companion object {
        private const val TAG = "InsertProfessor"
    }
}