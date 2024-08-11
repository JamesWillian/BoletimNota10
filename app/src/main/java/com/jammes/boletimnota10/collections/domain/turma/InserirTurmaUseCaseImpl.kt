package com.jammes.boletimnota10.collections.domain.turma

import android.util.Log
import com.jammes.boletimnota10.core.repository.api.TurmaApiService
import com.jammes.boletimnota10.core.repository.api.params.TurmaBody
import com.jammes.boletimnota10.core.repository.room.TurmaRepository
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class InserirTurmaUseCaseImpl @Inject constructor(
    private val turmaRepository: TurmaRepository,
    private val turmaApiService: TurmaApiService
): InserirTurmaUseCase {

    override suspend fun invoke(
        nome: String,
        escola: String,
        turno: String,
        ano: String,
        dataInicio: String,
        alunoId: String
    ): String {
        Log.d(TAG, "Gravando nova Turma: $nome")

        val turma = TurmaBody(
            nome = nome,
            escola = escola,
            turno = turno,
            ano = ano,
            dataInicio =  SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dataInicio)!!,
            alunoId = alunoId
        )

        val response = turmaApiService.criarTurma(turma)

        return if (response.isSuccessful) {
            turmaRepository.add(nome, escola, turno, ano, dataInicio)
        } else {
            ""
        }

    }

    companion object {
        private const val TAG = "InserirTurma"
    }
}