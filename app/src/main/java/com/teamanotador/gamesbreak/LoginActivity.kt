package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario = findViewById(R.id.et_login_usuario)
        etPassword = findViewById(R.id.et_login_password)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            val usuarioLogueado = LoginUtils.login(
                etUsuario.text.toString().trim(),
                etPassword.text.toString().trim(),
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