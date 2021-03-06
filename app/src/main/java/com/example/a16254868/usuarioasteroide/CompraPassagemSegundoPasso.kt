package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_compra_passagem_segundo_passo.*
import kotlinx.android.synthetic.main.content_adicionar_dados_pessoa_poltrona.*
import models.Poltrona
import models.Viagem
import org.jetbrains.anko.toast
import utils.poltronasSelecionadas
import utils.repetirViagem

class CompraPassagemSegundoPasso : AppCompatActivity() {

    //var numeroList = arrayOf("1", "2")
    lateinit var numeroList:IntArray

    //internal var imagemList = intArrayOf(R.drawable.square_green, R.drawable.square_green)
    lateinit var imagemList:IntArray

    var arrayPoltronasSelecionadasNum = intArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_passagem_segundo_passo)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var viagem = Viagem()

        val idViagem = intent.getStringExtra("id")

        val gridview: GridView = findViewById(R.id.gridview)

        var adapter = GridAdapter()

        var poltrona = Poltrona()

        var cor = intArrayOf()

        var arrayNumeroPoltrona = intArrayOf()

        var numerosPoltronas = 0

        repetirViagem(idViagem, applicationContext){
            viagem  = it

            numerosPoltronas = viagem.getPoltronas().toInt()

            poltronasSelecionadas(idViagem, applicationContext){
                var x = 0
                while (x <= 50){
                    arrayPoltronasSelecionadasNum[x] = it
                }

            }

            toast(arrayPoltronasSelecionadasNum.toString())

            var x = 1

            while (x <= numerosPoltronas){

                if(arrayPoltronasSelecionadasNum[x] != x){
                    arrayNumeroPoltrona = arrayNumeroPoltrona + x
                    cor = cor + 0

                }else{
                    arrayNumeroPoltrona = arrayNumeroPoltrona + x
                    cor = cor + 2
                }

                x = x + 1

            }

            poltrona = Poltrona(arrayNumeroPoltrona, cor)

            adapter =  GridAdapter (applicationContext, poltrona)

            gridview.setAdapter(adapter)

            gridview.setOnItemClickListener { adapterView, view, i, l ->

                x = 0

                while (x < numerosPoltronas){

                    if(x == i){//verificando se é a poltrona que foi selecionada

                        if(cor[i] == 0){

                            arrayNumeroPoltrona[i] = x+1
                            cor[i] = 1

                        }else if(cor[i] == 1){

                            arrayNumeroPoltrona[i] = x+1
                            cor[i] = 0

                        }
                    }

                    x = x + 1

                }

                poltrona = Poltrona(arrayNumeroPoltrona, cor)
                adapter =  GridAdapter (applicationContext, poltrona)

                gridview.setAdapter(adapter)

            }

            fab.setOnClickListener { view ->

                var arrayPoltronasSelecionas = intArrayOf()

                x = 0

                while (x < numerosPoltronas){

                    if(cor[x] == 1){
                        arrayPoltronasSelecionas =  arrayPoltronasSelecionas + (x + 1)
                    }

                    x = x + 1

                }

                if(arrayPoltronasSelecionas.size >= 1){
                    intent = Intent(applicationContext, CompraPassagemTerceiroPassoActivity::class.java)

                    intent.putExtra("quantidadePoltronas", arrayPoltronasSelecionas.size.toString())
                    intent.putExtra("poltronasSelecionadas", arrayPoltronasSelecionas)
                    intent.putExtra("id", idViagem)

                    startActivity(intent)
                }else{
                    toast("Selecione no mínimo uma poltrona")
                }
            }
        }
    }
}
