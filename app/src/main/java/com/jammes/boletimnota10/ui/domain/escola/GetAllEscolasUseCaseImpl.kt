package com.jammes.boletimnota10.ui.domain.escola

import android.util.Log
import com.jammes.boletimnota10.core.repository.EscolaRepository
import com.jammes.boletimnota10.ui.model.EscolaItem
import javax.inject.Inject

class GetAllEscolasUseCaseImpl @Inject constructor(
    private val escolaRepository: EscolaRepository
): GetAllEscolasUseCase {

    override suspend fun invoke(): List<EscolaItem> {

        Log.d(TAG, "Listando todas as Escolas")

        return escolaRepository
            .fetchAll()
            .map {
                EscolaItem(
                    id = it.id,
                    nome = it.nome
                )
            }
    }

    companion object {
        private const val TAG = "GetAllEscolas"
    }
}