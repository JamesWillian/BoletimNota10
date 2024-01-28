package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.AvaliacaoDao
import com.jammes.boletimnota10.core.database.entity.Disciplina
import com.jammes.boletimnota10.core.database.entity.Avaliacao
import com.jammes.boletimnota10.core.model.AvaliacaoDomain
import java.util.UUID
import javax.inject.Inject

class AvaliacaoRepositoryImpl @Inject constructor(
    private val dao: AvaliacaoDao
) : AvaliacaoRepository {

    override suspend fun buscarTodasAvaliacoes(
        moduloId: String
    ): List<AvaliacaoDomain> {
        Log.d(TAG, "Listando todas as Avaliações do Módulo: $moduloId")
        return dao.buscarAvaliacoes(moduloId).map {
            AvaliacaoDomain(
                id = it.uuid,
                moduloId = it.moduloId,
                descricao = it.descricao,
                nota = it.nota,
                data = it.data,
                recuperacao = it.recuperacao
            )
        }
    }

    override suspend fun add(
        moduloId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ) {
        Log.d(TAG, "Adicionando nova Avaliação: $descricao")
        val avaliacao = Avaliacao(
            uuid = UUID.randomUUID().toString(),
            moduloId = moduloId,
            descricao = descricao,
            nota = nota,
            data = data,
            recuperacao = recuperacao
        )
        dao.insert(avaliacao)
    }

    companion object {
        private const val TAG = "AvaliacaoRepository"
    }
}