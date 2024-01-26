package com.jammes.boletimnota10.collections.domain.turma

import com.jammes.boletimnota10.collections.model.TurmaItem

interface BuscarTurmaAtualUseCase {

    suspend operator fun invoke(): TurmaItem
}