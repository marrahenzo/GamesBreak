package com.teamanotador.gamesbreak.data

import com.teamanotador.gamesbreak.Intermediario

data class Purchase(
    val id: Long,
    val userId: Long,
    val gameId: Long,
    val amount: Double,
    val createdDate: String,
    val intermediario: Intermediario
)
