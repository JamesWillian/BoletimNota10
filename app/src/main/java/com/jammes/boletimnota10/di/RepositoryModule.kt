package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.core.repository.AlunoRepository
import com.jammes.boletimnota10.core.repository.AlunoRepositoryImpl
import com.jammes.boletimnota10.core.repository.DisciplinaRepository
import com.jammes.boletimnota10.core.repository.DisciplinaRepositoryImpl
import com.jammes.boletimnota10.core.repository.TurmaRepository
import com.jammes.boletimnota10.core.repository.TurmaRepositoryImpl
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
    abstract fun providesTurmaRepository(impl: TurmaRepositoryImpl): TurmaRepository

    @Singleton
    @Binds
    abstract fun providesAlunoRepository(impl: AlunoRepositoryImpl): AlunoRepository

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