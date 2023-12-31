package com.jammes.boletimnota10.di

import android.app.Application
import com.jammes.boletimnota10.core.database.AppDatabase
import com.jammes.boletimnota10.core.database.dao.AlunoDao
import com.jammes.boletimnota10.core.database.dao.DisciplinaDao
import com.jammes.boletimnota10.core.database.dao.TurmaDao
import com.jammes.boletimnota10.core.database.dao.AvaliacaoDao
import com.jammes.boletimnota10.core.database.dao.TurmaDisciplinaDao
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
    fun providesDisciplinaDao(database: AppDatabase): DisciplinaDao {
        return database.disciplinaDao()
    }

    @Singleton
    @Provides
    fun providesAvaliacaoDao(database: AppDatabase): AvaliacaoDao {
        return database.avaliacaoDao()
    }

    @Singleton
    @Provides
    fun providesTurmaDisciplinaDao(database: AppDatabase): TurmaDisciplinaDao {
        return database.turmaDisciplinaDao()
    }
}