package com.jammes.boletimnota10.collections.domain.modulo

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.room.ModuloRepository
import com.jammes.boletimnota10.collections.model.ModuloItem
import javax.inject.Inject

class BuscarModulosDoPeriodoUseCaseImpl @Inject constructor(
    private val moduloRepository: ModuloRepository,
    private val disciplinaRepository: DisciplinaRepository
): BuscarModulosDoPeriodoUseCase {

    override suspend fun invoke(periodoId: String): List<ModuloItem> {

        Log.d(TAG, "Listando todos os Módulos do Período: $periodoId")

        val modulos = moduloRepository
            .fetchAll(periodoId)

        return disciplinaRepository
            .fetchAll()
            .map {disciplina ->

                //Encontra o id do módulo da disciplina atual
                val moduloId = modulos.find { modulo ->
                    modulo.disciplinaId == disciplina.id
                }?.id

                ModuloItem(
                    id = moduloId ?: "",
                    periodoId = periodoId,
                    disciplinaId = disciplina.id,
                    disciplina = disciplina.descricao,
                    marcado = moduloId != null
                )

            }

    }

    companion object {
        private const val TAG = "BuscarModulosDoPeriodo"
    }
}