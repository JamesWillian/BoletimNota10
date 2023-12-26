package com.jammes.boletimnota10.ui.domain.turma_disciplina

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaDisciplinaRepository
import javax.inject.Inject

class InsertTurmaDisciplinaUseCaseImpl @Inject constructor(
    private val turmaDisciplinaRepository: TurmaDisciplinaRepository
): InsertTurmaDisciplinaUseCase {
    override suspend fun invoke(turmaId: String, disciplinaId: String): Boolean {

        Log.d(TAG, "Gravando nova Disciplina na Turma: $disciplinaId")

        turmaDisciplinaRepository.add(turmaId, disciplinaId)

        return true
    }

    companion object {
        private const val TAG = "InsertTurmaDisciplina"
    }
}