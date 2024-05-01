package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.TurmaRepository
import com.jammes.boletimnota10.collections.model.TurmaItem
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
            turno = turma.turno,
            ano = turma.ano,
            dataInicio = turma.dataInicio,
            dataFinal = turma.dataFinal,
            concluido = turma.concluido
        )
    }

    companion object {
        private const val TAG = "BuscarTurmaPorId"
    }
}