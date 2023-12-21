package com.jammes.boletimnota10.di

import com.jammes.boletimnota10.ui.domain.ano_serie.GetAllAnoSeriesUseCase
import com.jammes.boletimnota10.ui.domain.ano_serie.GetAllAnoSeriesUseCaseImpl
import com.jammes.boletimnota10.ui.domain.ano_serie.InsertAnoSerieUseCase
import com.jammes.boletimnota10.ui.domain.ano_serie.InsertAnoSerieUseCaseImpl
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.GetAllDisciplinasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCase
import com.jammes.boletimnota10.ui.domain.disciplina.InsertDisciplinaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.escola.GetAllEscolasUseCase
import com.jammes.boletimnota10.ui.domain.escola.GetAllEscolasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.escola.InsertEscolaUseCase
import com.jammes.boletimnota10.ui.domain.escola.InsertEscolaUseCaseImpl
import com.jammes.boletimnota10.ui.domain.professor.GetAllProfessoresUseCase
import com.jammes.boletimnota10.ui.domain.professor.GetAllProfessoresUseCaseImpl
import com.jammes.boletimnota10.ui.domain.professor.InsertProfessorUseCase
import com.jammes.boletimnota10.ui.domain.professor.InsertProfessorUseCaseImpl
import com.jammes.boletimnota10.ui.domain.tipo_atividade.GetAllTiposAtividadesUseCase
import com.jammes.boletimnota10.ui.domain.tipo_atividade.GetAllTiposAtividadesUseCaseImpl
import com.jammes.boletimnota10.ui.domain.tipo_atividade.InsertTipoAtividadeUseCase
import com.jammes.boletimnota10.ui.domain.tipo_atividade.InsertTipoAtividadeUseCaseImpl
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
    abstract fun providesGetAllEscolasUseCase(
        impl: GetAllEscolasUseCaseImpl
    ): GetAllEscolasUseCase

    @Singleton
    @Binds
    abstract fun providesInsertEscolaUseCase(
        impl: InsertEscolaUseCaseImpl
    ): InsertEscolaUseCase

    @Singleton
    @Binds
    abstract fun providesGetAllAnoSeriesUseCase(
        impl: GetAllAnoSeriesUseCaseImpl
    ): GetAllAnoSeriesUseCase

    @Singleton
    @Binds
    abstract fun providesInsertAnoSerieUseCase(
        impl: InsertAnoSerieUseCaseImpl
    ): InsertAnoSerieUseCase

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
    abstract fun providesGetAllTiposAtividadesUseCase(
        impl: GetAllTiposAtividadesUseCaseImpl
    ): GetAllTiposAtividadesUseCase

    @Singleton
    @Binds
    abstract fun providesInsertTipoAtividadeUseCase(
        impl: InsertTipoAtividadeUseCaseImpl
    ): InsertTipoAtividadeUseCase
}