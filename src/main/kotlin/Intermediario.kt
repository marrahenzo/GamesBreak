import data.Game
import data.Purchase
import data.User
import java.text.SimpleDateFormat
import java.util.Date

abstract class Intermediario {

  abstract fun comprar(game:Game, user:User):Purchase?
  protected fun mostrarDateComoCadena (fecha:Date):String{
    val formatoFecha = SimpleDateFormat("yyyy-MM-dd")
    return formatoFecha.format(fecha)
  }
}