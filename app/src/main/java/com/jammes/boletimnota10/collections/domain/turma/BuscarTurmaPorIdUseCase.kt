package com.jammes.boletimnota10.collections.domain.turma

import com.jammes.boletimnota10.collections.model.TurmaItem

interface BuscarTurmaPorIdUseCase {

    suspend operator fun invoke(turmaId: String): TurmaItem
}