package com.jammes.boletimnota10.ui.domain.professor

import android.util.Log
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.ProfessorRepository
import com.jammes.boletimnota10.ui.model.ProfessorItem
import javax.inject.Inject

class GetAllProfessoresUseCaseImpl @Inject constructor(
    private val professorRepository: ProfessorRepository,
    private val disciplinaRepository: DisciplinaRepository
): GetAllProfessoresUseCase {

    override suspend fun invoke(): List<ProfessorItem> {
        Log.d(TAG, "Listando todos os Professores")

        return professorRepository
            .fetchAll()
            .map {

                val disciplina = disciplinaRepository.fetchById(
                    it.disciplina.uuid
                )

                ProfessorItem(
                    id = it.id,
                    nome = it.nome,
                    disciplina = disciplina.descricao
                )
            }
    }

    companion object {
        private const val TAG = "GetAllAnoSeries"
    }
}