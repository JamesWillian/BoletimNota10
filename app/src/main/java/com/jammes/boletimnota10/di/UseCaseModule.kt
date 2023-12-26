package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.ui.domain.aluno.GetAlunoUseCase
import com.jammes.boletimnota10.ui.domain.aluno.GetAlunoUseCaseImpl
import com.jammes.boletimnota10.ui.domain.aluno.InsertAlunoUseCase
import com.jammes.boletimnota10.ui.domain.aluno.InsertAlunoUseCaseImpl
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma.GetAllTurmasUseCase
import com.jammes.boletimnota10.ui.domain.turma.GetAllTurmasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCase
import com.jammes.boletimnota10.ui.domain.turma.InsertTurmaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.professor.GetAllProfessoresUseCase
import com.jammes.boletimnota10.ui.domain.professor.GetAllProfessoresUseCaseImpl
import com.jammes.boletimnota10.ui.domain.professor.InsertProfessorUseCase
import com.jammes.boletimnota10.ui.domain.professor.InsertProfessorUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma_disciplina.GetAllTurmaDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.GetAllTurmaDisciplinasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.turma_disciplina.InsertTurmaDisciplinaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.unidade.GetAllUnidadesUseCase
import com.jammes.boletimnota10.ui.domain.unidade.GetAllUnidadesUseCaseImpl
import com.jammes.boletimnota10.ui.domain.unidade.InsertUnidadeUseCase
import com.jammes.boletimnota10.ui.domain.unidade.InsertUnidadeUseCaseImpl
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
    abstract fun providesGetAllTurmasUseCase(
        impl: GetAllTurmasUseCaseImpl
    ): GetAllTurmasUseCase

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
    abstract fun providesGetAllUnidadesUseCase(
        impl: GetAllUnidadesUseCaseImpl
    ): GetAllUnidadesUseCase

    @Singleton
    @Binds
    abstract fun providesInsertUnidadeUseCase(
        impl: InsertUnidadeUseCaseImpl
    ): InsertUnidadeUseCase

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
    abstract fun providesGetAllProfessoresUseCase(
        impl: GetAllProfessoresUseCaseImpl
    ): GetAllProfessoresUseCase

    @Singleton
    @Binds
    abstract fun providesInsertProfessorUseCase(
        impl: InsertProfessorUseCaseImpl
    ): InsertProfessorUseCase

    @Singleton
    @Binds
    abstract fun providesGetAllTurmaDisciplinasUseCase(
        impl: GetAllTurmaDisciplinasUseCaseImpl
    ): GetAllTurmaDisciplinasUseCase

    @Singleton
    @Binds
    abstract fun providesInsertTurmaDisciplinasUseCase(
        impl: InsertTurmaDisciplinaUseCaseImpl
    ): InsertTurmaDisciplinaUseCase
}