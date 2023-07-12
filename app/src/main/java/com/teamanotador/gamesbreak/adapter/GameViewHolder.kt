package com.teamanotador.gamesbreak.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Game

class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val game: TextView = view.findViewById(R.id.name)
    private val genre: TextView = view.findViewById(R.id.genre)
    private val image: ImageView = view.findViewById(R.id.image)
    private val navButton: Button = view.findViewById(R.id.button_navigator)
    private val rating: TextView = view.findViewById(R.id.tv_game_calificacion)

    fun render(gameModel: Game, onClickListener: (Game) -> Unit) {
        game.text = gameModel.name
        genre.text = gameModel.genre.nombre
        rating.text = gameModel.rating.toString()

        Picasso.get()
            .load(gameModel.permalink)
            .placeholder(R.drawable.game_placeholder)
            .error(R.drawable.game_placeholder)
            .into(image)

        navButton.setOnClickListener { onClickListener(gameModel) }

    }


}