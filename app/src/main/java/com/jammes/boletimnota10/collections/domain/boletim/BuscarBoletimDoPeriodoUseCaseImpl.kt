package com.jammes.boletimnota10.collections.domain.boletim

import android.util.Log
import com.jammes.boletimnota10.collections.model.BoletimItem
import com.jammes.boletimnota10.core.repository.BoletimRepository
import javax.inject.Inject

class BuscarBoletimDoPeriodoUseCaseImpl @Inject constructor(
    private val boletimRepository: BoletimRepository,
): BuscarBoletimDoPeriodoUseCase {

    override suspend fun invoke(periodoId: String): List<BoletimItem> {

        Log.d(TAG, "Listando o Boletim do Período: $periodoId")

        return boletimRepository
            .buscarBoletimDoPeriodo(periodoId)
            .map {

                BoletimItem(
                    moduloId = it.moduloId,
                    periodoId = it.periodoId,
                    modulo = it.modulo,
                    notaTotal = it.notaTotal.toString(),
                    professor = it.professor
                )
            }
    }

    companion object {
        private const val TAG = "BuscarBoletimDoPeriodo"
    }
}