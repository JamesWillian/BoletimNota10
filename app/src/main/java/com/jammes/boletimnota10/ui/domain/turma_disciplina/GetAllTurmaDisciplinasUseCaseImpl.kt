package com.jammes.boletimnota10.ui.domain.turma_disciplina

import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.TurmaDisciplinaRepository
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import javax.inject.Inject

class GetAllTurmaDisciplinasUseCaseImpl @Inject constructor(
    private val turmaDisciplinaRepository: TurmaDisciplinaRepository,
    private val disciplinaRepository: DisciplinaRepository
): GetAllTurmaDisciplinasUseCase {

    override suspend fun invoke(turmaId: String): List<TurmaDisciplinaItem> {

        Log.d(TAG, "Listando todas as Disciplinas da Turma")

        return turmaDisciplinaRepository
            .fetchAll(turmaId)
            .map {

                val disciplina = disciplinaRepository.fetchById(it.disciplinaId)

                TurmaDisciplinaItem(
                    id = it.id,
                    disciplina = disciplina.descricao
                )
            }
    }

    companion object {
        private const val TAG = "GetAllTurmaDisciplinas"
    }
}