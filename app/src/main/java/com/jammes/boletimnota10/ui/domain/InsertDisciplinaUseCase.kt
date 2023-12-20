package com.jammes.boletimnota10.ui.domain

interface InsertDisciplinaUseCase {

    suspend operator fun invoke(descricao: String): Boolean
}