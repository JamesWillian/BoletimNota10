package com.jammes.boletimnota10.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jammes.boletimnota10.core.database.dao.AlunoDao
import com.jammes.boletimnota10.core.database.dao.DisciplinaDao
import com.jammes.boletimnota10.core.database.dao.TurmaDao
import com.jammes.boletimnota10.core.database.dao.AvaliacaoDao
import com.jammes.boletimnota10.core.database.dao.BoletimDao
import com.jammes.boletimnota10.core.database.dao.PeriodoDao
import com.jammes.boletimnota10.core.database.dao.ModuloDao
import com.jammes.boletimnota10.core.database.entity.Aluno
import com.jammes.boletimnota10.core.database.entity.Disciplina
import com.jammes.boletimnota10.core.database.entity.Turma
import com.jammes.boletimnota10.core.database.entity.Avaliacao
import com.jammes.boletimnota10.core.database.entity.Boletim
import com.jammes.boletimnota10.core.database.entity.Periodo
import com.jammes.boletimnota10.core.database.entity.Modulo

@Database(
    entities = [
        Aluno::class,
        Disciplina::class,
        Turma::class,
        Periodo::class,
        Modulo::class,
        Avaliacao::class,],
    views = [
        Boletim::class
            ],
    version = 9,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun AlunoDao(): AlunoDao
    abstract fun disciplinaDao(): DisciplinaDao
    abstract fun turmaDao(): TurmaDao
    abstract fun periodoDao(): PeriodoDao
    abstract fun moduloDao(): ModuloDao
    abstract fun avaliacaoDao(): AvaliacaoDao
    abstract fun boletimDao(): BoletimDao

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
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

        private const val DATABASE_NAME = "app-database.db"

    }
}