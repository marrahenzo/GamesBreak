package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_login: Button
    private lateinit var et_usuario: EditText
    private lateinit var et_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_usuario = findViewById(R.id.et_login_usuario)
        et_password = findViewById(R.id.et_login_password)
        btn_login = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            var usuarioLogueado = LoginUtils.login(
                et_usuario.text.toString().trim(),
                et_password.text.toString().trim(),
                applicationContext
            )

            if (usuarioLogueado != null) {
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.putExtra("idUsuario", usuarioLogueado.id)
                startActivity(intent)
            }
        }
    }
}