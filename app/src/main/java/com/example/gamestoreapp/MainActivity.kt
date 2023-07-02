package com.example.gamestoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamestoreapp.adapter.GameAdapter
import com.example.gamestoreapp.adapter.GenreAdapter
import com.example.gamestoreapp.repositories.GenreRepository
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