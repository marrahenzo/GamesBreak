import data.Game
import data.Purchase
import data.User
import exceptions.SaldoInsuficienteException
import repositories.PurchaseRepository
import java.time.LocalDate
import java.util.*

class Nakama : Intermediario() {
    override fun comprar(game: Game, user: User): Purchase {
        val fechaActual: LocalDate = LocalDate.now()
        val comision = if (fechaActual.dayOfWeek.value == 6 || fechaActual.dayOfWeek.value == 7) 1.03 else 1.0075

        val totalAPagar: Double = game.price.times(comision)
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${user.money} insuficiente para pagar $totalAPagar")
        }
        user.efectuarTransaccion(totalAPagar)
        return Purchase(idCompra, user.id, game.id, totalAPagar, Utils.mostrarDateComoCadena(Date()))
    }

}