package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.adapter.GameAdapter
import com.teamanotador.gamesbreak.adapter.GenreAdapter
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.GenreRepository
import com.teamanotador.gamesbreak.repositories.UserRepository
import data.User

class MainActivity : AppCompatActivity() {

    private lateinit var tv_main_usuario: TextView
    private lateinit var iv_main_profile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initRecyclerViewGenre()
        tv_main_usuario = findViewById(R.id.tv_main_usuario)
        iv_main_profile = findViewById(R.id.iv_main_profile)

        val idUsuario = intent.getLongExtra("idUsuario", 0)
        val usuario: User? = UserRepository.getById(idUsuario)
        tv_main_usuario.text = usuario?.name
        Picasso.get()
            .load(usuario?.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(iv_main_profile)
    }

    private fun onGameSelected(game: Game) {
        val intent: Intent = Intent(this, GameActivity::class.java)
        intent.putExtra("gameId", game.id)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_main_games)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GameAdapter(GameRepository.get()) { game -> onGameSelected(game) }

    }

    private fun initRecyclerViewGenre() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_main_generos)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = (GenreAdapter(GenreRepository.get()))
    }
}