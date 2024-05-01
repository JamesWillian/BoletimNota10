package com.jammes.boletimnota10.core.repository.room

import com.jammes.boletimnota10.core.model.AvaliacaoDomain

interface AvaliacaoRepository {

    suspend fun buscarTodasAvaliacoes(moduloId: String): List<AvaliacaoDomain>

    suspend fun add(moduloId: String, descricao: String, nota: Float, data: String, recuperacao: Boolean)

    suspend fun editar(avaliacaoId: String, moduloId: String, descricao: String, nota: Float, data: String, recuperacao: Boolean)

}