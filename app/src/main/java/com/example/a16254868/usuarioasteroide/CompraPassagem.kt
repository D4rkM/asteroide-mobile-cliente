package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_compra_passagem.*
import kotlinx.android.synthetic.main.content_compra_passagem.*
import java.text.NumberFormat
import java.util.*



class CompraPassagem : AppCompatActivity() {


    var passagem_adapter = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_passagem)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val lstPassagem = ArrayList<String>()

        var adapter = PassagemAdapter(this, lstPassagem)

        list_view_passagem.setAdapter(adapter)

        lstPassagem.add("12:00");
        lstPassagem.add("16:00");
        lstPassagem.add("16:00");
        lstPassagem.add("16:00");
        lstPassagem.add("16:00");

        /*object : AsyncTask<Void, Void, Void>() {

            var lstPassagem = ArrayList<Viagem>()

            override fun doInBackground(vararg voids: Void): Void? {

                val retornoJson = HttpConnection.get(ipServidorComPorta() + "/api/v1/sugestoes")

                Log.d("TAG", retornoJson)

                try {
                    val jsonArray = JSONArray(retornoJson)

                    for (i in 0 until jsonArray.length()) {

                        val item = jsonArray.getJSONObject(i)

                        val c = Viagem(
                                item.getInt("id"),
                                item.getString("valor"),
                                item.getString("nome"),
                                item.getString("data"),
                                item.getString("hora"),
                                item.getString("descricao"),
                                item.getString("imagem"))

                        lstPassagem.add(c)
                    }

                    Log.d("TAG", lstPassagem.size.toString() + "")
                } catch (ex: Exception) {
                    Log.e("Erro: ", ex.message)
                }

                return null
            }

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                adapter.addAll(lstPassagem)
            }
        }.execute()*/

        list_view_passagem.setOnItemClickListener(OnItemClickListener { adapter, view, posicao, id ->
            // TODO Auto-generated method stub

            startActivity(Intent(applicationContext, CompraPassagemSegundoPasso::class.java))
        })
    }

    //classe do adapter
    private inner class PassagemAdapter(ctx: Context, items: List<String>) : ArrayAdapter<String>(ctx, 0, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var v = convertView

            if (v == null) {
                v = LayoutInflater.from(context).inflate(R.layout.item_lista_passagem, null)
            }


            val item = getItem(position)

            val tltViagem = v!!.findViewById<TextView>(R.id.tltViagem)
            val txtValor = v.findViewById<TextView>(R.id.txtValorViagem)

            val imagemViagem = v.findViewById<ImageView>(R.id.imagemViagem)

            val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

            return v
        }
    }
}
