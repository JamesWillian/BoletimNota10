package com.jammes.boletimnota10.collections.domain.periodo

interface InserirPeriodoUseCase {

    suspend operator fun invoke(periodo: String, turmaId: String): String
}