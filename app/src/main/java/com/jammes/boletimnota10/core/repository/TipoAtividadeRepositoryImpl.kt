package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.TipoAtividadeDao
import com.jammes.boletimnota10.core.database.entity.TipoAtividade
import com.jammes.boletimnota10.core.model.TipoAtividadeDomain
import java.util.UUID
import javax.inject.Inject

class TipoAtividadeRepositoryImpl @Inject constructor(
    private val dao: TipoAtividadeDao
): TipoAtividadeRepository {

    override suspend fun fetchAll(): List<TipoAtividadeDomain> {
        Log.d(TAG, "Listando todas os Tipos de Atividades.")
        return dao.fetchTipoAtividade().map { tipo ->
            TipoAtividadeDomain(
                id = tipo.uuid,
                descricao = tipo.descricao
            )
        }
    }

    override suspend fun add(descricao: String) {
        Log.d(TAG, "Adicionando novo Tipo de Atividade: $descricao")
        val tipoAtividade = TipoAtividade(
            uuid = UUID.randomUUID().toString(),
            descricao = descricao
        )
        dao.insert(tipoAtividade)
    }

    override suspend fun post(tipoAtividadeId: String, descricao: String) {
        Log.d(TAG, "Atualizando o Tipo de Atividade: $descricao")
        val tipoAtividade = TipoAtividade(
            uuid = tipoAtividadeId,
            descricao = descricao
        )
        dao.update(tipoAtividade)
    }

    override suspend fun delete(tipoAtividadeId: String) {
        Log.d(TAG, "Excluindo o Tipo de Atividade: $tipoAtividadeId")
        val tipoAtividade = TipoAtividade(
            uuid = tipoAtividadeId,
            descricao = ""
        )
        dao.delete(tipoAtividade)
    }

    companion object {
        private const val TAG = "TipoAtividadeRepository"
    }
}