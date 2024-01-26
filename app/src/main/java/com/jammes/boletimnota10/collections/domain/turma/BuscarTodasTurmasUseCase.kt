package com.jammes.boletimnota10.collections.domain.turma

import com.jammes.boletimnota10.collections.model.TurmaItem

interface BuscarTodasTurmasUseCase {

    suspend operator fun invoke(): List<TurmaItem>
}