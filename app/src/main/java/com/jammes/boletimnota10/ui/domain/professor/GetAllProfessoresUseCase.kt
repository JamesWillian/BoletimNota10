package com.jammes.boletimnota10.ui.domain.professor

import com.jammes.boletimnota10.ui.model.ProfessorItem

interface GetAllProfessoresUseCase {

    suspend operator fun invoke(): List<ProfessorItem>
}