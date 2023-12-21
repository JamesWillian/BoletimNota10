package com.jammes.boletimnota10.ui.domain.unidade

import com.jammes.boletimnota10.ui.model.UnidadeItem

interface GetAllUnidadesUseCase {

    suspend operator fun invoke(): List<UnidadeItem>
}