package com.jammes.boletimnota10.collections.domain.avaliacao

import com.jammes.boletimnota10.collections.model.AvaliacaoItem

interface BuscarTodasAvaliacoesUseCase {

    suspend operator fun invoke(turmaId: String, disciplinaId: String): List<AvaliacaoItem>
}