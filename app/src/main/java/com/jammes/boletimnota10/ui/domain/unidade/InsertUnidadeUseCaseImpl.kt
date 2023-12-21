package com.jammes.boletimnota10.ui.domain.unidade

import android.util.Log
import com.jammes.boletimnota10.core.repository.UnidadeRepository
import javax.inject.Inject

class InsertUnidadeUseCaseImpl @Inject constructor(
    private val unidadeRepository: UnidadeRepository
): InsertUnidadeUseCase {

    override suspend fun invoke(descricao: String): Boolean {

        Log.d(TAG, "Gravando nova Unidade: $descricao")

        unidadeRepository.add(descricao)

        return true
    }

    companion object {
        private const val TAG = "InsertUnidade"
    }
}