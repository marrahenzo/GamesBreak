package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.adapter.GameAdapter
import com.teamanotador.gamesbreak.adapter.GenreAdapter
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.databinding.ActivityMainBinding
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.GenreRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initRecyclerViewGenre()

        val idUsuario = intent.getLongExtra(resources.getString(R.string.intent_id_usuario), 0)
        val usuario: User? = UserRepository.getById(idUsuario)
        binding.tvMainUsuario.text = usuario?.name
        Picasso.get()
            .load(usuario?.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(binding.ivMainProfile)
    }

    private fun onGameSelected(game: Game) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(resources.getString(R.string.game_id), game.id)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        binding.rvMainGames.layoutManager = LinearLayoutManager(this)
        binding.rvMainGames.adapter =
            GameAdapter(GameRepository.get()) { game -> onGameSelected(game) }
    }

    private fun initRecyclerViewGenre() {
        binding.rvMainGeneros.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvMainGeneros.adapter = (GenreAdapter(GenreRepository.get()))
    }
}