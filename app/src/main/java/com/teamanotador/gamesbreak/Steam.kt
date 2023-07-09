package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import data.Purchase
import data.User
import exceptions.SaldoInsuficienteException
import java.util.Date

const val COMISION = 1.02

class Steam : Intermediario() {
    override fun comprar(game: Game, user: User): Purchase {
        val totalAPagar: Double = game.price.times(COMISION)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${user.money} insuficiente para pagar $totalAPagar")
        }
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)
        user.efectuarTransaccion(totalAPagar)
        return Purchase(
            idCompra, user.id, game.id, totalAPagar,
            Utils.mostrarDateComoCadena(Date())
        )
    }

}