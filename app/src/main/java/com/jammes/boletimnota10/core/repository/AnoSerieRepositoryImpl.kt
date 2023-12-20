package com.jammes.boletimnota10.core.repository

import android.util.Log
import com.jammes.boletimnota10.core.database.AppDatabase
import com.jammes.boletimnota10.core.database.entity.AnoSerie
import com.jammes.boletimnota10.core.model.AnoSerieDomain
import java.util.UUID

class AnoSerieRepositoryImpl(appDatabase: AppDatabase): AnoSerieRepository {

    private val dao = appDatabase.anoSerieDao()

    override suspend fun fetchAll(): List<AnoSerieDomain> {
        Log.d(TAG, "Listando todas as Séries.")
        return dao.fetchAnoSerie().map { serie ->
            AnoSerieDomain(
                id = serie.uuid,
                descricao = serie.descricao
            )
        }
    }

    override suspend fun add(descricao: String) {
        Log.d(TAG, "Adicionando novo Ano/Série: $descricao")
        val anoSerie = AnoSerie(
            uuid = UUID.randomUUID().toString(),
            descricao = descricao
        )
        dao.insert(anoSerie)
    }

    override suspend fun post(anoSerieId: String, descricao: String) {
        Log.d(TAG, "Atualizando o Ano/Série: $descricao")
        val anoSerie = AnoSerie(
            uuid = anoSerieId,
            descricao = descricao
        )
        dao.update(anoSerie)
    }

    override suspend fun delete(anoSerieId: String) {
        Log.d(TAG, "Excluindo o Ano/Série: $anoSerieId")
        val anoSerie = AnoSerie(
            uuid = anoSerieId,
            descricao = ""
        )
        dao.delete(anoSerie)
    }

    companion object {
        private const val TAG = "AnoSerieRepository"
    }
}