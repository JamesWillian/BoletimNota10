package com.jammes.boletimnota10.collections.domain.aluno

import com.jammes.boletimnota10.core.repository.room.AlunoRepository
import javax.inject.Inject

class BuscarIdAlunoUseCaseImpl @Inject constructor(
    private val alunoRepository: AlunoRepository
): BuscarIdAlunoUseCase {

    override suspend fun invoke(): String {
        return alunoRepository.buscarIdAluno()
    }

}