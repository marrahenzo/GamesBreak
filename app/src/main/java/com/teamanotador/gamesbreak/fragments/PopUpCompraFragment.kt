package com.teamanotador.gamesbreak.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.repositories.GameRepository

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PopUpCompra : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pop_up_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameId = arguments?.getLong("gameId")
        val subtotal = view.findViewById<TextView>(R.id.subtotal)
        val total = view.findViewById<TextView>(R.id.total)
        if(gameId != null){
            val game =  GameRepository.getById(gameId)
            subtotal.setText(game.getPriceFormateado())
            total.setText(game.getPriceFormateado())

        }



    }

    private fun getDataGame (){

    }

}