package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.DisciplinaDao
import com.jammes.boletimnota10.core.database.entity.Disciplina
import com.jammes.boletimnota10.core.model.DisciplinaDomain
import java.util.UUID
import javax.inject.Inject

class DisciplinaRepositoryImpl @Inject constructor(
    private val dao: DisciplinaDao
): DisciplinaRepository {

    override suspend fun fetchAll(): List<DisciplinaDomain> {
        Log.d(TAG, "Listando todas as Disciplinas")
        return dao.fetchDisciplina().map { disciplina ->
            DisciplinaDomain(
                id = disciplina.uuid,
                descricao = disciplina.descricao
            )
        }
    }

    override suspend fun fetchById(disciplinaId: String): DisciplinaDomain {
        Log.d(TAG, "Buscando Disciplina por Id: $disciplinaId")
        val disciplina = dao.fetchDisciplinaById(disciplinaId)
        return DisciplinaDomain(
            disciplina.uuid,
            disciplina.descricao
        )
    }

    override suspend fun add(descricao: String) {
        Log.d(TAG, "Adicionando nova Disciplina: $descricao")
        val disciplina = Disciplina(
            uuid = UUID.randomUUID().toString(),
            descricao = descricao
        )
        dao.insert(disciplina)
    }

    override suspend fun post(disciplinaId: String, descricao: String) {
        Log.d(TAG, "Atualizando a Disciplina: $descricao")
        val disciplina = Disciplina(
            uuid = disciplinaId,
            descricao = descricao
        )
        dao.update(disciplina)
    }

    override suspend fun delete(disciplinaId: String) {
        Log.d(TAG, "Excluindo a Disciplina: $disciplinaId")
        val disciplina = Disciplina(
            uuid = disciplinaId,
            descricao = ""
        )
        dao.delete(disciplina)
    }

    companion object {
        private const val TAG = "DisciplinaRepository"
    }
}