package com.jammes.boletimnota10.collections.domain.boletim

import android.util.Log
import com.jammes.boletimnota10.collections.model.BoletimItem
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.ModuloRepository
import javax.inject.Inject

class BuscarBoletimDoPeriodoUseCaseImpl @Inject constructor(
    private val moduloRepository: ModuloRepository,
    private val disciplinaRepository: DisciplinaRepository
): BuscarBoletimDoPeriodoUseCase {

    override suspend fun invoke(periodoId: String): List<BoletimItem> {

        Log.d(TAG, "Listando o Boletim do Período: $periodoId")

        return moduloRepository
            .fetchAll(periodoId)
            .map {

                val disciplina = disciplinaRepository.fetchById(it.disciplinaId!!)

                BoletimItem(
                    id = it.id!!,
                    periodoId = it.periodoId!!,
                    disciplina = disciplina.descricao,
                    "", ""
                )
            }
    }

    companion object {
        private const val TAG = "BuscarBoletimDoPeriodo"
    }
}