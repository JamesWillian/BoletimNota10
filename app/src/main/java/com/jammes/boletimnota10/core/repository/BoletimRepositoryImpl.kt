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

    companion object {
        private const val TAG = "BoletimRepository"
    }
}