package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.teamanotador.gamesbreak.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val usuarioLogueado = LoginUtils.login(
                binding.etLoginUsuario.text.toString().trim(),
                binding.etLoginPassword.text.toString().trim(),
                applicationContext
            )

            if (usuarioLogueado != null) {
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.putExtra(resources.getString(R.string.intent_id_usuario), usuarioLogueado.id)
                startActivity(intent)
            }
        }
    }
}