package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.adapter.GameAdapter
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.databinding.ActivityMainBinding
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idUser = intent.getLongExtra(resources.getString(R.string.usuario_id), 0)
        val user: User = UserRepository.getById(idUser)

        initRecyclerView(user)
        binding.tvMainUsuario.text = user.name
        Picasso.get()
            .load(user.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(binding.ivMainProfile)
    }

    private fun onGameSelected(game: Game, user: User) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(resources.getString(R.string.game_id), game.id)
        intent.putExtra(
            resources.getString(R.string.usuario_id),
            user.id
        )
        startActivity(intent)
    }

    private fun initRecyclerView(user: User) {
        binding.rvMainGames.layoutManager = LinearLayoutManager(this)
        binding.rvMainGames.adapter =
            GameAdapter(GameRepository.get()) { game -> onGameSelected(game, user) }
    }
}