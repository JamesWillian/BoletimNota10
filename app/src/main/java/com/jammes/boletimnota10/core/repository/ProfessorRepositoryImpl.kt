package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.ProfessorDao
import com.jammes.boletimnota10.core.database.entity.Disciplina
import com.jammes.boletimnota10.core.database.entity.Professor
import com.jammes.boletimnota10.core.model.ProfessorDomain
import java.util.UUID
import javax.inject.Inject

class ProfessorRepositoryImpl @Inject constructor(
    private val dao: ProfessorDao
): ProfessorRepository {

    override suspend fun fetchAll(): List<ProfessorDomain> {
        Log.d(TAG, "Listando todas os Professores.")
        return dao.fetchProfessor().map { prof ->
            ProfessorDomain(
                id = prof.uuid,
                nome = prof.nome,
                disciplina = Disciplina(prof.disciplinaId,"")
            )
        }
    }

    override suspend fun add(nome: String, disciplinaId: String) {
        Log.d(TAG, "Adicionando novo Professor: $nome")
        val professor = Professor(
            uuid = UUID.randomUUID().toString(),
            nome = nome,
            disciplinaId = disciplinaId
        )
        dao.insert(professor)
    }

    override suspend fun post(professorId: String, nome: String, disciplinaId: String) {
        Log.d(TAG, "Atualizando o Professor: $nome")
        val professor = Professor(
            uuid = professorId,
            nome = nome,
            disciplinaId = disciplinaId
        )
        dao.update(professor)
    }

    override suspend fun delete(professorId: String) {
        Log.d(TAG, "Excluindo o Professor: $professorId")
        val professor = Professor(
            uuid = professorId,
            nome = "",
            disciplinaId = ""
        )
        dao.delete(professor)
    }

    companion object {
        private const val TAG = "ProfessorRepository"
    }
}