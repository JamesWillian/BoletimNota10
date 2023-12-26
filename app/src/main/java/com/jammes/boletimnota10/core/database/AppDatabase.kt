package com.jammes.boletimnota10.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jammes.boletimnota10.core.database.dao.AlunoDao
import com.jammes.boletimnota10.core.database.dao.DisciplinaDao
import com.jammes.boletimnota10.core.database.dao.TurmaDao
import com.jammes.boletimnota10.core.database.dao.ProfessorDao
import com.jammes.boletimnota10.core.database.dao.TurmaDisciplinaDao
import com.jammes.boletimnota10.core.database.dao.UnidadeDao
import com.jammes.boletimnota10.core.database.entity.Aluno
import com.jammes.boletimnota10.core.database.entity.Disciplina
import com.jammes.boletimnota10.core.database.entity.Turma
import com.jammes.boletimnota10.core.database.entity.Professor
import com.jammes.boletimnota10.core.database.entity.TurmaDisciplina
import com.jammes.boletimnota10.core.database.entity.Unidade

@Database(
    entities = [Turma::class, Aluno::class, Disciplina::class, Professor::class,
        TurmaDisciplina::class, Unidade::class],
    version = 2,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun turmaDao(): TurmaDao
    abstract fun AlunoDao(): AlunoDao
    abstract fun disciplinaDao(): DisciplinaDao
    abstract fun professorDao(): ProfessorDao
    abstract fun turmaDisciplinaDao(): TurmaDisciplinaDao
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
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

        private const val DATABASE_NAME = "app-database.db"

    }
}