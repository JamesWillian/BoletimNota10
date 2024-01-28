package com.jammes.boletimnota10.collections.domain.avaliacao

import android.util.Log
import com.jammes.boletimnota10.core.repository.AvaliacaoRepository
import com.jammes.boletimnota10.collections.model.AvaliacaoItem
import javax.inject.Inject

class BuscarTodasAvaliacoesUseCaseImpl @Inject constructor(
    private val avaliacaoRepository: AvaliacaoRepository
): BuscarTodasAvaliacoesUseCase {

    override suspend fun invoke(moduloId: String): List<AvaliacaoItem> {
        Log.d(TAG, "Listando todas as Avaliações do Módulo: $moduloId")

        return avaliacaoRepository
            .buscarTodasAvaliacoes(moduloId)
            .map {

                AvaliacaoItem(
                    id = it.id,
                    moduloId = it.moduloId,
                    descricao = it.descricao,
                    nota = it.nota.toString(),
                    data = it.data,
                    recuperacao = it.recuperacao
                )
            }
    }

    companion object {
        private const val TAG = "BuscarTodasAvaliacoes"
    }
}