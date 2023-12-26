package com.jammes.boletimnota10.core.repository

import android.annotation.SuppressLint
import android.util.Log
import com.jammes.boletimnota10.core.database.dao.TurmaDisciplinaDao
import com.jammes.boletimnota10.core.database.entity.TurmaDisciplina
import com.jammes.boletimnota10.core.model.TurmaDisciplinaDomain
import java.util.UUID
import javax.inject.Inject

class TurmaDisciplinaRepositoryImpl @Inject constructor(
    private val dao: TurmaDisciplinaDao
): TurmaDisciplinaRepository {

    override suspend fun fetchAll(turmaId: String): List<TurmaDisciplinaDomain> {
        Log.d(TAG, "Listando todas as Disciplinas da Turma: $turmaId")
        return dao.fetchTurmaDisciplina(turmaId).map {
            TurmaDisciplinaDomain(
                id = it.uuid,
                turmaId = it.turmaId,
                disciplinaId = it.disciplinaId
            )
        }
    }

    override suspend fun add(turmaId: String, disciplinaId: String) {
        Log.d(TAG, "Adicionando nova disciplina da turma: $disciplinaId")
        val tipoAtividade = TurmaDisciplina(
            uuid = UUID.randomUUID().toString(),
            turmaId = turmaId,
            disciplinaId = disciplinaId
        )
        dao.insert(tipoAtividade)
    }

    override suspend fun delete(turmaDisciplinaId: String) {
        Log.d(TAG, "Excluindo a disciplina da turma: $turmaDisciplinaId")
        val tipoAtividade = TurmaDisciplina(
            uuid = turmaDisciplinaId,
            turmaId = "",
            disciplinaId = ""
        )
        dao.delete(tipoAtividade)
    }

    companion object {
        private const val TAG = "TurmaDisciplinaRepo"
    }
}