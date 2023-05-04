import data.Game
import data.User
import repositories.Carrito
import repositories.GameRepository
import repositories.PurchaseRepository
import repositories.UserRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    var usuarioEnSesion: User? = null

    fun mostrarOpcionesMenu() {
        println("-----MENU TIENDA VIDEJUEGOS-----")
        if (usuarioEnSesion == null) {
            println("1 - Iniciar sesion")
            println("2 - Ver listado de juegos disponibles")
            println("3 - salir")
            return
        }
        println("1 - Ver datos de usuario")
        println("2 - Comprar juego")
        println("3 - Ver lista de compras")
        println("4 - Cerrar sesion")
        println("5 - Salir")
    }

    fun selecionarUnIntermediario(): Intermediario? {
        println("Elige un intermediario para hacer la compra.")
        println("1 - Steam")
        println("2 - Epic Games")
        println("3 - Nakama")
        var intermediarioSeleccionado: Boolean = false;
        var opcion: String;
        do {
            print("Ingrese una opcion: ")
            opcion = readln()
            when (opcion) {
                "1" -> return Steam()
                "2" -> return EpicGames()
                "3" -> return Nakama()
            }
        } while (!intermediarioSeleccionado)
        return null
    }

    fun buscarUnJuego(): Game? {
        var juegoEncontrado: Boolean = false
        var game: Game? = null
        var nombreJuego: String = ""
        do {
            print("Ingrese el nombre de un juego: ")
            nombreJuego = readln()
            game = GameRepository.getByName(nombreJuego)
            if (game == null) {
                println("No se encontro un juego con ese nombre")
                continue
            }
            juegoEncontrado = true
        } while (!juegoEncontrado)
        return game
    }

    fun iniciarMenu() {
        mostrarOpcionesMenu()
        var opcion: String
        var salir: Boolean = false
        do {
            print("Ingrese una opcion: ")
            opcion = readln()

            if (usuarioEnSesion == null) {
                when (opcion) {
                    "1" -> {
                        usuarioEnSesion = UserRepository.login()
                        mostrarOpcionesMenu()
                        continue
                    }

                    "2" -> {
                        val games = GameRepository.get()
                        games.forEach { game ->
                            println(game.toString())
                        }
                        mostrarOpcionesMenu()
                        continue
                    }

                    "3" -> {
                        salir = true
                    }

                    else -> println("Opcion invalida")
                }
            } else {
                when (opcion) {
                    "1" -> {
                        val user = UserRepository.getById(usuarioEnSesion!!.id)
                        println(user.toString())
                        mostrarOpcionesMenu()
                        continue
                    }

                    "2" -> {
                        if (usuarioEnSesion == null) {
                            println("Debes iniciar sesion")
                            continue
                        }
                        println("-----Comprar un Juego-----")
                        val intermediario = selecionarUnIntermediario()
                        val juegoSeleccionado = buscarUnJuego()
                        if (juegoSeleccionado != null && intermediario != null) {
                            val compra = intermediario.comprar(juegoSeleccionado, usuarioEnSesion!!)
                            if (compra != null) {
                                PurchaseRepository.add(compra)
                                println("Juego comprado correctamente.")
                            }
                        } else {
                            println("No se pudo comprar el juego")
                        }
                        mostrarOpcionesMenu()
                        continue
                    }

                    "3" -> {
                        if (usuarioEnSesion == null) {
                            println("Debes iniciar sesion")
                            continue
                        } else {
                            PurchaseRepository.mostrarTodasLasComprasDeUnUsuario(usuarioEnSesion!!.id)
                        }
                        mostrarOpcionesMenu()
                        continue
                    }

                    "4" -> {
                        usuarioEnSesion = null
                        mostrarOpcionesMenu()
                        continue
                    }

                    "5" -> {
                        salir = true
                    }

                    else -> {
                        println("no es valida")
                    }
                }
            }
        } while (!salir)
    }
    iniciarMenu()

}

