package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.TurmaDao
import com.jammes.boletimnota10.core.database.entity.Turma
import com.jammes.boletimnota10.core.model.TurmaDomain
import java.util.UUID
import javax.inject.Inject

class TurmaRepositoryImpl @Inject constructor(
    private val dao: TurmaDao
) : TurmaRepository {

    override suspend fun existeTurmaCadastrada(): Boolean {

        return dao.existeTurmaCadastrada()
    }

    override suspend fun buscarTurmaAtiva(): TurmaDomain {
        Log.d(TAG, "Buscando Turma Ativa")

        val turma = dao.selectTurmaAtiva()

        return TurmaDomain(
                id = turma.uuid,
                nome = turma.nome,
                escola = turma.escola,
                turno = turma.turno,
                ano = turma.ano,
                dataInicio = turma.dataInicio,
                dataFinal = turma.dataFinal,
                concluido = turma.concluido
            )
    }

    override suspend fun buscarTurmaPorId(turmaId: String): TurmaDomain {

        Log.d(TAG, "Buscando Turma: $turmaId")

        val turma = dao.selectTurmaPorId(turmaId)

        return TurmaDomain(
            id = turma.uuid,
            nome = turma.nome,
            escola = turma.escola,
            turno = turma.turno,
            ano = turma.ano,
            dataInicio = turma.dataInicio,
            dataFinal = turma.dataFinal,
            concluido = turma.concluido
        )
    }

    override suspend fun buscarTodasTurmas(): List<TurmaDomain> {

        Log.d(TAG, "Buscando Todas as Turmas")

        return dao.selectTodasTurmas().map { turma ->
            TurmaDomain(
                id = turma.uuid,
                nome = turma.nome,
                escola = turma.escola,
                turno = turma.turno,
                ano = turma.ano,
                dataInicio = turma.dataInicio,
                dataFinal = turma.dataFinal,
                concluido = turma.concluido
            )
        }
    }

    override suspend fun add(
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    ): String {
        Log.d(TAG, "Adicionando nova Turma: $nome")
        val turma = Turma(
            uuid = UUID.randomUUID().toString(),
            nome = nome,
            escola = escola,
            turno = turno,
            ano = ano,
            dataInicio = dataInicio,
            dataFinal = dataFinal,
        )
        dao.insert(turma)
        return turma.uuid
    }

    override suspend fun post(
        turmaId: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String
    ) {
        Log.d(TAG, "Atualizando a Turma: $nome")
        val turma = Turma(
            uuid = turmaId,
            nome = nome,
            escola = escola,
            turno = turno,
            ano = ano,
            dataInicio = dataInicio,
            dataFinal = dataFinal,
        )
        dao.update(turma)
    }

    override suspend fun delete(turmaId: String) {
        Log.d(TAG, "Excluindo a Turma: $turmaId")
        val turma = Turma(
            uuid = turmaId,
            nome = "",
            escola = "",
            turno = "",
            ano = "",
            dataInicio = "",
            dataFinal = "",
        )
        dao.delete(turma)
    }

    companion object {
        private const val TAG = "TurmaRepository"
    }
}