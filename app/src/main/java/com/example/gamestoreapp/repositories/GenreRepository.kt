package com.example.gamestoreapp.repositories

import com.example.gamestoreapp.data.Genre

object GenreRepository {
    private val genres = mutableListOf<Genre>()

    init {
        genres.add(
            Genre(
            "Survival Horror"
        )
        )
        genres.add(Genre(
            "Sandbox"
        ))
        genres.add(Genre(
            "Deporte"
        ))
        genres.add(Genre(
            "Shooter"
        ))
        genres.add(Genre(
            "Pelea"
        ))
        genres.add(Genre(
            "Carreras"
        ))
        genres.add(Genre(
            "Aventura"
        ))
        genres.add(Genre(
            "Acción"
        ))
        genres.add(
            Genre(
            "RPG"
            ))
    }

    fun get():List<Genre>{
        return genres
    }
}