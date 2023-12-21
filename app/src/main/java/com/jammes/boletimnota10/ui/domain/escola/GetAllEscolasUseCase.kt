package com.jammes.boletimnota10.ui.domain.escola

import com.jammes.boletimnota10.ui.model.EscolaItem

interface GetAllEscolasUseCase {

    suspend operator fun invoke(): List<EscolaItem>
}