package com.jammes.boletimnota10.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jammes.boletimnota10.core.database.dao.AnoSerieDao
import com.jammes.boletimnota10.core.database.dao.DisciplinaDao
import com.jammes.boletimnota10.core.database.dao.EscolaDao
import com.jammes.boletimnota10.core.database.dao.ProfessorDao
import com.jammes.boletimnota10.core.database.dao.TipoAtividadeDao
import com.jammes.boletimnota10.core.database.dao.UnidadeDao
import com.jammes.boletimnota10.core.database.entity.AnoSerie
import com.jammes.boletimnota10.core.database.entity.Disciplina
import com.jammes.boletimnota10.core.database.entity.Escola
import com.jammes.boletimnota10.core.database.entity.Professor
import com.jammes.boletimnota10.core.database.entity.TipoAtividade
import com.jammes.boletimnota10.core.database.entity.Unidade

@Database(
    entities = [Escola::class, AnoSerie::class, Disciplina::class, Professor::class,
        TipoAtividade::class, Unidade::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun escolaDao(): EscolaDao
    abstract fun anoSerieDao(): AnoSerieDao
    abstract fun disciplinaDao(): DisciplinaDao
    abstract fun professorDao(): ProfessorDao
    abstract fun tipoAtividadeDao(): TipoAtividadeDao
    abstract fun unidadeDao(): UnidadeDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                        .build()
                }
            }
            return instance!!
        }

        private const val DATABASE_NAME = "app-database.db"

    }
}