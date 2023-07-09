package com.teamanotador.gamesbreak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Game

class GameAdapter(private val gamesList: List<Game>, private val onClickListener: (Game) -> Unit) :
    RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(layoutInflater.inflate(R.layout.game_item, parent, false))
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.render(gamesList[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return this.gamesList.size
    }
}