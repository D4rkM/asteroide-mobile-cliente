package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_fale_conosco.*
import kotlinx.android.synthetic.main.content_fale_conosco.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import utils.HttpConnection
import utils.ipServidorComPorta

class FaleConoscoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fale_conosco)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var preferencias = getSharedPreferences("Cliente", Context.MODE_PRIVATE)

        var id = preferencias.getString("id", "")

        enviarConosco.setOnClickListener {

            doAsync {

                val sugestao = txtSugestao.text.toString()
                val reclamacao = txtReclamacao.text.toString()
                val elogio = txtElogio.text.toString()

                val url = ipServidorComPorta() +"/api/v1/feedback/"+id

                val map = HashMap<String, String>()
                map.put("sugestao", sugestao)
                map.put("reclamacao", reclamacao)
                map.put("elogio", elogio)

                val resultado = HttpConnection.post(url, map)

                Log.d("API", resultado)

                uiThread {
                    //Nothing
                }

            }

            toast("Mensagens enviadas")
        }

    }

}
