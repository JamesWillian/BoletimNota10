package com.jammes.boletimnota10.collections.domain.disciplina

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.DisciplinaRepository
import com.jammes.boletimnota10.collections.model.DisciplinaItem
import javax.inject.Inject

class GetAllDisciplinasUseCaseImpl @Inject constructor(
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