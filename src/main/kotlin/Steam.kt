import data.Game
import data.Purchase
import data.User
import java.util.*

class Steam : Intermediario() {
    override fun comprar(game: Game, user:User): Purchase? {
        var totalAPagar:Double = game.price * 1.02
        val random = Random()
        val idRandom = random.nextLong()

        if(user.money < totalAPagar){
            println("No tenens deinero suficiente para comprar el juego")
            return null
        }
        user.calcularCashback(totalAPagar)
        return Purchase(idRandom,user.id, game.id, totalAPagar, mostrarDateComoCadena(Date()))
    }

}