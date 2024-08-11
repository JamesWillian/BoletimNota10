package com.jammes.boletimnota10.core.repository.room

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.PeriodoDao
import com.jammes.boletimnota10.core.database.entity.Periodo
import com.jammes.boletimnota10.core.model.PeriodoDomain
import java.util.UUID
import javax.inject.Inject

class PeriodoRepositoryImpl @Inject constructor(
    private val dao: PeriodoDao
): PeriodoRepository {
    override suspend fun add(periodo: String, turmaId: String): String {
        Log.d(TAG, "Adicionando novo período $periodo a turma $turmaId")
        val novoPeriodo = Periodo(
            id = UUID.randomUUID().toString(),
            turmaId = turmaId,
            descricao = periodo
        )
        dao.inserir(novoPeriodo)

        return novoPeriodo.id
    }

    override suspend fun fetch(turmaId: String): List<PeriodoDomain> {
        Log.d(TAG, "Listando todos períodos da turma $turmaId")
        return dao.buscarTodosPeriodosDaTurma(turmaId).map {
            PeriodoDomain(
                id = it.id,
                turmaId = it.turmaId,
                descricao = it.descricao
            )
        }
    }

    companion object {
        private const val TAG = "PeriodoRepository"
    }
}