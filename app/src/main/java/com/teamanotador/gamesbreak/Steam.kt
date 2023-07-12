package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.Purchase
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.exceptions.SaldoInsuficienteException
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import java.util.Date

const val COMISION = 0.02

object Steam : Intermediario {
    override fun comprar(game: Game, user: User): Purchase {
        val totalAPagar: Double = obtenerTotal(game, user)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${Utils.formatMonto(user.money)} insuficiente")
        }
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)
        Utils.efectuarTransaccion(totalAPagar, user)

        return Purchase(
            idCompra, user.id, game.id, totalAPagar,
            Utils.mostrarDateComoCadena(Date()), this
        )
    }

    override fun obtenerComision(game: Game): Double {
        return game.price.times(COMISION)
    }

    override fun obtenerTotal(game: Game, user: User): Double {
        return game.price.plus(obtenerComision(game))
            .minus(Utils.calcularCashback(user.createdDate))
    }
}