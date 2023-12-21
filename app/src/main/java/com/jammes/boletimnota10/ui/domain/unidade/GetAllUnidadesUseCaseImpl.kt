package com.jammes.boletimnota10.ui.domain.unidade

import android.util.Log
import com.jammes.boletimnota10.core.repository.UnidadeRepository
import com.jammes.boletimnota10.ui.model.UnidadeItem
import javax.inject.Inject

class GetAllUnidadesUseCaseImpl @Inject constructor(
    private val unidadeRepository: UnidadeRepository
): GetAllUnidadesUseCase {

    override suspend fun invoke(): List<UnidadeItem> {

        Log.d(TAG, "Listando todas as Unidades")

        return unidadeRepository
            .fetchAll()
            .map {
                UnidadeItem(
                    id = it.id,
                    descricao = it.descricao
                )
            }
    }

    companion object {
        private const val TAG = "GetAllUnidades"
    }
}