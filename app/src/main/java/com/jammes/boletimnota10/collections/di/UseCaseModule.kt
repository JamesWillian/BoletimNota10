package com.jammes.boletimnota10.collections.di

import com.jammes.boletimnota10.collections.domain.aluno.GetAlunoUseCase
import com.jammes.boletimnota10.collections.domain.aluno.GetAlunoUseCaseImpl
import com.jammes.boletimnota10.collections.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.collections.domain.aluno.InsertAlunoUseCaseImpl
import com.jammes.boletimnota10.collections.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.collections.domain.disciplina.GetAllDisciplinasUseCaseImpl
import com.jammes.boletimnota10.collections.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.collections.domain.disciplina.InsertDisciplinaUseCaseImpl
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaAtualUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaAtualUseCaseImpl
import com.jammes.boletimnota10.collections.domain.turma.InserirTurmaUseCase
import com.jammes.boletimnota10.collections.domain.turma.InserirTurmaUseCaseImpl
import com.jammes.boletimnota10.collections.domain.avaliacao.BuscarTodasAvaliacoesUseCase
import com.jammes.boletimnota10.collections.domain.avaliacao.BuscarTodasAvaliacoesUseCaseImpl
import com.jammes.boletimnota10.collections.domain.avaliacao.InserirAvaliacaoUseCase
import com.jammes.boletimnota10.collections.domain.avaliacao.InserirAvaliacaoUseCaseImpl
import com.jammes.boletimnota10.collections.domain.boletim.BuscarBoletimDoPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.boletim.BuscarBoletimDoPeriodoUseCaseImpl
import com.jammes.boletimnota10.collections.domain.turma.BuscarTodasTurmasUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTodasTurmasUseCaseImpl
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaPorIdUseCase
import com.jammes.boletimnota10.collections.domain.turma.BuscarTurmaPorIdUseCaseImpl
import com.jammes.boletimnota10.collections.domain.turma.ExisteTurmaCadastradaUseCase
import com.jammes.boletimnota10.collections.domain.turma.ExisteTurmaCadastradaUseCaseImpl
import com.jammes.boletimnota10.collections.domain.modulo.BuscarModulosDoPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.modulo.BuscarModulosDoPeriodoUseCaseImpl
import com.jammes.boletimnota10.collections.domain.modulo.AlternarModuloUseCase
import com.jammes.boletimnota10.collections.domain.modulo.AlternarModuloUseCaseImpl
import com.jammes.boletimnota10.collections.domain.periodo.BuscarPeriodosDaTurmaUseCase
import com.jammes.boletimnota10.collections.domain.periodo.BuscarPeriodosDaTurmaUseCaseImpl
import com.jammes.boletimnota10.collections.domain.periodo.InserirPeriodoUseCase
import com.jammes.boletimnota10.collections.domain.periodo.InserirPeriodoUseCaseImpl
import com.jammes.boletimnota10.collections.domain.turma.AlterarTurmaUseCase
import com.jammes.boletimnota10.collections.domain.turma.AlterarTurmaUseCaseImpl
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
    abstract fun providesBuscarTodasTurmasUseCase(
        impl: BuscarTodasTurmasUseCaseImpl
    ): BuscarTodasTurmasUseCase

    @Singleton
    @Binds
    abstract fun providesBuscarTurmaPorIdUseCase(
        impl: BuscarTurmaPorIdUseCaseImpl
    ): BuscarTurmaPorIdUseCase

    @Singleton
    @Binds
    abstract fun providesInsertTurmaUseCase(
        impl: InserirTurmaUseCaseImpl
    ): InserirTurmaUseCase

    @Singleton
    @Binds
    abstract fun providesAlterarTurmaUseCase(
        impl: AlterarTurmaUseCaseImpl
    ): AlterarTurmaUseCase

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
    abstract fun providesBuscarModulosDoPeriodoUseCase(
        impl: BuscarModulosDoPeriodoUseCaseImpl
    ): BuscarModulosDoPeriodoUseCase

    @Singleton
    @Binds
    abstract fun providesAlternarModuloUseCase(
        impl: AlternarModuloUseCaseImpl
    ): AlternarModuloUseCase

    @Singleton
    @Binds
    abstract fun providesInserirPeriodoUseCase(
        impl: InserirPeriodoUseCaseImpl
    ): InserirPeriodoUseCase

    @Singleton
    @Binds
    abstract fun providesBuscarPeriodosDaTurmaUseCase(
        impl: BuscarPeriodosDaTurmaUseCaseImpl
    ): BuscarPeriodosDaTurmaUseCase

    @Singleton
    @Binds
    abstract fun providesBuscarBoletimDoPeriodoUseCase(
        impl: BuscarBoletimDoPeriodoUseCaseImpl
    ): BuscarBoletimDoPeriodoUseCase
}