package exceptions

class SaldoInsuficienteException(private val mensaje: String = "Saldo insuficiente") : Exception(mensaje) {
    override val message: String
        get() = mensaje
}