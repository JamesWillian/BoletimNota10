package com.jammes.boletimnota10.ui.domain.turma

import com.jammes.boletimnota10.ui.model.TurmaItem

interface BuscarTodasTurmasUseCase {

    suspend operator fun invoke(): List<TurmaItem>
}