package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.Purchase
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.exceptions.SaldoInsuficienteException
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import java.time.LocalTime
import java.util.Date

const val HORA_INICIO = 20
const val MINUTO_INICIO = 0
const val HORA_FIN = 23
const val MINUTO_FIN = 59
const val COMISION_MENOR_EPIC = 0.01
const val COMISION_MAYOR_EPIC = 0.03

object EpicGames : Intermediario {
    override fun comprar(game: Game, user: User): Purchase {
        val totalAPagar: Double = obtenerTotal(game, user)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${Utils.formatMonto(user.money)} insuficiente")
        }
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)
        user.efectuarTransaccion(totalAPagar)
        return Purchase(
            idCompra, user.id, game.id, totalAPagar,
            Utils.mostrarDateComoCadena(Date())
        )
    }

    override fun obtenerComision(game: Game): Double {
        val hora1 = LocalTime.of(HORA_INICIO, MINUTO_INICIO)
        val hora2 = LocalTime.of(HORA_FIN, MINUTO_FIN)
        val ahora = LocalTime.now()
        return if (ahora.isAfter(hora1) && ahora.isBefore(hora2))
            game.price.times(COMISION_MENOR_EPIC) else game.price.times(COMISION_MAYOR_EPIC)
    }

    override fun obtenerTotal(game: Game, user: User): Double {
        return game.price.plus(obtenerComision(game)).minus(user.calcularCashback())
    }
}