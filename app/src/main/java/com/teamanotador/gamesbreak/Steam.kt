package com.teamanotador.gamesbreak

import android.util.Log
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.Purchase
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.exceptions.SaldoInsuficienteException
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import java.util.Date

const val COMISION = 1.02

object Steam : Intermediario {
    override fun comprar(game: Game, user: User): Purchase {
        Log.d(user.money.toString(), "guita del usuario")
        val totalAPagar: Double = game.price.times(COMISION)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${user.money} insuficiente para pagar $totalAPagar")
        }
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)
        user.efectuarTransaccion(totalAPagar)

        Log.d(user.money.toString(), "guita del usuario")
        //TODO sacar

        return Purchase(
            idCompra, user.id, game.id, totalAPagar,
            Utils.mostrarDateComoCadena(Date())
        )
    }

}