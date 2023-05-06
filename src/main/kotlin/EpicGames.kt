import data.Game
import data.Purchase
import data.User
import repositories.PurchaseRepository
import java.time.LocalTime
import java.util.*

class EpicGames : Intermediario() {
    override fun comprar(game: Game, user: User): Purchase {
        val hora1 = LocalTime.of(20, 0)
        val hora2 = LocalTime.of(23, 59)
        val ahora = LocalTime.now()
        val comision = if (ahora.isAfter(hora1) && ahora.isBefore(hora2)) 1.01 else 1.03

        val totalAPagar: Double = game.price.times(comision)
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${user.money} insuficiente para pagar $totalAPagar")
        }
        user.efectuarTransaccion(totalAPagar)
        return Purchase(idCompra, user.id, game.id, totalAPagar, Utils.mostrarDateComoCadena(Date()))
    }

}