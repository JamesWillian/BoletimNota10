package com.jammes.boletimnota10.ui.domain.tipo_atividade

import android.util.Log
import com.jammes.boletimnota10.core.repository.TipoAtividadeRepository
import com.jammes.boletimnota10.ui.model.TipoAtividadeItem
import javax.inject.Inject

class GetAllTiposAtividadesUseCaseImpl @Inject constructor(
    private val tipoAtividadeRepository: TipoAtividadeRepository
): GetAllTiposAtividadesUseCase {

    override suspend fun invoke(): List<TipoAtividadeItem> {

        Log.d(TAG, "Listando todos os Tipos de Atividades")

        return tipoAtividadeRepository
            .fetchAll()
            .map {
                TipoAtividadeItem(
                    id = it.id,
                    descricao = it.descricao
                )
            }
    }

    companion object {
        private const val TAG = "GetAllTiposAtividades"
    }
}