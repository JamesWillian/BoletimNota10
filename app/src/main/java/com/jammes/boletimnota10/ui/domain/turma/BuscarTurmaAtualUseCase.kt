package com.jammes.boletimnota10.ui.domain.turma

import com.jammes.boletimnota10.ui.model.TurmaItem

interface BuscarTurmaAtualUseCase {

    suspend operator fun invoke(): TurmaItem
}