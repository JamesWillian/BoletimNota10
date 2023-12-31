package com.jammes.boletimnota10.ui.domain.turma

import com.jammes.boletimnota10.ui.model.TurmaItem

interface BuscarTurmaPorIdUseCase {

    suspend operator fun invoke(turmaId: String): TurmaItem
}