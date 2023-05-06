import data.Game
import data.Purchase
import data.User
import exceptions.SaldoInsuficienteException
import repositories.GameRepository
import repositories.PurchaseRepository
import repositories.UserRepository

fun main() {
    var usuarioEnSesion: User? = null

    fun mostrarOpcionesMenu() {
        println("-----BIENVENIDO A GAMESBREAK-----")
        if (usuarioEnSesion == null) {
            println("1 - Iniciar sesión")
            println("2 - Ver listado de juegos disponibles")
            println("3 - Salir")
            return
        }
        println("1 - Ver datos de usuario")
        println("2 - Comprar juego")
        println("3 - Ver lista de compras")
        println("4 - Cerrar sesión")
        println("5 - Salir")
    }

    fun seleccionarUnIntermediario(): Intermediario? {
        println("Elige un intermediario para hacer la compra.")
        println("1 - Steam")
        println("2 - Epic Games")
        println("3 - Nakama")
        val intermediarioSeleccionado = false
        var opcion: String
        do {
            print("Ingrese una opción: ")
            opcion = readln()
            when (opcion) {
                "1" -> return Steam()
                "2" -> return EpicGames()
                "3" -> return Nakama()
                else -> println("Opción inválida")
            }
        } while (!intermediarioSeleccionado)
        return null
    }

    fun buscarUnJuego(): Game? {
        var juegoEncontrado = false
        var juego: Game?
        var nombreJuego: String
        do {
            println("-----------------------------")
            for (game in GameRepository.get()) {
                println(game.toString())
            }
            println("-----------------------------")
            print("Ingrese el nombre de un juego: ")
            nombreJuego = readln()
            juego = GameRepository.getByName(nombreJuego)
            if (juego == null) {
                println("No se encontró un juego con ese nombre")
                continue
            }
            juegoEncontrado = true
        } while (!juegoEncontrado)
        return juego
    }

    fun iniciarMenu() {
        mostrarOpcionesMenu()
        var opcion: String
        var salir = false
        do {
            print("Ingrese una opción: ")
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
                        println("---------------------------------------------------------------")
                        games.forEach { game ->
                            println(game.toString())
                        }
                        println("---------------------------------------------------------------")
                        mostrarOpcionesMenu()
                        continue
                    }

                    "3" -> {
                        salir = true
                    }

                    else -> println("Opción inválida")
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
                        println("-----Comprar un Juego-----")
                        val intermediario = seleccionarUnIntermediario()
                        val juegoSeleccionado = buscarUnJuego()
                        var compra: Purchase? = null
                        if (juegoSeleccionado != null && intermediario != null) {
                            try {
                                compra = intermediario.comprar(juegoSeleccionado, usuarioEnSesion!!)
                            } catch (e: SaldoInsuficienteException) {
                                println("El saldo actual es insuficiente")
                            }
                            if (compra != null) {
                                PurchaseRepository.add(compra)
                                println("--------------------------------------")
                                println("Compraste: ${GameRepository.getById(compra.gameId).name}")
                                println("Valor de la compra: $${Utils.formatMonto(compra.amount)}")
                                val cashback = usuarioEnSesion!!.calcularCashback()
                                if (cashback != 0.0)
                                    println(
                                        "Obtuviste un cashback de $${Utils.formatMonto(compra.amount.times(cashback))}"
                                    )
                                println("Saldo actual: $${Utils.formatMonto(usuarioEnSesion?.money)}")
                                println("--------------------------------------")
                            }
                        } else {
                            println("No se pudo comprar el juego")
                        }
                        mostrarOpcionesMenu()
                        continue
                    }

                    "3" -> {
                        PurchaseRepository.mostrarTodasLasComprasDeUnUsuario(usuarioEnSesion!!.id)
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
                        println("Opción inválida")
                    }
                }
            }
        } while (!salir)
        println("¡Adiós!")
    }

    iniciarMenu()
}

