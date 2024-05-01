package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.TurmaRepository
import javax.inject.Inject

class InserirTurmaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): InserirTurmaUseCase {

    override suspend fun invoke(
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    ): String {
        Log.d(TAG, "Gravando nova Turma: $nome")

        return turmaRepository.add(nome, escola, turno, ano, dataInicio, dataFinal)
    }

    companion object {
        private const val TAG = "InserirTurma"
    }
}