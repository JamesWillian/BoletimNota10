package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.TurmaDao
import com.jammes.boletimnota10.core.database.entity.Turma
import com.jammes.boletimnota10.core.model.TurmaDomain
import java.util.UUID
import javax.inject.Inject

class TurmaRepositoryImpl @Inject constructor(
    private val dao: TurmaDao
): TurmaRepository {

    override suspend fun fetchAll(): List<TurmaDomain> {
        Log.d(TAG, "Listando todas as Turmas")
        return dao.fetchTurmas().map { turma ->
            TurmaDomain(
                id = turma.uuid,
                nome = turma.nome,
                periodo = turma.periodo
            )
        }
    }

    override suspend fun add(nome: String, periodo: String) {
        Log.d(TAG, "Adicionando nova Turma: $nome")
        val turma = Turma(
            uuid = UUID.randomUUID().toString(),
            nome = nome,
            periodo = periodo
        )
        dao.insert(turma)
    }

    override suspend fun post(turmaId: String, nome: String, periodo: String) {
        Log.d(TAG, "Atualizando a Turma: $nome")
        val turma = Turma(
            uuid = turmaId,
            nome = nome,
            periodo = periodo
        )
        dao.update(turma)
    }

    override suspend fun delete(turmaId: String) {
        Log.d(TAG, "Excluindo a Turma: $turmaId")
        val turma = Turma(
            uuid = turmaId,
            nome = "",
            periodo = ""
        )
        dao.delete(turma)
    }

    companion object {
        private const val TAG = "TurmaRepository"
    }
}