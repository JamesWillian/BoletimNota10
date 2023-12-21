package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.core.repository.AnoSerieRepository
import com.jammes.boletimnota10.core.repository.AnoSerieRepositoryImpl
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.DisciplinaRepositoryImpl
import com.jammes.boletimnota10.core.repository.EscolaRepository
import com.jammes.boletimnota10.core.repository.EscolaRepositoryImpl
import com.jammes.boletimnota10.core.repository.ProfessorRepository
import com.jammes.boletimnota10.core.repository.ProfessorRepositoryImpl
import com.jammes.boletimnota10.core.repository.TipoAtividadeRepository
import com.jammes.boletimnota10.core.repository.TipoAtividadeRepositoryImpl
import com.jammes.boletimnota10.core.repository.UnidadeRepository
import com.jammes.boletimnota10.core.repository.UnidadeRepositoryImpl
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
    abstract fun providesEscolaRepository(impl: EscolaRepositoryImpl): EscolaRepository

    @Singleton
    @Binds
    abstract fun providesAnoSerieRepository(impl: AnoSerieRepositoryImpl): AnoSerieRepository

    @Singleton
    @Binds
    abstract fun providesUnidadeRepository(impl: UnidadeRepositoryImpl): UnidadeRepository

    @Singleton
    @Binds
    abstract fun providesDisciplinaRepository(impl: DisciplinaRepositoryImpl): DisciplinaRepository

    @Singleton
    @Binds
    abstract fun providesProfessorRepository(impl: ProfessorRepositoryImpl): ProfessorRepository

    @Singleton
    @Binds
    abstract fun providesTipoAtividadeRepository(impl: TipoAtividadeRepositoryImpl): TipoAtividadeRepository
}