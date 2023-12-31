package com.jammes.boletimnota10.ui.domain.turma_disciplina

import android.annotation.SuppressLint
import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.TurmaDisciplinaRepository
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import javax.inject.Inject

class BuscarDisciplinasDaTurmaUseCaseImpl @Inject constructor(
    private val turmaDisciplinaRepository: TurmaDisciplinaRepository,
    private val disciplinaRepository: DisciplinaRepository
): BuscarDisciplinasDaTurmaUseCase {

    @SuppressLint("LongLogTag")
    override suspend fun invoke(turmaId: String): List<TurmaDisciplinaItem> {

        Log.d(TAG, "Listando todas as Disciplinas da Turma")

        return turmaDisciplinaRepository
            .fetchAll(turmaId)
            .map {

                val disciplina = disciplinaRepository.fetchById(it.disciplinaId)

                TurmaDisciplinaItem(
                    id = it.id,
                    turmaId = it.turmaId,
                    disciplina = disciplina.descricao
                )
            }
    }

    companion object {
        private const val TAG = "BuscarDisciplinasDaTurma"
    }
}