package com.teamanotador.gamesbreak

import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.repositories.UserRepository

object LoginUtils {
    fun login(user: String, password: String): User? {
        var usuarioEncontrado: User? = UserRepository.getByName(user)

        if (usuarioEncontrado?.password == password)
            return usuarioEncontrado

        return null
    }
}