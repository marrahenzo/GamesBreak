package com.teamanotador.gamesbreak.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Game

class SearchGameAdapter(private val gamesList: List<Game>, private val onClickListener: (Game) -> Unit) :
    RecyclerView.Adapter<SearchGameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchGameViewHolder(layoutInflater.inflate(R.layout.search_game_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchGameViewHolder, position: Int) {
        holder.render(gamesList[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return this.gamesList.size
    }

    fun actualizarDataList(size: Int) {
        this.notifyDataSetChanged()
    }
}