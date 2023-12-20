package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.AppDatabase
import com.jammes.boletimnota10.core.database.entity.Unidade
import com.jammes.boletimnota10.core.model.UnidadeDomain
import java.util.UUID

class UnidadeRepositoryImpl(appDatabase: AppDatabase): UnidadeRepository {

    private val dao = appDatabase.unidadeDao()

    override suspend fun fetchAll(): List<UnidadeDomain> {
        Log.d(TAG, "Listando todas as Unidades.")
        return dao.fetchUnidade().map { unidade ->
            UnidadeDomain(
                id = unidade.uuid,
                descricao = unidade.descricao
            )
        }
    }

    override suspend fun add(descricao: String) {
        Log.d(TAG, "Adicionando nova Unidade: $descricao")
        val unidade = Unidade(
            uuid = UUID.randomUUID().toString(),
            descricao = descricao
        )
        dao.insert(unidade)
    }

    override suspend fun post(unidadeId: String, descricao: String) {
        Log.d(TAG, "Atualizando a Unidade: $descricao")
        val unidade = Unidade(
            uuid = unidadeId,
            descricao = descricao
        )
        dao.update(unidade)
    }

    override suspend fun delete(unidadeId: String) {
        Log.d(TAG, "Excluindo a Unidade: $unidadeId")
        val unidade = Unidade(
            uuid = unidadeId,
            descricao = ""
        )
        dao.delete(unidade)
    }

    companion object {
        private const val TAG = "UnidadeRepository"
    }

}