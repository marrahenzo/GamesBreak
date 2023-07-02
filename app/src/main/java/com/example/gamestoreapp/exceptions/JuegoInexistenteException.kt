package exceptions

class JuegoInexistenteException(private val mensaje: String = "Juego inexistente") : Exception(mensaje) {
    override val message: String
        get() = mensaje
}