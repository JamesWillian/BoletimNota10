package com.jammes.boletimnota10.core.repository.room

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.AlunoDao
import com.jammes.boletimnota10.core.database.entity.Aluno
import com.jammes.boletimnota10.core.model.AlunoDomain
import javax.inject.Inject

class AlunoRepositoryImpl @Inject constructor(
    private val dao: AlunoDao
): AlunoRepository {

    override suspend fun fetch(): AlunoDomain {

        Log.d(TAG, "Buscando Aluno")

        val aluno = dao.fetchAluno()

        return AlunoDomain(
            id = aluno.id,
            nome = aluno.nome,
            matricula = aluno.matricula?: "",
            turmaId = aluno.turmaAtualId?: "",
            ""
        )
    }

    override suspend fun add(idAluno: String): String {

        Log.d(TAG, "Adicionando novo Aluno: $idAluno")

        val aluno = Aluno(
            id = idAluno,
            nome = "Estudante"
        )

        dao.insert(aluno)

        return aluno.id
    }

    override suspend fun post(alunoId: String, nome: String): Boolean {

        Log.d(TAG, "Atualizando os dados do Aluno: $nome")

        val aluno = Aluno(
            id = alunoId,
            nome = nome
        )

        dao.update(aluno)

        return true
    }

    override suspend fun buscarIdAluno(): String {

        Log.d(TAG, "Buscando Id do Aluno")

        return dao.buscarIdAluno()
    }

    override suspend fun atualizarTurma(turmaId: String): Boolean {

        Log.d(TAG, "Atualizando a Turma Atual para $turmaId")

        dao.atualizarTurmaAtual(turmaId)

        return true
    }

    companion object {
        private const val TAG = "AlunoRepository"
    }
}