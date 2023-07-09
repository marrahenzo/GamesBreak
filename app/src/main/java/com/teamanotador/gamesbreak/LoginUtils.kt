package com.teamanotador.gamesbreak

import android.content.Context
import android.widget.Toast
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.repositories.UserRepository

object LoginUtils {
    fun login(user: String, password: String, context: Context): User? {
        var usuarioEncontrado: User? = UserRepository.getUsers().firstOrNull { it.nickName == user }

        if (usuarioEncontrado?.password == password) {
            return usuarioEncontrado
        } else {
            Toast.makeText(context, R.string.login_error, Toast.LENGTH_SHORT).show()
        }
        return null
    }
}