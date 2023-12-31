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
        turmaId: String,
        disciplinaId: String
    ): List<AvaliacaoDomain> {
        Log.d(TAG, "Listando todas as Avaliações da Disciplina: $disciplinaId")
        return dao.buscarAvaliacoes(turmaId, disciplinaId).map {
            AvaliacaoDomain(
                id = it.uuid,
                turmaId = it.turmaId,
                disciplinaId = it.disciplinaId,
                descricao = it.descricao,
                nota = it.nota,
                data = it.data,
                recuperacao = it.recuperacao
            )
        }
    }

    override suspend fun add(
        turmaId: String,
        disciplinaId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ) {
        Log.d(TAG, "Adicionando nova Avaliação: $descricao")
        val avaliacao = Avaliacao(
            uuid = UUID.randomUUID().toString(),
            turmaId = turmaId,
            disciplinaId = disciplinaId,
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