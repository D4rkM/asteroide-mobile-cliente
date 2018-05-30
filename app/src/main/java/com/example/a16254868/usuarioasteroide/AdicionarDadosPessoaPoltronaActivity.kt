package com.example.a16254868.usuarioasteroide

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_adicionar_dados_pessoa_poltrona.*
import kotlinx.android.synthetic.main.content_adicionar_dados_pessoa_poltrona.*
import models.Usuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import utils.*
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class AdicionarDadosPessoaPoltronaActivity : AppCompatActivity() {

    private val myCalendar = Calendar.getInstance()
    private var etDate: EditText? = null

    var BRAZIL = Locale("pt", "BR")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_dados_pessoa_poltrona)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var intent = intent

        val pol = intent.getStringExtra("poltrona")
        val pol2 = intent.getIntArrayExtra("poltronasSelecionadas")
        val arrayNumPoltronaComNomePosition = intent.getIntArrayExtra("arrayNumPoltronaComNomePosition")
        var sexo = ""

        txtCPF.addTextChangedListener(MaskEditUtil.mask(txtCPF, "###.###.###-##"))
        txtRG.addTextChangedListener(MaskEditUtil.mask(txtRG, "##.###.###-#"))

        //CRIANDO UM DATE PICKER DIALOG PARA O USUÁRIO SELECIONAR SUA DATA DE NASCIMENTO
        val datePickerListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, BRAZIL)
            etDate!!.setText(sdf.format(myCalendar.getTime()))
        }


        etDate = findViewById<View>(R.id.et_date) as EditText
        etDate!!.setOnClickListener(View.OnClickListener {
            DatePickerDialog(this, datePickerListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        })

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

        //BUSCAR CPF PRA VERIFICAR SE EXISTE USUÁRIO
        txtCPFCompraPassagem.setOnFocusChangeListener{ v, hasFocus ->
            if (!hasFocus) {
                /*val cpf = txtCPFCompraPassagem.text.toString()

                var usuario = Usuario()

                //executar tarefas em segundo plano
                doAsync {

                    val url = ipServidorComPorta() +"/api/v1/clientecpf/cliente"

                    val map = HashMap<String, String>()
                    map.put("cpf", cpf)

                    val resultado = HttpConnection.post(url, map)

                    Log.d("API", resultado)

                    uiThread {

                        val jsonarray = JSONArray(resultado)

                        //Verificando se a senha ou usuário estão corretas
                        if(jsonarray.isNull(0)){
                            Toast.makeText(applicationContext, "Usuário não existente, adicione alguns dados", Toast.LENGTH_SHORT).show()
                            linearNome.visibility = LinearLayout.VISIBLE
                            linearCPF.visibility = LinearLayout.VISIBLE
                            linearRG.visibility = LinearLayout.VISIBLE
                            linearSexo.visibility = LinearLayout.VISIBLE
                            linearDatanasc.visibility = LinearLayout.VISIBLE
                        }else{
                            for (i in 0 until jsonarray.length()) {

                                val jsonobject = jsonarray.getJSONObject(i)

                                usuario = Usuario(jsonobject.getInt("id"), jsonobject.getString("nome"), jsonobject.getString("login"), jsonobject.getString("cpf"), jsonobject.getString("email"), jsonobject.getString("datanasc"),
                                        jsonobject.getString("sexo"), jsonobject.getString("telefone"), jsonobject.getString("celular"), jsonobject.getString("rg"))

                            }
                        }
                    }
                }*/
                linearNome.visibility = LinearLayout.VISIBLE
                linearCPF.visibility = LinearLayout.VISIBLE
                linearRG.visibility = LinearLayout.VISIBLE
                linearSexo.visibility = LinearLayout.VISIBLE
                linearDatanasc.visibility = LinearLayout.VISIBLE
            }
        }

        //ADICIONAR DADOS ADICIONAIS
        btnFinalizarAddDados.setOnClickListener {

            //TRATAMENTOS NAS CAIXAS DE TEXTO
            /*if(txtCPF.text.toString().equals("")){
                txtCPF.setError("Esse campo é obrigatório")
            }else if(txtRG.text.toString().equals("")){
                txtRG.setError("Esse campo é obrigatório")
            }else if(txtNomeCompletoUser.text.toString().equals("")){
                txtRG.setError("Esse campo é obrigatório")
            }else {

                if(sexoMasc.isChecked){
                    sexo = "M"
                }else {
                    sexo = "F"
                }

                doAsync {

                    val url = ipServidorComPorta() +"/api/v1/cliente"

                    val map = java.util.HashMap<String, String>()
                    map.put("nome", txtNomeCompletoUser.text.toString())
                    map.put("email", "")
                    map.put("telefone", "")
                    map.put("celular", "")
                    map.put("senha", "")
                    map.put("datanasc", et_date.text.toString())
                    map.put("login", "")
                    map.put("sexo", sexo)
                    map.put("cpf", txtCPF.text.toString())
                    map.put("rg", txtRG.text.toString())

                    val resultado = HttpConnection.post(url, map)

                    Log.d("API", resultado)

                    uiThread {
                        //Code
                    }
                }

                Toast.makeText(applicationContext, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()

                intent = Intent(applicationContext, AdicionarPessoasPoltronaActivity::class.java)

                intent.putExtra("poltronasSelecionadas", pol2)
                intent.putExtra("nome", txtNomeCompletoUser.text.toString())
                intent.putExtra("poltrona", pol)

                startActivity(intent)

            }*/
            intent = Intent(applicationContext, AdicionarPessoasPoltronaActivity::class.java)

            intent.putExtra("poltronasSelecionadas", pol2)
            intent.putExtra("nome", txtNomeCompletoUser.text.toString())
            intent.putExtra("poltrona", pol)
            intent.putExtra("primeiraPoltrona", "1")
            intent.putExtra("segundaPoltrona", "0")
            intent.putExtra("arrayNumPoltronaComNomePosition", arrayNumPoltronaComNomePosition)
            startActivity(intent)

        }
    }

    fun mascaras(){
        txtCPF.addTextChangedListener(MaskEditUtil.mask(txtCPF, "###.###.###-##"))
        txtRG.addTextChangedListener(MaskEditUtil.mask(txtRG, "##.###.###-#"))
    }
}
