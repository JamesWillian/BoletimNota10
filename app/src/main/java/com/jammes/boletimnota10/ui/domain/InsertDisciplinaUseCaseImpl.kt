package com.jammes.boletimnota10.ui.domain

import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository

class InsertDisciplinaUseCaseImpl(
    private val disciplinaRepository: DisciplinaRepository
): InsertDisciplinaUseCase {

    override suspend fun invoke(descricao: String): Boolean {

        Log.d(TAG, "Gravando nova Disciplina: $descricao")

        disciplinaRepository.add(descricao)

        return true
    }

    companion object {
        private const val TAG = "InsertDisciplina"
    }
}