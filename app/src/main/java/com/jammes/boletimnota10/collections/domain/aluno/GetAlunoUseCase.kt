package com.jammes.boletimnota10.collections.domain.aluno

import com.jammes.boletimnota10.collections.model.AlunoItem

interface GetAlunoUseCase {

    suspend operator fun invoke(): AlunoItem
}