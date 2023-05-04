import data.Game
import data.Purchase
import data.User
import java.time.LocalDate
import java.util.*
class Nakama : Intermediario() {
    override fun comprar(game: Game, user:User): Purchase? {
        val fechaActual:LocalDate = LocalDate.now();
        var comision:Double = 1.0075

        if(fechaActual.dayOfWeek.value.equals(6) || fechaActual.dayOfWeek.value.equals(7)){
            comision = 1.03
        }
        val random = Random()
        val idRandom = random.nextLong()

        val totalAPagar:Double =  game.price * comision
        if(user.money < totalAPagar){
            println("No tenens deinero suficiente para comprar el juego")
            return null
        }
        user.calcularCashback(totalAPagar)
        return Purchase(idRandom,user.id, game.id, totalAPagar, mostrarDateComoCadena(Date()))
    }

}