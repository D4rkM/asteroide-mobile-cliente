package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_adicionar_pessoas_poltrona.*
import kotlinx.android.synthetic.main.content_adicionar_pessoas_poltrona.*
import kotlinx.android.synthetic.main.content_compra_passagem.*
import kotlinx.android.synthetic.main.item_poltronas_dados_usuario.*
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class AdicionarPessoasPoltronaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_pessoas_poltrona)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var intent = intent

        var pol = intent.getIntArrayExtra("poltronasSelecionadas")

        var nome = intent.getStringExtra("nome")

        var numPoltrona = intent.getStringExtra("poltrona")

        val lstPoltrona = ArrayList<String>()

        var x = 0

        var adapter = PoltronaAdpter(this, lstPoltrona, pol, nome, numPoltrona)

        while (x < pol.size){

            lstPoltrona.add(pol.get(x).toString());

            list_view_poltronas.setAdapter(adapter)

            x = x + 1
        }
    }

    //classe do adapter
    private inner class PoltronaAdpter(ctx: Context, items: List<String>, pol:IntArray, nome:String, numPoltrona:String) : ArrayAdapter<String>(ctx, 0, items) {

        var poltronasSelecionadas = pol

        var nomeUsuario = nome

        var numPoltrona = numPoltrona

        var arrayNumPoltronaComNomePosition = intArrayOf()
        var arrayNomePoltrona = mutableListOf<String>()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var v = convertView

            if (v == null) {
                v = LayoutInflater.from(context).inflate(R.layout.item_poltronas_dados_usuario, null)
            }

            val item = getItem(position)

            val txtPoltronaNum = v!!.findViewById<TextView>(R.id.txtPoltronaNumber)
            val txtAddPessoa = v.findViewById<TextView>(R.id.txtAddPessoa)

            txtPoltronaNum.setText("Número da poltrona: " + item)

            if(!nomeUsuario.equals("Adicionar Passageiro")) {

                var t = 0

                while (t < poltronasSelecionadas.size) {

                    if (item.toInt() == numPoltrona.toInt()) {
                        txtAddPessoa.setText(nomeUsuario)

                        arrayNumPoltronaComNomePosition = arrayNumPoltronaComNomePosition + position
                    }

                    if (position == arrayNumPoltronaComNomePosition[t]) {
                        txtAddPessoa.setText("funcionou")
                    }

                    t = t + 1
                }
            }

            txtAddPessoa.setOnClickListener{

                if(position == 0){
                    toast("Essa poltrona já tem uma pessoa")
                }else{
                    intent = Intent(applicationContext, AdicionarDadosPessoaPoltronaActivity::class.java)

                    intent.putExtra("poltronasSelecionadas", poltronasSelecionadas)
                    intent.putExtra("poltrona", item.toString())
                    intent.putExtra("arrayNumPoltronaComNomePosition", arrayNumPoltronaComNomePosition)

                    startActivity(intent)
                }

            }

            if(position == 0){

                txtAddPessoa.setText("Usuário atual")

            }

            return v
        }
    }
}
