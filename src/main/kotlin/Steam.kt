import data.Game
import data.Purchase
import data.User
import repositories.PurchaseRepository
import java.util.*

class Steam : Intermediario() {
    override fun comprar(game: Game, user: User): Purchase {
        val comision = 1.02
        val totalAPagar: Double = game.price.times(comision)
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${user.money} insuficiente para pagar $totalAPagar")
        }

        user.efectuarTransaccion(totalAPagar)
        return Purchase(idCompra, user.id, game.id, totalAPagar, Utils.mostrarDateComoCadena(Date()))
    }

}