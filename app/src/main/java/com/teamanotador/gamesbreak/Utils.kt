package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.repositories.UserRepository
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object Utils {
    fun formatMonto(monto: Double?): String = String.format("%.2f", monto)

    fun mostrarDateComoCadena(fecha: Date): String {
        val formatoFecha = SimpleDateFormat("yyyy/MM/dd")
        return formatoFecha.format(fecha)
    }

    fun getById(userId: Long): User? {
        return UserRepository.getUsers().firstOrNull { it.id == userId }
    }

    fun mostrarMoneyFormateada(cantidad: Double?): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
        return currencyFormat.format(cantidad)
    }

    fun calcularCashback(date: String): Double {
        var cashback = 0.0

        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val antiguedad = LocalDate.parse(date, formatter)

        val periodoMeses = Period.between(antiguedad, LocalDate.now()).toTotalMonths()
        if (periodoMeses <= 3) {
            cashback = 0.05
        } else if (periodoMeses < 12) {
            cashback = 0.03
        }

        return cashback
    }

    fun efectuarTransaccion(importe: Double, user: User) {
        user.money = user.money.minus(importe)
    }
}