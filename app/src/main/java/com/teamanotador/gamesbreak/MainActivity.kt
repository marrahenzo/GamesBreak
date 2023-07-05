package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamanotador.gamesbreak.adapter.GameAdapter
import com.teamanotador.gamesbreak.adapter.GenreAdapter
import com.teamanotador.gamesbreak.repositories.GenreRepository
import data.Game
import repositories.GameRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initRecyclerViewGenre()

    }

    private fun onGameSelected (game:Game){
        val intent:Intent = Intent(this, GameActivity::class.java)
        intent.putExtra("gameId", game.id)
        startActivity(intent)
    }
    private fun initRecyclerView () {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewGames)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GameAdapter(GameRepository.get()) {game -> onGameSelected(game)}

    }

    private fun initRecyclerViewGenre() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewGenres)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = (GenreAdapter(GenreRepository.get()))
    }
}