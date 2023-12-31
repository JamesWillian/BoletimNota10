package com.jammes.boletimnota10.ui.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.ui.model.TurmaItem
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
                periodo = turma.periodo,
                turno = turma.turno,
                ano = turma.ano,
                concluido = turma.concluido
            )
        }
    }

    companion object {
        private const val TAG = "BuscarTodasTurmas"
    }
}