package com.jammes.boletimnota10.collections.domain.boletim

import android.util.Log
import com.jammes.boletimnota10.collections.model.BoletimItem
import com.jammes.boletimnota10.core.repository.room.BoletimRepository
import javax.inject.Inject

class BuscarBoletimDoModuloUseCaseImpl @Inject constructor(
    private val boletimRepository: BoletimRepository
) : BuscarBoletimDoModuloUseCase {

    override suspend fun invoke(moduloId: String): BoletimItem? {

        Log.d(TAG, "Buscando o Boletim do Módulo: $moduloId")

        val boletim = boletimRepository.buscarBoletimDoModulo(moduloId)

        return if (boletim != null) {
            BoletimItem(
                moduloId = boletim.moduloId,
                periodoId = boletim.periodoId,
                modulo = boletim.modulo,
                notaTotal = boletim.notaTotal.toString(),
                professor = boletim.professor
            )
        } else null
    }

    companion object {
        private const val TAG = "BuscarBoletimDoModulo"
    }

}