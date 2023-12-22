package com.jammes.boletimnota10.ui.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.ui.model.TurmaItem
import javax.inject.Inject

class GetAllTurmasUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): GetAllTurmasUseCase {

    override suspend fun invoke(): List<TurmaItem> {

        Log.d(TAG, "Listando todas as Turmas")

        return turmaRepository
            .fetchAll()
            .map {
                TurmaItem(
                    id = it.id,
                    nome = it.nome,
                    periodo = it.periodo
                )
            }
    }

    companion object {
        private const val TAG = "GetAllTurmas"
    }
}