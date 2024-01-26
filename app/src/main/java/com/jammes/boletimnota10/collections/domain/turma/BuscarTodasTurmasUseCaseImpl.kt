package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.collections.model.TurmaItem
import javax.inject.Inject

class BuscarTodasTurmasUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository
): BuscarTodasTurmasUseCase {

    override suspend fun invoke(): List<TurmaItem> {

        Log.d(TAG, "Obtendo Todas as Turmas")

        return turmaRepository.buscarTodasTurmas().map {turma ->
            TurmaItem(
                id = turma.id,
                nome = turma.nome,
                escola = turma.escola,
                turno = turma.turno,
                ano = turma.ano,
                dataInicio = turma.dataInicio,
                dataFinal = turma.dataFinal,
                concluido = turma.concluido
            )
        }
    }

    companion object {
        private const val TAG = "BuscarTodasTurmas"
    }
}