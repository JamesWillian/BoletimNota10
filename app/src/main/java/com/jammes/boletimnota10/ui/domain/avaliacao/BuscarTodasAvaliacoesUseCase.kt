package com.jammes.boletimnota10.ui.domain.avaliacao

import com.jammes.boletimnota10.ui.model.AvaliacaoItem

interface BuscarTodasAvaliacoesUseCase {

    suspend operator fun invoke(turmaId: String, disciplinaId: String): List<AvaliacaoItem>
}