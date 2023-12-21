package com.jammes.boletimnota10.ui.domain.ano_serie

import android.util.Log
import com.jammes.boletimnota10.core.repository.AnoSerieRepository
import javax.inject.Inject

class InsertAnoSerieUseCaseImpl @Inject constructor(
    private val anoSerieRepository: AnoSerieRepository
): InsertAnoSerieUseCase {

    override suspend fun invoke(descricao: String): Boolean {

        Log.d(TAG, "Gravando novo Ano/Série: $descricao")

        anoSerieRepository.add(descricao)

        return true
    }

    companion object {
        private const val TAG = "InsertAnoSerie"
    }
}