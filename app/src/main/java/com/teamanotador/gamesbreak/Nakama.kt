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

class Nakama : Intermediario {
    override fun comprar(game: Game, user: User): Purchase {
        val fechaActual: LocalDate = LocalDate.now()
        val comision =
            if (fechaActual.dayOfWeek.name == SABADO || fechaActual.dayOfWeek.name == DOMINGO) 1.03 else 1.0075
        val totalAPagar: Double = game.price.times(comision)

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