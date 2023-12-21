package com.jammes.boletimnota10.ui.domain.unidade

interface InsertUnidadeUseCase {

    suspend operator fun invoke(descricao: String): Boolean
}