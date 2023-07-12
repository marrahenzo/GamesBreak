package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.Purchase
import com.teamanotador.gamesbreak.data.User

interface Intermediario {
    fun comprar(game: Game, user: User): Purchase?
    fun obtenerComision(game: Game): Double
    fun obtenerTotal(game: Game, user: User): Double
    fun obtenerNombreIntermediario(): String {
        return this::class.simpleName.toString()
    }
}