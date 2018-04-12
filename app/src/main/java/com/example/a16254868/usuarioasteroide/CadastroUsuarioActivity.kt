package com.example.a16254868.usuarioasteroide

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_cadastro_usuario.*
import kotlinx.android.synthetic.main.content_cadastro_usuario.*

class CadastroUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnContinuarCadastro.setOnClickListener {

            if(txtNomeCompletoUser.text.toString().equals("")){
                txtNomeCompletoUser.setError("Esse campo é obrigatório")
            }else if(txtUser.text.toString().equals("")){
                txtUser.setError("Esse campo é obrigatório")
            }else if(txtSenhaUser.text.toString().equals("")){
                txtSenhaUser.setError("Esse campo é obrigatório")
            }else if(txtConfirmarSenhaUser.text.toString().equals("")){
                txtConfirmarSenhaUser.setError("Esse campo é obrigatório")
            }else if(txtConfirmarSenhaUser.text.toString() != txtSenhaUser.text.toString()){
                txtConfirmarSenhaUser.setError("As senhas não condizem")
            }else if(txtEmailUser.text.toString().equals("")){
                txtEmailUser.setError("Esse campo é obrigatório")
            }else{
                startActivity(Intent(this, CadastroUsuarioSegundaParteActivity::class.java))
            }



        }
    }

}
