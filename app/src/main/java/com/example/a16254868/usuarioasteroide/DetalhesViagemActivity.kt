package com.example.a16254868.usuarioasteroide

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_detalhes_viagem.*
import kotlinx.android.synthetic.main.content_detalhes_viagem.*
import models.Viagem
import utils.converterDinheiro
import utils.ipServidorSemPorta
import utils.repetirViagem

class DetalhesViagemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_viagem)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idViagem = intent.getStringExtra("id")

        repetirViagem(idViagem, applicationContext){
            var viagem = it

            Picasso.with(applicationContext).load(ipServidorSemPorta() + "/inf4m/asteroide/" + viagem.getImagem()).into(imgDetalheViagem)

            tltViagemDetail.setText(viagem.getOrigem() + " X " + viagem.getDestino())
                saidaHorarioDetail.setText(viagem.enderecoSaida + " às " + viagem.horaPartida)
            destinoHorarioDetail.setText(viagem.enderecoChegada + " às " + viagem.horaChegada)
            classeOnibusDetail.setText("Classe do ônibus: " + viagem.tipo_classe)
            valorDetail.setText(converterDinheiro(viagem.valor))

            btnComprarViagemDetail.setOnClickListener {
                var intent = Intent(applicationContext, CompraPassagemSegundoPasso::class.java)

                intent.putExtra("id", viagem.getId().toString())

                startActivity(intent)
            }

        }
    }

}
