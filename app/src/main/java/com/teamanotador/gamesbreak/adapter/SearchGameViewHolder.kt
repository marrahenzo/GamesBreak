package com.teamanotador.gamesbreak.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Game

class SearchGameViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val game: TextView = view.findViewById(R.id.tv_nombre_game)
    private val genre: TextView = view.findViewById(R.id.tv_categoria_game)
    private val navButton: Button = view.findViewById(R.id.button_navigator)
    private val rating: TextView = view.findViewById(R.id.tv_game_calificacion)
    private val image: ImageView = view.findViewById(R.id.iv_game_image)

    fun render(gameModel: Game, onClickListener: (Game) -> Unit) {
       game.text = cortarNombre(gameModel.name)
        genre.text = gameModel.genre.nombre
        rating.text = gameModel.rating.toString()
        Picasso.get()
            .load(gameModel.permalink)
            .placeholder(R.drawable.game_placeholder)
            .error(R.drawable.game_placeholder)
            .into(image)

        navButton.setOnClickListener { onClickListener(gameModel) }
    }

    fun cortarNombre(nombre: String): String {
        val MAX_LONGITUD = 16
        val textoCortado = if (nombre.length > MAX_LONGITUD) {
            nombre.substring(0, MAX_LONGITUD) + "..."
        } else {
            nombre
        }
        return textoCortado
    }


}