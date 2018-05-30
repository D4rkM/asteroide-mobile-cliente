package com.example.a16254868.usuarioasteroide

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_cadastro_usuario_terceiro_passo.*
import kotlinx.android.synthetic.main.content_cadastro_usuario_terceiro_passo.*
import utils.retornaMêsValidadeCartao
import utils.retornarAnoValidadeCartao
import java.util.*

class CompraPassagemTerceiroPassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario_terceiro_passo)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (spinnerMesValidade != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, retornaMêsValidadeCartao())
            spinnerMesValidade.adapter = arrayAdapter

            spinnerMesValidade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Toast.makeText(applicationContext, personNames[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        }

        if (spinnerAnoValidade != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, retornarAnoValidadeCartao())
            spinnerAnoValidade.adapter = arrayAdapter

            spinnerAnoValidade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Toast.makeText(applicationContext, personNames[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        }
    }

}
