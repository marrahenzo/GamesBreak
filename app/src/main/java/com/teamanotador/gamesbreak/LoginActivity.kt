package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
                binding.etLoginPassword.text.toString().trim()
            )

            if (usuarioLogueado != null) {
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra(resources.getString(R.string.usuario_id), usuarioLogueado.id)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, R.string.login_error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}