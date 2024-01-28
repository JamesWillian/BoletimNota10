package com.jammes.boletimnota10.collections.domain.periodo

import com.jammes.boletimnota10.collections.model.PeriodoItem

interface BuscarPeriodosDaTurmaUseCase {

    suspend operator fun invoke(turmaId: String): List<PeriodoItem>
}