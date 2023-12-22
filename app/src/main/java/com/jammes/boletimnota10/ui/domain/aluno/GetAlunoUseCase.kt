package com.jammes.boletimnota10.ui.domain.aluno

import com.jammes.boletimnota10.ui.model.AlunoItem

interface GetAlunoUseCase {

    suspend operator fun invoke(): AlunoItem
}