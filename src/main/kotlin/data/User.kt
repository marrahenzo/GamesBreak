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
    fun calcularCashback(importe: Double) {
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
        this.money -= importe
        this.money += (importe * cashback)
    }

    override fun toString(): String {
        return "nickName='$nickName', name='$name', surname='$surname', money=$money, createdDate='$createdDate'"
    }

}
