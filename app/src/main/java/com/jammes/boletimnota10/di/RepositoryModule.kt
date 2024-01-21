package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.core.repository.AlunoRepository
import com.jammes.boletimnota10.core.repository.AlunoRepositoryImpl
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.DisciplinaRepositoryImpl
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.core.repository.TurmaRepositoryImpl
import com.jammes.boletimnota10.core.repository.AvaliacaoRepository
import com.jammes.boletimnota10.core.repository.AvaliacaoRepositoryImpl
import com.jammes.boletimnota10.core.repository.PeriodoRepository
import com.jammes.boletimnota10.core.repository.PeriodoRepositoryImpl
import com.jammes.boletimnota10.core.repository.TurmaDisciplinaRepository
import com.jammes.boletimnota10.core.repository.TurmaDisciplinaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesAlunoRepository(impl: AlunoRepositoryImpl): AlunoRepository

    @Singleton
    @Binds
    abstract fun providesDisciplinaRepository(impl: DisciplinaRepositoryImpl): DisciplinaRepository

    @Singleton
    @Binds
    abstract fun providesTurmaRepository(impl: TurmaRepositoryImpl): TurmaRepository

    @Singleton
    @Binds
    abstract fun providesPeriodoRepository(impl: PeriodoRepositoryImpl): PeriodoRepository

    @Singleton
    @Binds
    abstract fun providesAvaliacaoRepository(impl: AvaliacaoRepositoryImpl): AvaliacaoRepository

    @Singleton
    @Binds
    abstract fun providesTurmaDisciplinaRepository(impl: TurmaDisciplinaRepositoryImpl): TurmaDisciplinaRepository
}