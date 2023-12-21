package com.jammes.boletimnota10.ui.domain.tipo_atividade

import android.util.Log
import com.jammes.boletimnota10.core.repository.TipoAtividadeRepository
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCaseImpl
import javax.inject.Inject

class InsertTipoAtividadeUseCaseImpl @Inject constructor(
    private val tipoAtividadeRepository: TipoAtividadeRepository
): InsertTipoAtividadeUseCase {
    override suspend fun invoke(descricao: String): Boolean {

        Log.d(TAG, "Gravando novo Tipo de Atividade: $descricao")

        tipoAtividadeRepository.add(descricao)

        return true
    }

    companion object {
        private const val TAG = "InsertTipoAtividade"
    }
}