package com.jammes.boletimnota10.collections.domain.aluno

import android.util.Log
import com.jammes.boletimnota10.core.repository.room.AlunoRepository
import com.jammes.boletimnota10.collections.model.AlunoItem
import javax.inject.Inject

class GetAlunoUseCaseImpl @Inject constructor(
    private val alunoRepository: AlunoRepository
): GetAlunoUseCase {

    override suspend fun invoke(): AlunoItem {

        Log.d(TAG, "Buscando dados do Aluno")

        val aluno = alunoRepository.fetch()
        return AlunoItem(
            id = aluno.id,
            nome = aluno.nome,
            matricula = aluno.matricula,
            turmaId = aluno.turmaId
        )

    }

    companion object {
        private const val TAG = "GetAluno"
    }
}