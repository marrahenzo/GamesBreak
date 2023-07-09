package com.teamanotador.gamesbreak.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.databinding.PopUpCompraBinding
import com.teamanotador.gamesbreak.repositories.GameRepository

class PopUpCompra : DialogFragment() {

    private lateinit var binding: PopUpCompraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PopUpCompraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameId = arguments?.getLong(resources.getString(R.string.game_id))
        if (gameId != null) {
            val game = GameRepository.getById(gameId)
            binding.subtotal.text = game.getPriceFormateado()
            binding.total.text = game.getPriceFormateado()
        }
    }
}