import data.Game
import data.Purchase
import data.User
import exceptions.SaldoInsuficienteException
import repositories.PurchaseRepository
import java.time.LocalTime
import java.util.*

const val HORA_INICIO = 20
const val MINUTO_INICIO = 0
const val HORA_FIN = 23
const val MINUTO_FIN = 59

class EpicGames : Intermediario() {
    override fun comprar(game: Game, user: User): Purchase {
        val hora1 = LocalTime.of(HORA_INICIO, MINUTO_INICIO)
        val hora2 = LocalTime.of(HORA_FIN, MINUTO_FIN)
        val ahora = LocalTime.now()
        val comision = if (ahora.isAfter(hora1) && ahora.isBefore(hora2)) 1.01 else 1.03
        val totalAPagar: Double = game.price.times(comision)

        if (user.money < totalAPagar) {
            throw SaldoInsuficienteException("Saldo de ${user.money} insuficiente para pagar $totalAPagar")
        }
        val idCompra = PurchaseRepository.obtenerUltimoId().plus(1)
        user.efectuarTransaccion(totalAPagar)
        return Purchase(idCompra, user.id, game.id, totalAPagar, Utils.mostrarDateComoCadena(Date()))
    }

}