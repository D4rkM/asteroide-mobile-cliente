package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import models.Usuario
import utils.repetirUsuario

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferencias = getSharedPreferences("Cliente", Context.MODE_PRIVATE)

        var usuario = Usuario()

        botaoLogar.setOnClickListener {
            val login = txtLoginUsuario.text.toString()
            val senha = txtSenhaUsuario.text.toString()

           /*repetirUsuario(login, senha, applicationContext){
                usuario = it

                preferencias.edit().putString("login", login).apply()
                preferencias.edit().putString("senha", senha).apply()

                preferencias.edit().putString("id", usuario.getId().toString()).apply()

                val intent = Intent(applicationContext, HomeUsuarioActivity::class.java)

                startActivity(intent)

                finish()
            }*/

            val intent = Intent(applicationContext, HomeUsuarioActivity::class.java)

            startActivity(intent)
        }

        botaoCadastrar.setOnClickListener {

            val intent = Intent(this, CadastroUsuarioActivity::class.java)

            intent.putExtra("tela", "Cadastro")

            startActivity(intent)

        }
    }
}
