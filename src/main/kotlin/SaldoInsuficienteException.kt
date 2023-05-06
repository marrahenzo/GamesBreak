class SaldoInsuficienteException(private val mensaje: String) : Exception() {
    override val message: String
        get() = mensaje
}