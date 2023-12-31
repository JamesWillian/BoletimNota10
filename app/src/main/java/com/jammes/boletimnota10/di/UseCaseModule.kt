package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.ui.domain.aluno.GetAlunoUseCase
import com.jammes.boletimnota10.ui.domain.aluno.GetAlunoUseCaseImpl
import com.jammes.boletimnota10.ui.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.ui.domain.aluno.InsertAlunoUseCaseImpl
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma.BuscarTurmaAtualUseCase
import com.jammes.boletimnota10.ui.domain.turma.BuscarTurmaAtualUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCase
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.avaliacao.BuscarTodasAvaliacoesUseCase
import com.jammes.boletimnota10.ui.domain.avaliacao.BuscarTodasAvaliacoesUseCaseImpl
import com.jammes.boletimnota10.ui.domain.avaliacao.InserirAvaliacaoUseCase
import com.jammes.boletimnota10.ui.domain.avaliacao.InserirAvaliacaoUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma.ExisteTurmaCadastradaUseCase
import com.jammes.boletimnota10.ui.domain.turma.ExisteTurmaCadastradaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma_disciplina.BuscarDisciplinasDaTurmaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.BuscarDisciplinasDaTurmaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun providesExisteTurmaCadastradaUseCase(
        impl: ExisteTurmaCadastradaUseCaseImpl
    ): ExisteTurmaCadastradaUseCase

    @Singleton
    @Binds
    abstract fun providesBuscarTurmaAtualUseCase(
        impl: BuscarTurmaAtualUseCaseImpl
    ): BuscarTurmaAtualUseCase

    @Singleton
    @Binds
    abstract fun providesInsertTurmaUseCase(
        impl: InsertTurmaUseCaseImpl
    ): InsertTurmaUseCase

    @Singleton
    @Binds
    abstract fun providesGetAlunoUseCase(
        impl: GetAlunoUseCaseImpl
    ): GetAlunoUseCase

    @Singleton
    @Binds
    abstract fun providesInsertAlunoUseCase(
        impl: InsertAlunoUseCaseImpl
    ): InsertAlunoUseCase


    @Singleton
    @Binds
    abstract fun providesGetAllDisciplinasUseCase(
        impl: GetAllDisciplinasUseCaseImpl
    ): GetAllDisciplinasUseCase

    @Singleton
    @Binds
    abstract fun providesInsertDisciplinaUseCase(
        impl: InsertDisciplinaUseCaseImpl
    ): InsertDisciplinaUseCase

    @Singleton
    @Binds
    abstract fun providesBuscarTodasAvaliacoesUseCase(
        impl: BuscarTodasAvaliacoesUseCaseImpl
    ): BuscarTodasAvaliacoesUseCase

    @Singleton
    @Binds
    abstract fun providesInserirAvaliacaoUseCase(
        impl: InserirAvaliacaoUseCaseImpl
    ): InserirAvaliacaoUseCase

    @Singleton
    @Binds
    abstract fun providesGetAllTurmaDisciplinasUseCase(
        impl: BuscarDisciplinasDaTurmaUseCaseImpl
    ): BuscarDisciplinasDaTurmaUseCase

    @Singleton
    @Binds
    abstract fun providesInsertTurmaDisciplinasUseCase(
        impl: InsertTurmaDisciplinaUseCaseImpl
    ): InsertTurmaDisciplinaUseCase
}