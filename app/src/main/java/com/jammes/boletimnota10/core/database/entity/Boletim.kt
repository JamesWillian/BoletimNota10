package com.jammes.boletimnota10.core.database.entity

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT m.id AS moduloId, m.periodoId, d.descricao AS modulo, m.professor, SUM(a.nota) AS notaTotal " +
    "  FROM modulo m "+
            " INNER JOIN disciplina d ON d.id = m.disciplinaId " +
            "  LEFT JOIN avaliacao a ON a.moduloId = m.id " +
            " GROUP BY m.id, m.periodoId, d.descricao, m.professor"
)
data class Boletim(
    val moduloId: String,
    val periodoId: String,
    val modulo: String,
    val professor: String,
    val notaTotal: Float
)
