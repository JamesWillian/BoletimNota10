package com.jammes.boletimnota10.ui.domain.tipo_atividade

import com.jammes.boletimnota10.ui.model.TipoAtividadeItem

interface GetAllTiposAtividadesUseCase {

    suspend operator fun invoke(): List<TipoAtividadeItem>
}