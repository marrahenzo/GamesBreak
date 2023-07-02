package com.example.gamestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamestoreapp.fragments.PopUpCompra
import repositories.GameRepository

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val gameId = intent.getLongExtra("gameId", 0)
        renderizarInfoJuego(gameId)
        val buttonCompra = findViewById<Button>(R.id.botonComprar);

        buttonCompra.setOnClickListener{mostrarPopUp(gameId)}
    }

    private fun renderizarInfoJuego(gameId: Long):Unit{
        val game = GameRepository.getById(gameId)
        val titulo = findViewById<TextView>(R.id.nombreJuego)
        val portada = findViewById<ImageView>(R.id.portadaJuego)
        val genero = findViewById<TextView>(R.id.generoJuego)
        val descripcion = findViewById<TextView>(R.id.descripcionJuego)
        val precio = findViewById<TextView>(R.id.precioJuego)

        titulo.setText(game.name)
        genero.setText(game.genre)
        precio.setText(game.getPriceFormateado())
        Glide.with(portada.context).load(game.permalink).into(portada)
    }

    private fun mostrarPopUp(gameId:Long) {
        val bundle = Bundle()
        bundle.putLong("gameId", gameId);
        val popUp = PopUpCompra()
        popUp.arguments = bundle
        popUp.show((this).supportFragmentManager, "show popup")
    }
}