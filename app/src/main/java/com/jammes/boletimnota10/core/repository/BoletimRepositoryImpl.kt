package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.BoletimDao
import com.jammes.boletimnota10.core.model.BoletimDomain
import javax.inject.Inject

class BoletimRepositoryImpl @Inject constructor(
    private val dao: BoletimDao
) : BoletimRepository {

    override suspend fun buscarBoletimDoPeriodo(periodoId: String): List<BoletimDomain> {

        Log.d(TAG, "Listando o Boletim do Período: $periodoId")
        return dao.buscarBoletim(periodoId).map {
            BoletimDomain(
                moduloId = it.moduloId,
                periodoId = it.periodoId,
                modulo = it.modulo,
                professor = it.professor,
                notaTotal = it.notaTotal
            )
        }
    }

    override suspend fun buscarBoletimDoModulo(moduloId: String): BoletimDomain? {

        Log.d(TAG, "Buscando o Boletim do Módulo: $moduloId")
        val boletim = dao.buscarBoletimDoModulo(moduloId)

        return if (boletim != null) {
            BoletimDomain(
                moduloId = boletim.moduloId,
                periodoId = boletim.periodoId,
                modulo = boletim.modulo,
                professor = boletim.professor,
                notaTotal = boletim.notaTotal
            )
        } else null
    }

    companion object {
        private const val TAG = "BoletimRepository"
    }
}