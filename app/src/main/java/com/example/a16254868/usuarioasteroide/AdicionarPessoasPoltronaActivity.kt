package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_adicionar_pessoas_poltrona.*
import kotlinx.android.synthetic.main.content_adicionar_pessoas_poltrona.*
import kotlinx.android.synthetic.main.content_compra_passagem.*
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class AdicionarPessoasPoltronaActivity : AppCompatActivity() {

    //ARRUUUUUUUUUUUUUUUMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR AAAAAA LISTAGEM DEEEEEEEEEE POLTRONAAAAAAAAAAAAAAS SELECIONADAAAAAAAAAAAAAAAAAAAAS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_pessoas_poltrona)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var intent = intent

        val pol = intent.getIntArrayExtra("poltronasSelecionadas")

        val lstPoltrona = ArrayList<String>()

        var x = 0

        var adapter = PoltronaAdpter(this, lstPoltrona, x)//OOOOOOO VALOOOOOOOOOOOOOOR QUEEEEEEE ESTA VINDOOOOOOOOOOOO É O X DE CIMA E NÃO O DO WHILEEEEEEEEEEEEEEEEEEE

        while (x < pol.size){

            lstPoltrona.add(pol.get(x).toString());

            list_view_poltronas.setAdapter(adapter)

            x = x + 1
        }

    }


    //classe do adapter
    private inner class PoltronaAdpter(ctx: Context, items: List<String>, x:Int) : ArrayAdapter<String>(ctx, 0, items) {

        var poltronas = x

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var v = convertView

            if (v == null) {
                v = LayoutInflater.from(context).inflate(R.layout.item_poltronas_dados_usuario, null)
            }

            val item = getItem(position)

            val txtPoltronaNum = v!!.findViewById<TextView>(R.id.txtPoltronaNumber)
            val txtAddPessoa = v.findViewById<TextView>(R.id.txtAddPessoa)

            txtPoltronaNum.setText("Número da poltrona: " + item)

            if(poltronas == 0){
                txtAddPessoa.setText("Usuário atual")
            }

            return v
        }
    }
}
