package com.teamanotador.gamesbreak.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Genre

class GenreViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val genre = view.findViewById<TextView>(R.id.genre_name)
    //val icon = view.findViewById<ImageView>(R.id.genre_icon)

    fun render (genreModel: Genre){
        genre.text = genreModel.name
    }
}