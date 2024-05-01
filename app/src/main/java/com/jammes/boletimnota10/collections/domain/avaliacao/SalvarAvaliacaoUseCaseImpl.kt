package com.jammes.boletimnota10.collections.domain.avaliacao

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.AvaliacaoRepository
import javax.inject.Inject

class SalvarAvaliacaoUseCaseImpl @Inject constructor(
    private val avaliacaoRepository: AvaliacaoRepository
) : SalvarAvaliacaoUseCase {

    override suspend fun invoke(
        avaliacaoId: String?,
        moduloId: String,
        descricao: String,
        nota: Float,
        data: String,
        recuperacao: Boolean
    ): Boolean {
        Log.d(TAG, "Inserindo nova Avaliação: $descricao")

        if (avaliacaoId.isNullOrEmpty()) {
            avaliacaoRepository.add(
                moduloId,
                descricao,
                nota,
                data,
                recuperacao
            )
        } else {
            avaliacaoRepository.editar(
                avaliacaoId,
                moduloId,
                descricao,
                nota,
                data,
                recuperacao
            )
        }

        return true
    }

    companion object {
        private const val TAG = "InserirAvaliacao"
    }
}