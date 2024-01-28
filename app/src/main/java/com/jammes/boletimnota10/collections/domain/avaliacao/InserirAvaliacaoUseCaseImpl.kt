package com.jammes.boletimnota10.collections.domain.avaliacao

import android.util.Log
import com.jammes.boletimnota10.core.repository.AvaliacaoRepository
import javax.inject.Inject

class InserirAvaliacaoUseCaseImpl @Inject constructor(
    private val avaliacaoRepository: AvaliacaoRepository
) : InserirAvaliacaoUseCase {

    override suspend fun invoke(
        moduloId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ): Boolean {
        Log.d(TAG, "Inserindo nova Avaliação: $descricao")

        avaliacaoRepository.add(
            moduloId,
            descricao,
            nota,
            data,
            recuperacao
        )

        return true
    }

    companion object {
        private const val TAG = "InserirAvaliacao"
    }
}