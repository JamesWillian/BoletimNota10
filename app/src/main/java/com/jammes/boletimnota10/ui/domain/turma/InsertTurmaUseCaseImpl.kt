package com.jammes.boletimnota10.ui.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import javax.inject.Inject

class InsertTurmaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): InsertTurmaUseCase {

    override suspend fun invoke(
        nome: String,
        escola: String,
        periodo: String,
        turno: String,
        ano: Int
    ): String {
        Log.d(TAG, "Gravando nova Turma: $nome")

        return turmaRepository.add(nome, escola, periodo, turno, ano)
    }

    companion object {
        private const val TAG = "InsertTurma"
    }
}