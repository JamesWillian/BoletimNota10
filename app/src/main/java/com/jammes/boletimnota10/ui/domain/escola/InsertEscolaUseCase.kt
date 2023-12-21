package com.jammes.boletimnota10.ui.domain.escola

interface InsertEscolaUseCase {

    suspend operator fun invoke(nome: String): Boolean
}