package com.jammes.boletimnota10.collections.domain.periodo

import android.util.Log
import com.jammes.boletimnota10.collections.domain.modulo.InserirModuloUseCaseImpl
import com.jammes.boletimnota10.core.repository.PeriodoRepository
import javax.inject.Inject

class InserirPeriodoUseCaseImpl @Inject constructor(
    private val periodoRepository: PeriodoRepository
) : InserirPeriodoUseCase {

    override suspend fun invoke(periodo: String, turmaId: String): String {

        Log.d(TAG, "Gravando novo Período $periodo da Turma: $turmaId")

        return periodoRepository.add(periodo, turmaId)
    }

    companion object {
        private const val TAG = "InserirPeriodo"
    }

}