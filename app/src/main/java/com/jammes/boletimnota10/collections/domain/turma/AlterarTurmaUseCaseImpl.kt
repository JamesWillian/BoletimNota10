package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.api.TurmaApiService
import com.jammes.boletimnota10.core.repository.api.params.TurmaBody
import com.jammes.boletimnota10.core.repository.room.TurmaRepository
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class AlterarTurmaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository,
    private val turmaApiService: TurmaApiService
): AlterarTurmaUseCase {

    override suspend fun invoke(
        turmaId: String,
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        dataFinal: String,
        alunoId: String
    ) {
        Log.d(TAG, "Alterando Turma: $turmaId")

        val turma = TurmaBody(
            nome = nome,
            escola = escola,
            turno = turno,
            ano = ano,
            dataInicio = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dataInicio)!!,
            alunoId = alunoId
        )

        val response = turmaApiService.criarTurma(turma)

        if (response.isSuccessful) {
            turmaRepository.post(turmaId, nome, escola, turno, ano, dataInicio, dataFinal)
        }
    }

    companion object {
        private const val TAG = "AlterarTurma"
    }
}