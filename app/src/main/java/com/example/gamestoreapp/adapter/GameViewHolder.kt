package com.example.gamestoreapp.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamestoreapp.R
import data.Game

class GameViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val game = view.findViewById<TextView>(R.id.name)
    val genre = view.findViewById<TextView>(R.id.genre)
    val image = view.findViewById<ImageView>(R.id.image)
    val navButton:Button = view.findViewById<Button>(R.id.button_navigator)

    fun render (gameModel: Game, onClickListener:(Game) -> Unit ){
        game.text = gameModel.name
        genre.text = gameModel.genre
        Glide.with(image.context).load(gameModel.permalink).into(image)

        navButton.setOnClickListener{ onClickListener(gameModel) }

    }


}