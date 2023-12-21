package com.jammes.boletimnota10.ui.domain.escola

import android.util.Log
import com.jammes.boletimnota10.core.repository.EscolaRepository
import javax.inject.Inject

class InsertEscolaUseCaseImpl @Inject constructor(
    private val escolaRepository: EscolaRepository
): InsertEscolaUseCase {

    override suspend fun invoke(nome: String): Boolean {
        Log.d(TAG, "Gravando nova Escola: $nome")

        escolaRepository.add(nome)

        return true
    }

    companion object {
        private const val TAG = "InsertEscola"
    }
}