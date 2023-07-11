package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.Purchase
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.exceptions.SaldoInsuficienteException
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import java.time.LocalDate
import java.util.Date

const val SABADO = "SATURDAY"
const val DOMINGO = "SUNDAY"
const val COMISION_MENOR_NAKAMA = 0.0075
const val COMISION_MAYOR_NAKAMA = 0.03

object Nakama : Intermediario {
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
        val fechaActual: LocalDate = LocalDate.now()
        return if (fechaActual.dayOfWeek.name == SABADO || fechaActual.dayOfWeek.name == DOMINGO)
            game.price.times(COMISION_MENOR_NAKAMA) else game.price.times(COMISION_MAYOR_NAKAMA)
    }

    override fun obtenerTotal(game: Game, user: User): Double {
        return game.price.plus(obtenerComision(game)).minus(user.calcularCashback())
    }
}