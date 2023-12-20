package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.AppDatabase
import com.jammes.boletimnota10.core.database.entity.Escola
import com.jammes.boletimnota10.core.model.EscolaDomain
import java.util.UUID

class EscolaRepositoryImpl(appDatabase: AppDatabase): EscolaRepository {

    private val dao = appDatabase.escolaDao()

    override suspend fun fetchAll(): List<EscolaDomain> {
        Log.d(TAG, "Listando todas as Escolas.")
        return dao.fetchEscolas().map { escola ->
            EscolaDomain(
                id = escola.uuid,
                nome = escola.nome
            )
        }
    }

    override suspend fun add(nome: String) {
        Log.d(TAG, "Adicionando nova Escola: $nome")
        val escola = Escola(
            uuid = UUID.randomUUID().toString(),
            nome = nome
        )
        dao.insert(escola)
    }

    override suspend fun post(escolaId: String, nome: String) {
        Log.d(TAG, "Atualizando a Escola: $nome")
        val escola = Escola(
            uuid = escolaId,
            nome = nome
        )
        dao.update(escola)
    }

    override suspend fun delete(escolaId: String) {
        Log.d(TAG, "Excluindo a Escola: $escolaId")
        val escola = Escola(
            uuid = escolaId,
            nome = ""
        )
        dao.delete(escola)
    }

    companion object {
        private const val TAG = "EscolaRepository"
    }
}