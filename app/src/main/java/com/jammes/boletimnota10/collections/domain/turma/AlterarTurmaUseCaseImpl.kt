package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import javax.inject.Inject

class AlterarTurmaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): AlterarTurmaUseCase {

    override suspend fun invoke(
        turmaId: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    ) {
        Log.d(TAG, "Alterando Turma: $turmaId")

        return turmaRepository.post(turmaId, nome, escola, turno, ano, dataInicio, dataFinal)
    }

    companion object {
        private const val TAG = "AlterarTurma"
    }
}