package com.jammes.boletimnota10.collections.domain.modulo

import android.annotation.SuppressLint
import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.ModuloRepository
import com.jammes.boletimnota10.collections.model.ModuloItem
import javax.inject.Inject

class BuscarModulosDoPeriodoUseCaseImpl @Inject constructor(
    private val moduloRepository: ModuloRepository,
    private val disciplinaRepository: DisciplinaRepository
): BuscarModulosDoPeriodoUseCase {

    override suspend fun invoke(periodoId: String): List<ModuloItem> {

        Log.d(TAG, "Listando todos os Módulos do Período: $periodoId")

        return moduloRepository
            .fetchAll(periodoId)
            .map {

                val disciplina = disciplinaRepository.fetchById(it.disciplinaId)

                ModuloItem(
                    id = it.id,
                    periodoId = it.periodoId,
                    disciplina = disciplina.descricao
                )
            }
    }

    companion object {
        private const val TAG = "BuscarModulosDoPeriodo"
    }
}