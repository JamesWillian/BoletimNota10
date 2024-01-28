package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.collections.model.TurmaItem
import javax.inject.Inject

class BuscarTurmaAtualUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): BuscarTurmaAtualUseCase {

    override suspend fun invoke(): TurmaItem? {

        Log.d(TAG, "Obtendo a Turma Atual")

        val turma = turmaRepository.buscarTurmaAtiva()

        return if (turma != null) {
            TurmaItem(
                id = turma.id,
                nome = turma.nome,
                escola = turma.escola,
                turno = turma.turno,
                ano = turma.ano,
                dataInicio = turma.dataInicio,
                dataFinal = turma.dataFinal,
                concluido = turma.concluido
            )
        } else null
    }

    companion object {
        private const val TAG = "BuscarTurmaAtual"
    }
}