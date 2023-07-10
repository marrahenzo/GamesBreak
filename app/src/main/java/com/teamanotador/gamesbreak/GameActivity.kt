package com.teamanotador.gamesbreak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.databinding.ActivityGameBinding
import com.teamanotador.gamesbreak.fragments.PopUpCompra
import com.teamanotador.gamesbreak.repositories.GameRepository

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gameId = intent.getLongExtra(resources.getString(R.string.game_id), 0)
        renderizarInfoJuego(gameId)

        binding.botonComprar.setOnClickListener { mostrarPopUp(gameId) }
        binding.ivBotonVolver.setOnClickListener { finish() }
    }

    private fun renderizarInfoJuego(gameId: Long): Unit {
        val game = GameRepository.getById(gameId)

        binding.tvMainGeneros.text = game.name
        binding.generoJuego.text = game.genre.nombre
        binding.descripcionJuego.text = game.description
        binding.precioJuego.text = game.getPriceFormateado()
        binding.tvGameCalificacion.text = game.rating.toString()
        Picasso.get()
            .load(game.permalink)
            .placeholder(R.drawable.game_placeholder)
            .error(R.drawable.game_placeholder)
            .into(binding.portadaJuego)
    }

    private fun mostrarPopUp(gameId: Long) {
        val bundle = Bundle()
        bundle.putLong(resources.getString(R.string.game_id), gameId);
        val popUp = PopUpCompra()
        popUp.arguments = bundle
        popUp.show((this).supportFragmentManager, "show popup")
    }
}