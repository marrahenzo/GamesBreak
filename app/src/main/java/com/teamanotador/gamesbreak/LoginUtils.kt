package com.teamanotador.gamesbreak

import android.content.Context
import android.widget.Toast
import com.teamanotador.gamesbreak.repositories.UserRepository
import data.User

const val alert = "Usuario o contraseña incorrecto."

object LoginUtils {
    fun login(user: String, password: String, context: Context): User? {
        var usuarioEncontrado: User?

        usuarioEncontrado = UserRepository.getUsers().firstOrNull { it.nickName == user }
        if (usuarioEncontrado?.password == password) {
            // TODO PASAR AL MAIN
            println("Logueado")

        } else {
            // TODO REEMPLAZAR POR CARTEL DE ERROR
            println("Nombre de usuario o contraseña incorrecta.")

            Toast.makeText(context, alert, Toast.LENGTH_SHORT).show()
        }

        return usuarioEncontrado
    }
}