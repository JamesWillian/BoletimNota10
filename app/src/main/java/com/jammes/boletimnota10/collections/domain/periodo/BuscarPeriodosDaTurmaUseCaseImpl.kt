package com.jammes.boletimnota10.collections.domain.periodo

import android.util.Log
import com.jammes.boletimnota10.collections.model.PeriodoItem
import com.jammes.boletimnota10.core.repository.room.PeriodoRepository
import javax.inject.Inject

class BuscarPeriodosDaTurmaUseCaseImpl @Inject constructor(
    private val periodoRepository: PeriodoRepository
): BuscarPeriodosDaTurmaUseCase {

    override suspend fun invoke(turmaId: String): List<PeriodoItem> {

        Log.d(TAG, "Listando todos os Períodos da Turma: $turmaId")

        return periodoRepository
            .fetch(turmaId)
            .map {
                PeriodoItem(
                    id = it.id,
                    periodo = it.descricao,
                    turmaId = it.turmaId
                )
            }
    }

    companion object {
        private const val TAG = "BuscarPeriodosDaTurma"
    }
}