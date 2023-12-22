package com.jammes.boletimnota10.ui.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import javax.inject.Inject

class InsertTurmaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): InsertTurmaUseCase {

    override suspend fun invoke(nome: String, periodo: String): Boolean {
        Log.d(TAG, "Gravando nova Turma: $nome")

        turmaRepository.add(nome, periodo)

        return true
    }

    companion object {
        private const val TAG = "InsertTurma"
    }
}