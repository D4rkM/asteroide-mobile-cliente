package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_usuario.*
import kotlinx.android.synthetic.main.content_cadastro_usuario.*
import models.Usuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import utils.HttpConnection
import utils.ipServidorComPorta
import utils.repetirUsuario
import android.text.Editable
import android.text.TextWatcher



class CadastroUsuarioActivity : AppCompatActivity() {
    var userExistente = false
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


        txtUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                //aqui você executa uma determinada ação antes da modificação do editText

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                doAsync {

                    val url = ipServidorComPorta() +"/api/v1/buscaruser/cliente"

                    val map = HashMap<String, String>()
                    map.put("login", txtUser.text.toString())

                    val resultado = HttpConnection.post(url, map)

                    Log.d("API", resultado)

                    uiThread {

                        val jsonarray = JSONArray(resultado)

                        //Verificando se a senha ou usuário estão corretas
                        if(!jsonarray.isNull(0)){


                            userExistente = true

                            toast(userExistente.toString())
                        }else{
                            userExistente = false
                        }
                    }
                }

            }

            override fun afterTextChanged(s: Editable) {

                //aqui você executa uma determinada ação depois da modificação do editText

            }
        })


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
                }else if(userExistente == false){

                    intent = Intent(this, CadastroUsuarioSegundaParteActivity::class.java)

                    intent.putExtra("NomeUser", txtNomeCompletoUser.text.toString())
                    intent.putExtra("user", txtUser.text.toString())
                    intent.putExtra("senhaUser", txtSenhaUser.text.toString())
                    intent.putExtra("emailUser", txtEmailUser.text.toString())

                    intent.putExtra("tela", tela)

                    startActivity(intent)

                    finish()
                }else{
                    txtUser.setError("Usuário Existente")
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
