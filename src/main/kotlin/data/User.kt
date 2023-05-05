package data

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

data class User(
    val id: Long,
    val nickName: String,
    val password: String,
    val name: String,
    val surname: String,
    var money: Double,
    val createdDate: String
) {
    fun calcularCashback(importe: Double): Double {
        var cashback = 0.0

        val fechaStr = createdDate
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val antiguedad = LocalDate.parse(fechaStr, formatter)

        val periodo = Period.between(antiguedad, LocalDate.now())
        if (periodo.months <= 3) {
            cashback = 0.05
        } else if (periodo.months < 12) {
            cashback = 0.03
        }

        return cashback
    }

    fun efectuarTransaccion(importe: Double) {
        val cashback = this.calcularCashback(importe)
        this.money -= importe
        this.money += (importe * cashback)
    }

    override fun toString(): String {
        return "Usuario: $nickName, Nombre: $name, Apellido: $surname, Saldo: $money, Fecha de creación: $createdDate"
    }

}
