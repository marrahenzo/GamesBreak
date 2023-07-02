package com.example.gamestoreapp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamestoreapp.R
import com.example.gamestoreapp.data.Genre

class GenreViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val genre = view.findViewById<TextView>(R.id.genre_name)
    //val icon = view.findViewById<ImageView>(R.id.genre_icon)

    fun render (genreModel: Genre){
        genre.text = genreModel.name
    }
}