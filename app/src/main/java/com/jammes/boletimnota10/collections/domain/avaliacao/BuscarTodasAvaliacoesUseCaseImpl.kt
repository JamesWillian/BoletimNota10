package com.jammes.boletimnota10.collections.domain.avaliacao

import android.util.Log
import com.jammes.boletimnota10.core.repository.AvaliacaoRepository
import com.jammes.boletimnota10.collections.model.AvaliacaoItem
import javax.inject.Inject

class BuscarTodasAvaliacoesUseCaseImpl @Inject constructor(
    private val avaliacaoRepository: AvaliacaoRepository
): BuscarTodasAvaliacoesUseCase {

    override suspend fun invoke(turmaId: String, disciplinaId: String): List<AvaliacaoItem> {
        Log.d(TAG, "Listando todas as Avaliações da disciplina: $disciplinaId")

        return avaliacaoRepository
            .buscarTodasAvaliacoes(turmaId, disciplinaId)
            .map {

                AvaliacaoItem(
                    id = it.id,
                    turmaId = it.turmaId,
                    disciplinaId = it.disciplinaId,
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