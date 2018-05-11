package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro_usuario.*
import kotlinx.android.synthetic.main.content_cadastro_usuario.*
import models.Usuario
import utils.repetirUsuario

class CadastroUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tela:String = intent.getStringExtra("tela")

        val preferencias = getSharedPreferences("Cliente", Context.MODE_PRIVATE)

        val login = preferencias.getString("login", "")
        val senha = preferencias.getString("senha", "")

        var usuario = Usuario()

        if(tela.equals("perfil")){
            tltTelaCadastroPefil.text = "Seu Perfil"
            botãoCadastroPefil("perfil")

            repetirUsuario(login, senha, applicationContext) {

                usuario = it

                preencherCampos(usuario)
            }

        }else{
            tltTelaCadastroPefil.text = "Cadastro"
            botãoCadastroPefil("Cadastro")
        }

    }

    fun botãoCadastroPefil(tela:String){

        if(tela.equals("Cadastro")){
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

                    intent = Intent(this, CadastroUsuarioSegundaParteActivity::class.java)

                    intent.putExtra("NomeUser", txtNomeCompletoUser.text.toString())
                    intent.putExtra("user", txtUser.text.toString())
                    intent.putExtra("senhaUser", txtSenhaUser.text.toString())
                    intent.putExtra("emailUser", txtEmailUser.text.toString())

                    intent.putExtra("tela", tela)

                    startActivity(intent)

                    finish()
                }
            }
        }else{
            btnContinuarCadastro.setOnClickListener {

                if(txtNomeCompletoUser.text.toString().equals("")){
                    txtNomeCompletoUser.setError("Esse campo é obrigatório")
                }else if(txtUser.text.toString().equals("")){
                    txtUser.setError("Esse campo é obrigatório")
                }else if(txtConfirmarSenhaUser.text.toString() != txtSenhaUser.text.toString()){
                    txtConfirmarSenhaUser.setError("As senhas não condizem")
                }else if(txtEmailUser.text.toString().equals("")){
                    txtEmailUser.setError("Esse campo é obrigatório")
                }else{

                    intent = Intent(this, CadastroUsuarioSegundaParteActivity::class.java)

                    intent.putExtra("NomeUser", txtNomeCompletoUser.text.toString())
                    intent.putExtra("user", txtUser.text.toString())
                    intent.putExtra("senhaUser", txtSenhaUser.text.toString())
                    intent.putExtra("emailUser", txtEmailUser.text.toString())

                    intent.putExtra("tela", tela)

                    startActivity(intent)

                    finish()
                }
            }
        }

    }

    fun preencherCampos(usuario: Usuario){
        txtNomeCompletoUser.setText(usuario.getNome().toString())
        txtUser.setText(usuario.getUsuario().toString())
        txtEmailUser.setText(usuario.getEmail().toString())

    }

}
