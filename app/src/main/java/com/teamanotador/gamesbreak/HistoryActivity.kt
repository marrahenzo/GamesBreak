package com.teamanotador.gamesbreak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamanotador.gamesbreak.adapter.HistoryAdapter
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.databinding.ActivityHistoryBinding
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idUser = intent.getLongExtra(resources.getString(R.string.usuario_id), 0)
        val user: User = UserRepository.getById(idUser)
        initRecyclerView(user)

        binding.ivBotonVolver.setOnClickListener { finish() }
    }

    private fun initRecyclerView(user: User) {
        val lista = PurchaseRepository.getByUserId(user.id)
        binding.rvHistorialCompras.layoutManager = LinearLayoutManager(this)
        binding.rvHistorialCompras.adapter =
            HistoryAdapter(lista)
        if (lista.isEmpty())
            binding.tvHistorialVacio.text = resources.getString(R.string.historial_sin_compras)
    }
}