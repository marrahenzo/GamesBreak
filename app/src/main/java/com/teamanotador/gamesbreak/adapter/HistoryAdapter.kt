package com.teamanotador.gamesbreak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.data.Purchase

class HistoryAdapter(private val purchaseList: List<Purchase>) :
    RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoryViewHolder(layoutInflater.inflate(R.layout.history_item, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.render(purchaseList[position])
    }

    override fun getItemCount(): Int {
        return this.purchaseList.size
    }
}