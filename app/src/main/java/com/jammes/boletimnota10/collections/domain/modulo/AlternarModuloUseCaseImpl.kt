package com.jammes.boletimnota10.collections.domain.modulo

import android.util.Log
import com.jammes.boletimnota10.core.repository.ModuloRepository
import javax.inject.Inject

class AlternarModuloUseCaseImpl @Inject constructor(
    private val moduloRepository: ModuloRepository
): AlternarModuloUseCase {

    override suspend fun invoke(periodoId: String, disciplinaId: String): Boolean {

        Log.d(TAG, "Checando o Módulo da Disciplina $disciplinaId no Periodo $periodoId para deletar ou inserir")

        val modulo = moduloRepository.fetch(periodoId, disciplinaId)

        if (modulo != null) {
            moduloRepository.delete(modulo.id!!)
        } else {
            moduloRepository.add(periodoId, disciplinaId)
        }

        return true
    }

    companion object {
        private const val TAG = "AlternarModulo"
    }
}