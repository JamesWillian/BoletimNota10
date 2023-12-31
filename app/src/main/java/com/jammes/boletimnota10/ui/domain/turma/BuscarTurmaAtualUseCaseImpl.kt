package com.jammes.boletimnota10.ui.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.ui.model.TurmaItem
import javax.inject.Inject

class BuscarTurmaAtualUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): BuscarTurmaAtualUseCase {

    override suspend fun invoke(): TurmaItem {

        Log.d(TAG, "Obtendo a Turma Atual")

        val turma = turmaRepository.buscarTurmaAtiva()

        return TurmaItem(
                    id = turma.id,
                    nome = turma.nome,
                    escola = turma.escola,
                    periodo = turma.periodo,
                    turno = turma.turno,
                    ano = turma.ano,
                    concluido = turma.concluido
                )
    }

    companion object {
        private const val TAG = "BuscarTurmaAtual"
    }
}