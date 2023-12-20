package com.jammes.boletimnota10.ui.domain

import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.ui.model.DisciplinaItem

class GetAllDisciplinasUseCaseImpl(
    private val disciplinaRepository: DisciplinaRepository
): GetAllDisciplinasUseCase {

    override suspend fun invoke(): List<DisciplinaItem> {

        Log.d(TAG, "Listando todas Disciplinas")

        return disciplinaRepository
            .fetchAll()
            .map {
                DisciplinaItem(
                    id = it.id,
                    descricao = it.descricao
                )
            }
    }

    companion object {
        private const val TAG = "GetAllDiciplinas"
    }
}