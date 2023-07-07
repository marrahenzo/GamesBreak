package com.teamanotador.gamesbreak

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.fragments.PopUpCompra
import com.teamanotador.gamesbreak.repositories.GameRepository

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val gameId = intent.getLongExtra("gameId", 0)
        renderizarInfoJuego(gameId)
        val buttonCompra = findViewById<Button>(R.id.botonComprar);

        buttonCompra.setOnClickListener { mostrarPopUp(gameId) }
    }

    private fun renderizarInfoJuego(gameId: Long): Unit {
        val game = GameRepository.getById(gameId)
        val titulo = findViewById<TextView>(R.id.tv_main_generos)
        val portada = findViewById<ImageView>(R.id.portadaJuego)
        val genero = findViewById<TextView>(R.id.generoJuego)
        val descripcion = findViewById<TextView>(R.id.descripcionJuego)
        val precio = findViewById<TextView>(R.id.precioJuego)

        titulo.text = game.name
        genero.text = game.genre.nombre
        precio.text = game.getPriceFormateado()
        Picasso.get()
            .load(game.permalink)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(portada)
    }

    private fun mostrarPopUp(gameId: Long) {
        val bundle = Bundle()
        bundle.putLong("gameId", gameId);
        val popUp = PopUpCompra()
        popUp.arguments = bundle
        popUp.show((this).supportFragmentManager, "show popup")
    }
}