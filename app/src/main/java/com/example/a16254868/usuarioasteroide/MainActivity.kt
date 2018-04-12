package com.example.a16254868.usuarioasteroide

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botaoLogar.setOnClickListener {

            val intent = Intent(this, HomeUsuarioActivity::class.java)

            startActivity(intent)

        }

        botaoCadastrar.setOnClickListener {

            val intent = Intent(this, CadastroUsuarioActivity::class.java)

            startActivity(intent)
        }
    }
}
