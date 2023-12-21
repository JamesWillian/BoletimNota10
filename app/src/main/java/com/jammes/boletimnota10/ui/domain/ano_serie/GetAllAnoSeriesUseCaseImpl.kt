package com.jammes.boletimnota10.ui.domain.ano_serie

import android.util.Log
import com.jammes.boletimnota10.core.repository.AnoSerieRepository
import com.jammes.boletimnota10.ui.model.AnoSerieItem
import javax.inject.Inject

class GetAllAnoSeriesUseCaseImpl @Inject constructor(
    private val anoSerieRepository: AnoSerieRepository
): GetAllAnoSeriesUseCase {

    override suspend fun invoke(): List<AnoSerieItem> {

        Log.d(TAG, "Listando todos os Anos/Séries")

        return anoSerieRepository
            .fetchAll()
            .map {
                AnoSerieItem(
                    id = it.id,
                    descricao = it.descricao
                )
            }
    }

    companion object {
        private const val TAG = "GetAllAnoSeries"
    }
}