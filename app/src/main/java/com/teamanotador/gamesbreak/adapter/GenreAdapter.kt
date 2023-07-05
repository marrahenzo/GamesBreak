package com.teamanotador.gamesbreak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Genre

class GenreAdapter(private val genreList: List<Genre>): RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context);
        return GenreViewHolder(layoutInflater.inflate(R.layout.genre_item, parent, false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
       holder.render(genreList[position])
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

}