package com.teamanotador.gamesbreak.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.Utils
import com.teamanotador.gamesbreak.data.Purchase
import com.teamanotador.gamesbreak.repositories.GameRepository

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val game: TextView = view.findViewById(R.id.history_item_name)
    private val date: TextView = view.findViewById(R.id.history_item_date)
    private val image: ImageView = view.findViewById(R.id.history_item_image)
    private val amount: TextView = view.findViewById(R.id.history_item_amount)

    fun render(purchaseModel: Purchase) {
        game.text = GameRepository.getById(purchaseModel.gameId).name
        date.text = purchaseModel.createdDate
        amount.text = Utils.formatMonto(purchaseModel.amount)

        Picasso.get()
            .load(GameRepository.getById(purchaseModel.gameId).permalink)
            .placeholder(R.drawable.game_placeholder)
            .error(R.drawable.game_placeholder)
            .into(image)
    }


}