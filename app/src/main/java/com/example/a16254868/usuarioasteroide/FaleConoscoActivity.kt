package com.example.a16254868.usuarioasteroide

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_fale_conosco.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_fale_conosco.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

class FaleConoscoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fale_conosco)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        enviarConosco.setOnClickListener {

            doAsync {

                val sugestao = txtSugestao.text.toString()
                val reclamacao = txtReclamacao.text.toString()
                val elogio = txtElogio.text.toString()

                val url = "http://10.107.144.9:3000/api/v1/sugestoes"

                val map = HashMap<String, String>()
                /*map.put("sugestao", login)
                map.put("reclamacao", senha)
                map.put("elogio", senha)*/

                val resultado = HttpConnection.post(url, map)

                Log.d("API", resultado)

                uiThread {

                    val jsonarray = JSONArray(resultado)

                    //Verificando se a senha ou usuário estão corretas
                    if(jsonarray.isNull(0)){
                        Toast.makeText(applicationContext, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
                    }else{
                        for (i in 0 until jsonarray.length()) {

                            val jsonobject = jsonarray.getJSONObject(i)

                            val intent = Intent(applicationContext, HomeUsuarioActivity::class.java)

                            startActivity(intent)

                            finish()

                            Toast.makeText(applicationContext, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }

        }

    }

}
