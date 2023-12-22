package com.jammes.boletimnota10.di

import android.app.Application
import com.jammes.boletimnota10.core.database.AppDatabase
import com.jammes.boletimnota10.core.database.dao.AlunoDao
import com.jammes.boletimnota10.core.database.dao.DisciplinaDao
import com.jammes.boletimnota10.core.database.dao.TurmaDao
import com.jammes.boletimnota10.core.database.dao.ProfessorDao
import com.jammes.boletimnota10.core.database.dao.TipoAtividadeDao
import com.jammes.boletimnota10.core.database.dao.UnidadeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun providesTurmaDao(database: AppDatabase): TurmaDao {
        return database.turmaDao()
    }

    @Singleton
    @Provides
    fun providesAlunoDao(database: AppDatabase): AlunoDao {
        return database.AlunoDao()
    }

    @Singleton
    @Provides
    fun providesUnidadeDao(database: AppDatabase): UnidadeDao {
        return database.unidadeDao()
    }

    @Singleton
    @Provides
    fun providesDisciplinaDao(database: AppDatabase): DisciplinaDao {
        return database.disciplinaDao()
    }

    @Singleton
    @Provides
    fun providesProfessorDao(database: AppDatabase): ProfessorDao {
        return database.professorDao()
    }

    @Singleton
    @Provides
    fun providesTipoAtividadeDao(database: AppDatabase): TipoAtividadeDao {
        return database.tipoAtividadeDao()
    }
}