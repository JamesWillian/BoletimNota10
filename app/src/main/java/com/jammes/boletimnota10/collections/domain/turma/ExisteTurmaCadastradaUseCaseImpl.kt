package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import javax.inject.Inject

class ExisteTurmaCadastradaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): ExisteTurmaCadastradaUseCase {
    override suspend fun invoke(): Boolean {
        Log.d(TAG, "Verificando se Existe Turma Cadastrada")

        return turmaRepository.existeTurmaCadastrada()
    }

    companion object {
        private const val TAG = "ExisteTurmaCadastrada"
    }
}