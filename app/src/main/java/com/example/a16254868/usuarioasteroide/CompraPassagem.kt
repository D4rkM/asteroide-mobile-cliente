package com.example.a16254868.usuarioasteroide

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.activity_compra_passagem.*
import kotlinx.android.synthetic.main.content_compra_passagem.*
import models.Usuario
import models.Viagem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import utils.HttpConnection
import utils.converterDinheiro
import utils.ipServidorComPorta
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*



class CompraPassagem : AppCompatActivity() {

    private val myCalendar = Calendar.getInstance()
    private var dataPartida: EditText? = null

    var BRAZIL = Locale("pt", "BR")
    var passagem_adapter = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_passagem)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //CRIANDO UM DATE PICKER DIALOG PARA O USUÁRIO SELECIONAR SUA DATA DE NASCIMENTO
        val datePickerListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, BRAZIL)
            dataPartida!!.setText(sdf.format(myCalendar.getTime()))
        }


        dataPartida = findViewById<View>(R.id.dataPartida) as EditText
        dataPartida!!.setOnClickListener(View.OnClickListener {
            DatePickerDialog(this, datePickerListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        })

        var viagem = Viagem()

        var adapter = PassagemAdapter(this, ArrayList<Viagem>())

        list_view_passagem.setAdapter(adapter)

        buscarPassagem.setOnClickListener{//DEIXA ITENS VISIVEIS E INVISIVEIS


            doAsync {

                val url = ipServidorComPorta() +"/api/v1/buscaviagem"

                val map = HashMap<String, String>()
                map.put("origem", txtOrigemViagem.text.toString())
                map.put("destino", txtDestinoViagem.text.toString())
                map.put("data_saida", dataPartida!!.text.toString())

                val resultado = HttpConnection.post(url, map)

                Log.d("APIVIAGEM", resultado)

                uiThread {
                    val jsonarray = JSONArray(resultado)

                    //Verificando se a senha ou usuário estão corretas
                    if(jsonarray.isNull(0)){
                        txtSemViagem.visibility = TextView.VISIBLE
                    }else{
                        linearCompraPassagem.visibility = LinearLayout.GONE
                        linearBtnBuscarNovamente.visibility = LinearLayout.VISIBLE
                        linearPassagens.visibility = LinearLayout.VISIBLE
                        txtSemViagem.visibility = TextView.GONE

                        adapter.clear()
                        for (i in 0 until jsonarray.length()) {

                            val jsonobject = jsonarray.getJSONObject(i)

                            viagem = Viagem(jsonobject.getInt("id"), jsonobject.getString("preco"), jsonobject.getString("origem"), jsonobject.getString("destino"), jsonobject.getString("hora_saida"), jsonobject.getString("hora_chegada"),
                                    jsonobject.getString("data_saida"), jsonobject.getString("data_chegada"), jsonobject.getString("km"), jsonobject.getString("endereco_chegada"), jsonobject.getString("endereco_saida"), jsonobject.getString("img"),
                                    jsonobject.getString("classe"), jsonobject.getString("poltronas"))

                            adapter.add(viagem)
                        }
                    }
                }
            }
        }

        list_view_passagem.setOnItemClickListener(OnItemClickListener { adapter, view, posicao, id ->
            // TODO Auto-generated method stub

            var intent = Intent(applicationContext, CompraPassagemSegundoPasso::class.java)

            var viagem = Viagem()

            viagem = adapter.getItemAtPosition(posicao) as Viagem

            intent.putExtra("id", viagem.getId().toString())

            startActivity(intent)
        })

        buscarPassagemNovamente.setOnClickListener{//DEIXA ITENS VISIVEIS E INVISIVEIS
            linearCompraPassagem.visibility = LinearLayout.VISIBLE
            linearBtnBuscarNovamente.visibility = LinearLayout.GONE
            linearPassagens.visibility = LinearLayout.GONE
            txtSemViagem.visibility = TextView.GONE
        }
    }



    //classe do adapter
    private inner class PassagemAdapter(ctx: Context, items: List<Viagem>) : ArrayAdapter<Viagem>(ctx, 0, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var v = convertView

            if (v == null) {
                v = LayoutInflater.from(context).inflate(R.layout.item_lista_passagem, null)
            }

            val item = getItem(position)

            val txtHrPart = v!!.findViewById<TextView>(R.id.txtHrPart)
            val txtHrChegada = v.findViewById<TextView>(R.id.txtHrChegada)
            val txtLocalSaida = v.findViewById<TextView>(R.id.txtLocalSaida)
            val txtLocalChegada = v.findViewById<TextView>(R.id.txtLocalChegada)
            val txtClasseOnibus = v.findViewById<TextView>(R.id.txtClasseOnibus)
            val txtNomeViagem = v.findViewById<TextView>(R.id.txtNomeViagem)
            val txtValor = v.findViewById<TextView>(R.id.txtValor)

            txtHrPart.setText("Partida: " + item.getHoraPartida())
            txtHrChegada.setText("Chegada: " + item.getHoraChegada())
            txtLocalSaida.setText("Local Saida: " + item.getEnderecoSaida())
            txtLocalChegada.setText("Local Chegada: " + item.getEnderecoChegada())
            txtClasseOnibus.setText("Classe: " + item.getTipo_classe())
            txtNomeViagem.setText(item.getOrigem() + " x " + item.getDestino())
            txtValor.setText("Valor : " + converterDinheiro(item.getValor()))

            return v
        }
    }
}
