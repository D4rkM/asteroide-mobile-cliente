package com.example.a16254868.usuarioasteroide

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_cadastro_usuario_terceiro_passo.*
import kotlinx.android.synthetic.main.content_cadastro_usuario_terceiro_passo.*
import models.Usuario
import models.Viagem
import org.jetbrains.anko.toast
import utils.repetirUsuario
import utils.repetirViagem
import utils.retornaMêsValidadeCartao
import utils.retornarAnoValidadeCartao
import java.text.NumberFormat
import java.util.*

class CompraPassagemTerceiroPassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario_terceiro_passo)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtCPFCompraPassagem.isEnabled = false


        cartaoCredito.setOnClickListener {
            deixaVisivelCartaoDeCredito("cartao")
        }

        boleto.setOnClickListener {
            deixaVisivelCartaoDeCredito("boleto")
        }

        val preferencias = getSharedPreferences("Cliente", Context.MODE_PRIVATE)

        val login = preferencias.getString("login", "")
        val senha = preferencias.getString("senha", "")

        val idViagem = intent.getStringExtra("id")

        var usuario = Usuario()

        repetirUsuario(login, senha, applicationContext) {

            usuario = it

            txtCPFCompraPassagem.setText(usuario.getCpf())
        }

        repetirViagem(idViagem, applicationContext){

            var viagem = it


            origemDestino.setText(viagem.getOrigem() + " --> " + viagem.getDestino())
            idaDataHorario.setText("Ida: " + viagem.getDataPartida() + " às " + viagem.getHoraPartida())
            voltaDataHora.setText("Volta: " + viagem.getDataChegada() + " às " + viagem.getHoraChegada())

            var bandeiraSelecionada = ""

            val poltronasSelecionadas = intent.getIntArrayExtra("poltronasSelecionadas")

            val qntidadePoltrona = poltronasSelecionadas.size

            val valorPassagemUnica = viagem.getValor().toDouble()

            val calPassagem = valorPassagemUnica * qntidadePoltrona.toInt()//CALCULO DAS PASSAGENS

            val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))//FORMATO DINEHEIRO REAL

            valorPassagem.setText(formato.format(java.lang.Double.parseDouble(calPassagem.toString())))//FOMATANDO PARA REAL

            var i = 0

            var txtPoltronas = ""

            while(i < poltronasSelecionadas.size){

                if(i < poltronasSelecionadas.size-1 ){//Verificando se não é a ultima poltronas selecionada

                    txtPoltronas = txtPoltronas + poltronasSelecionadas[i].toString() + ", "//Adicionando poltronas e uma virgula

                }else{//Se for a ultima poltrona ou a unica poltrona, não adiciona uma virgula

                    txtPoltronas = txtPoltronas + poltronasSelecionadas[i].toString()

                }

                i = i + 1
            }

            poltronasCompradas.setText("Poltrona(s): " + txtPoltronas)

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

            btnFinalizarCompras.setOnClickListener {

                if(bandeiraBancoDoBrasil.isChecked){
                    bandeiraSelecionada = "bandeiraBancoDoBrasil"
                }else if(bandeiraBradesco.isChecked){
                    bandeiraSelecionada = "bandeiraBradesco"
                }else if(bandeiraItau.isChecked){
                    bandeiraSelecionada = "bandeiraItau"
                }else if(bandeiraMasterCard.isChecked){
                    bandeiraSelecionada = "bandeiraMasterCard"
                }else if(bandeiraVisa.isChecked){
                    bandeiraSelecionada = "bandeiraVisa"
                }
                toast(txtPoltronas)
            }
        }
    }



    fun deixaVisivelCartaoDeCredito(visibilidade:String){

        if(visibilidade.equals("cartao")){
            txtTltBandeiras.visibility = TextView.VISIBLE
            linearIMGBandeiras.visibility = LinearLayout.VISIBLE
            rdosBandeiras.visibility = RadioGroup.VISIBLE
            tltCPF.visibility = TextView.VISIBLE
            txtCPFCompraPassagem.visibility = EditText.VISIBLE
            txtNumCartaoCompraPassagem.visibility = EditText.VISIBLE
            linearMesAnoCartao.visibility = LinearLayout.VISIBLE
            linearSpinnerMesAno.visibility = LinearLayout.VISIBLE
            btnFinalizarCompras.visibility = Button.VISIBLE
        }

        if(visibilidade.equals("boleto")){
            txtTltBandeiras.visibility = TextView.GONE
            linearIMGBandeiras.visibility = LinearLayout.GONE
            rdosBandeiras.visibility = RadioGroup.GONE
            txtNumCartaoCompraPassagem.visibility = EditText.GONE
            linearMesAnoCartao.visibility = LinearLayout.GONE
            linearSpinnerMesAno.visibility = LinearLayout.GONE
            btnFinalizarCompras.visibility = Button.VISIBLE
            tltCPF.visibility = TextView.VISIBLE
            txtCPFCompraPassagem.visibility = EditText.VISIBLE
        }

    }
}
