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
        val userId = intent.getLongExtra(resources.getString(R.string.usuario_id), 0)
        renderizarInfoJuego(gameId)

        binding.btnComprar.setOnClickListener { mostrarPopUp(gameId, userId) }
        binding.ivBotonVolver.setOnClickListener { finish() }
    }

    private fun renderizarInfoJuego(gameId: Long): Unit {
        val game = GameRepository.getById(gameId)

        binding.tvGameTitulo.text = game.name
        binding.tvGameGenero.text = game.genre.nombre
        binding.tvGameDescripcion.text = game.description
        binding.tvGamePrecio.text = Utils.mostrarMoneyFormateada(game.price)
        binding.tvGameCalificacion.text = game.rating.toString()
        Picasso.get()
            .load(game.permalink)
            .placeholder(R.drawable.game_placeholder)
            .error(R.drawable.game_placeholder)
            .into(binding.ivGamePortada)
    }

    private fun mostrarPopUp(gameId: Long, userId: Long) {
        val bundle = Bundle()
        bundle.putLong(resources.getString(R.string.game_id), gameId)
        bundle.putLong(resources.getString(R.string.usuario_id), userId)
        val popUp = PopUpCompra()
        popUp.arguments = bundle
        popUp.show((this).supportFragmentManager, "show popup")
    }
}