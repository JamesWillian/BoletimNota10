package com.jammes.boletimnota10.core.repository.room

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

        return dao.existeTurmaCadastrada()?: false
    }

    override suspend fun buscarTurmaPorId(turmaId: String): TurmaDomain {

        Log.d(TAG, "Buscando Turma: $turmaId")

        val turma = dao.selectTurmaPorId(turmaId)

        return TurmaDomain(
            id = turma.id,
            nome = turma.nome,
            escola = turma.escola,
            turno = turma.turno,
            ano = turma.ano,
            dataInicio = turma.dataInicio,
            dataFinal = turma.dataFinal?: "",
            concluido = turma.concluido
        )
    }

    override suspend fun buscarTodasTurmas(): List<TurmaDomain> {

        Log.d(TAG, "Buscando Todas as Turmas")

        return dao.selectTodasTurmas().map { turma ->
            TurmaDomain(
                id = turma.id,
                nome = turma.nome,
                escola = turma.escola,
                turno = turma.turno,
                ano = turma.ano,
                dataInicio = turma.dataInicio,
                dataFinal = turma.dataFinal?: "",
                concluido = turma.concluido
            )
        }
    }

    override suspend fun add(
        id: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String
    ): String {
        Log.d(TAG, "Adicionando nova Turma: $nome")
        val turma = Turma(
            id = id,
            nome = nome,
            escola = escola,
            turno = turno,
            ano = ano,
            dataInicio = dataInicio
        )
        dao.insert(turma)
        return turma.id
    }

    override suspend fun post(
        turmaId: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String?
    ) {
        Log.d(TAG, "Atualizando a Turma: $nome")
        val turma = Turma(
            id = turmaId,
            nome = nome,
            escola = escola,
            turno = turno,
            ano = ano,
            dataInicio = dataInicio,
            dataFinal = dataFinal?: "",
        )
        dao.update(turma)
    }

    override suspend fun delete(turmaId: String) {
        Log.d(TAG, "Excluindo a Turma: $turmaId")
        val turma = Turma(
            id = turmaId,
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