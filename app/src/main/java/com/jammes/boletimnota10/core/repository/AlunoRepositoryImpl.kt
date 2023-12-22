package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.AlunoDao
import com.jammes.boletimnota10.core.database.entity.Aluno
import com.jammes.boletimnota10.core.model.AlunoDomain
import java.util.UUID
import javax.inject.Inject

class AlunoRepositoryImpl @Inject constructor(
    private val dao: AlunoDao
): AlunoRepository {

    override suspend fun fetch(): AlunoDomain {
        Log.d(TAG, "Buscando Aluno")
        val aluno = dao.fetchAluno()
        return AlunoDomain(
            id = aluno.uuid,
            nome = aluno.nome
        )
    }

    override suspend fun add(nome: String) {
        Log.d(TAG, "Adicionando novo Aluno: $nome")
        val aluno = Aluno(
            uuid = UUID.randomUUID().toString(),
            nome = nome
        )
        dao.insert(aluno)
    }

    override suspend fun post(alunoId: String, nome: String) {
        Log.d(TAG, "Atualizando os dados do Aluno: $nome")
        val aluno = Aluno(
            uuid = alunoId,
            nome = nome
        )
        dao.update(aluno)
    }

    companion object {
        private const val TAG = "AlunoRepository"
    }
}