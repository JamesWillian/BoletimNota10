package com.jammes.boletimnota10.ui.domain.ano_serie

interface InsertAnoSerieUseCase {

    suspend operator fun invoke(descricao: String): Boolean
}