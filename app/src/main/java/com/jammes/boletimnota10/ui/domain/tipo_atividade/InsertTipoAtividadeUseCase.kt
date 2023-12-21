package com.jammes.boletimnota10.ui.domain.tipo_atividade

interface InsertTipoAtividadeUseCase {

    suspend operator fun invoke(descricao: String): Boolean
}