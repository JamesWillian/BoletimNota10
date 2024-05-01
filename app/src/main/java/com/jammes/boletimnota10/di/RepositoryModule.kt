package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.core.repository.room.AlunoRepository
import com.jammes.boletimnota10.core.repository.room.AlunoRepositoryImpl
import com.jammes.boletimnota10.core.repository.room.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.room.DisciplinaRepositoryImpl
import com.jammes.boletimnota10.core.repository.room.TurmaRepository
import com.jammes.boletimnota10.core.repository.room.TurmaRepositoryImpl
import com.jammes.boletimnota10.core.repository.room.AvaliacaoRepository
import com.jammes.boletimnota10.core.repository.room.AvaliacaoRepositoryImpl
import com.jammes.boletimnota10.core.repository.room.BoletimRepository
import com.jammes.boletimnota10.core.repository.room.BoletimRepositoryImpl
import com.jammes.boletimnota10.core.repository.room.PeriodoRepository
import com.jammes.boletimnota10.core.repository.room.PeriodoRepositoryImpl
import com.jammes.boletimnota10.core.repository.room.ModuloRepository
import com.jammes.boletimnota10.core.repository.room.ModuloRepositoryImpl
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
    abstract fun providesModuloRepository(impl: ModuloRepositoryImpl): ModuloRepository

    @Singleton
    @Binds
    abstract fun providesAvaliacaoRepository(impl: AvaliacaoRepositoryImpl): AvaliacaoRepository

    @Singleton
    @Binds
    abstract fun providesBoletimRepository(impl: BoletimRepositoryImpl): BoletimRepository

}