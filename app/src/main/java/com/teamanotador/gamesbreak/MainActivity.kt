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
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.GenreRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var tvMainUsuario: TextView
    private lateinit var ivMainProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initRecyclerViewGenre()
        tvMainUsuario = findViewById(R.id.tv_main_usuario)
        ivMainProfile = findViewById(R.id.iv_main_profile)

        val idUsuario = intent.getLongExtra(resources.getString(R.string.intent_id_usuario), 0)
        val usuario: User? = UserRepository.getById(idUsuario)
        tvMainUsuario.text = usuario?.name
        Picasso.get()
            .load(usuario?.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(ivMainProfile)
    }

    private fun onGameSelected(game: Game) {
        val intent: Intent = Intent(this, GameActivity::class.java)
        intent.putExtra(resources.getString(R.string.game_id), game.id)
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