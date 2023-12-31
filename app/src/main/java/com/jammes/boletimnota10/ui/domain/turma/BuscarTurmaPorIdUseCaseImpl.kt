package com.jammes.boletimnota10.ui.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.ui.model.TurmaItem
import javax.inject.Inject

class BuscarTurmaPorIdUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
) : BuscarTurmaPorIdUseCase {

    override suspend fun invoke(turmaId: String): TurmaItem {
        Log.d(TAG, "Buscando Turma Por Id: $turmaId")

        val turma = turmaRepository.buscarTurmaPorId(turmaId)

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
        private const val TAG = "BuscarTurmaPorId"
    }
}