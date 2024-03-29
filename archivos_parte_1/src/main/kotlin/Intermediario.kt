import data.Game
import data.Purchase
import data.User

abstract class Intermediario {

    abstract fun comprar(game: Game, user: User): Purchase?
}