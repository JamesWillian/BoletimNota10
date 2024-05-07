package com.jammes.boletimnota10.core.repository.api

import retrofit2.Response

interface AlunoApiService {

    suspend fun criarAluno(): Response<String>
}