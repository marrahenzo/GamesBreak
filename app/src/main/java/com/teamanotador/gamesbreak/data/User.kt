package com.teamanotador.gamesbreak.data

import java.text.NumberFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

data class User(
    val id: Long,
    val nickName: String,
    val password: String,
    val name: String,
    val surname: String,
    var money: Double,
    val createdDate: String,
    val profilePicture: String
) {
    fun calcularCashback(): Double {
        var cashback = 0.0

        val fechaStr = createdDate
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val antiguedad = LocalDate.parse(fechaStr, formatter)

        val periodoMeses = Period.between(antiguedad, LocalDate.now()).toTotalMonths()
        if (periodoMeses <= 3) {
            cashback = 0.05
        } else if (periodoMeses < 12) {
            cashback = 0.03
        }

        return cashback
    }

    fun efectuarTransaccion(importe: Double) {
        this.money = this.money.minus(importe)
    }

    fun mostrarMoneyFormateada():String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
        return currencyFormat.format(this.money)
    }

    override fun toString(): String {
        return "Usuario: $nickName, Nombre: $name, Apellido: $surname, Saldo: $money, Fecha de creación: $createdDate"
    }

}
