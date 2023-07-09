package com.teamanotador.gamesbreak.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.repositories.GameRepository

class PopUpCompra : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pop_up_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameId = arguments?.getLong(resources.getString(R.string.game_id))
        val subtotal = view.findViewById<TextView>(R.id.subtotal)
        val total = view.findViewById<TextView>(R.id.total)
        if (gameId != null) {
            val game = GameRepository.getById(gameId)
            subtotal.text = game.getPriceFormateado()
            total.text = game.getPriceFormateado()
        }
    }
}