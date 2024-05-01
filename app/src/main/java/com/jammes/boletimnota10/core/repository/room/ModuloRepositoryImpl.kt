package com.jammes.boletimnota10.core.repository.room

import android.util.Log
import com.jammes.boletimnota10.core.database.dao.ModuloDao
import com.jammes.boletimnota10.core.database.entity.Modulo
import com.jammes.boletimnota10.core.model.ModuloDomain
import java.util.UUID
import javax.inject.Inject

class ModuloRepositoryImpl @Inject constructor(
    private val dao: ModuloDao
): ModuloRepository {

    override suspend fun fetchAll(periodoId: String): List<ModuloDomain> {
        Log.d(TAG, "Listando todas os Modulos do Periodo: $periodoId")
        return dao.buscarTodosModulosDoPeriodo(periodoId).map {
            ModuloDomain(
                id = it.uuid,
                periodoId = it.periodoId,
                disciplinaId = it.disciplinaId
            )
        }
    }

    override suspend fun fetch(periodoId: String, disciplinaId: String): ModuloDomain? {
        Log.d(TAG, "Buscando a disciplina $disciplinaId no período $periodoId")

        val modulo = dao.buscarModulo(periodoId, disciplinaId)
        return if (modulo != null) {
            ModuloDomain(
                id = modulo.uuid,
                periodoId = modulo.periodoId,
                disciplinaId = modulo.disciplinaId
            )
        } else null

    }

    override suspend fun add(periodoId: String, disciplinaId: String) {
        Log.d(TAG, "Adicionando novo Modulo $disciplinaId do Periodo: $periodoId")
        val modulo = Modulo(
            uuid = UUID.randomUUID().toString(),
            periodoId = periodoId,
            disciplinaId = disciplinaId
        )
        dao.inserir(modulo)
    }

    override suspend fun delete(moduloId: String) {
        Log.d(TAG, "Excluindo o Modulo: $moduloId")
        val modulo = Modulo(
            uuid = moduloId,
            periodoId = "",
            disciplinaId = ""
        )
        dao.delete(modulo)
    }

    companion object {
        private const val TAG = "ModuloRepository"
    }
}