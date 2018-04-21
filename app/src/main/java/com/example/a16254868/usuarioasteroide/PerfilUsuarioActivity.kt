package com.example.a16254868.usuarioasteroide

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_perfil_usuario.*
import kotlinx.android.synthetic.main.content_perfil_usuario.*
import org.jetbrains.anko.startActivity

class PerfilUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        alterarPerfil.setOnClickListener {

            val intent = Intent(applicationContext, CadastroUsuarioActivity::class.java)

            intent.putExtra("tela", "perfil")

            startActivity(intent)
        }

        visualizarQRCode.setOnClickListener {

            startActivity(Intent(applicationContext, QRCodeUsuarioActivity::class.java))


        }
    }

}
