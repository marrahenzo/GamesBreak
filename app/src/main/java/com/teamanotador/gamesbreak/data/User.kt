package com.teamanotador.gamesbreak.data

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
    override fun toString(): String {
        return "Usuario: $nickName, Nombre: $name, Apellido: $surname, Saldo: $money, Fecha de creación: $createdDate"
    }
}
