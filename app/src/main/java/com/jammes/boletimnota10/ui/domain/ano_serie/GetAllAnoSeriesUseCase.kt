package com.jammes.boletimnota10.ui.domain.ano_serie

import com.jammes.boletimnota10.ui.model.AnoSerieItem

interface GetAllAnoSeriesUseCase {

    suspend operator fun invoke(): List<AnoSerieItem>
}