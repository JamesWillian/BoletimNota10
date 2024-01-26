package com.jammes.boletimnota10.collections.domain.modulo

import android.util.Log
import com.jammes.boletimnota10.core.repository.ModuloRepository
import javax.inject.Inject

class InserirModuloUseCaseImpl @Inject constructor(
    private val moduloRepository: ModuloRepository
): InserirModuloUseCase {

    override suspend fun invoke(periodoId: String, disciplinaId: String): Boolean {

        Log.d(TAG, "Gravando novo Módulo: $disciplinaId no Periodo: $periodoId")

        moduloRepository.add(periodoId, disciplinaId)

        return true
    }

    companion object {
        private const val TAG = "InserirModulo"
    }
}