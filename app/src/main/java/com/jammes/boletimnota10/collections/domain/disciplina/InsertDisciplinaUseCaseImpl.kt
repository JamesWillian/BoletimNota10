package com.jammes.boletimnota10.collections.domain.disciplina

import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import javax.inject.Inject

class InsertDisciplinaUseCaseImpl @Inject constructor(
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