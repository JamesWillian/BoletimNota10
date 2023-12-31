package com.jammes.boletimnota10.core.repository

import com.jammes.boletimnota10.core.model.AvaliacaoDomain

interface AvaliacaoRepository {

    suspend fun buscarTodasAvaliacoes(turmaId: String, disciplinaId: String): List<AvaliacaoDomain>

    suspend fun add(turmaId: String, disciplinaId: String, descricao: String, nota: Float, data: String, recuperacao: Boolean)

}