package com.example.a16254868.usuarioasteroide

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_usuario_segunda_parte.*
import kotlinx.android.synthetic.main.content_cadastro_usuario_segunda_parte.*
import models.Usuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import utils.*
import java.text.SimpleDateFormat
import java.util.*

/////////////////////ARRRRRRRRRRRRRRUMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR A LISTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
class CadastroUsuarioSegundaParteActivity : AppCompatActivity() {

    private val myCalendar = Calendar.getInstance()
    private var etDate: EditText? = null

    var BRAZIL = Locale("pt", "BR")

    var sexo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario_segunda_parte)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var usuario = Usuario()

        val preferencias = getSharedPreferences("Cliente", Context.MODE_PRIVATE)

        val login = preferencias.getString("login", "")
        val senha = preferencias.getString("senha", "")

        //DEIXANDO O RADIO BUTTON MASCULINO CHECKED
        sexoMasc.isChecked = true

        /*CRIANDO MASCARAS COM A CLASSE MaskEditUtil*/
        mascaras()

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

        val tela:String = intent.getStringExtra("tela")
        val nomeCompleto:String = intent.getStringExtra("NomeUser")
        val user:String = intent.getStringExtra("user")
        val senhaUser:String = intent.getStringExtra("senhaUser")
        val email:String = intent.getStringExtra("emailUser")

        if(tela.equals("perfil")){
            tltTelaCadastroPerfilSegundaParte.text = "Seu Perfil"
            botaoCadastrarUsuario.text = "Atualizar"

            repetirUsuario(login, senha, applicationContext) {

                usuario = it

                preencherCampos(usuario)
            }

            //Atualizar Usuário
            botaoCadastrarUsuario.setOnClickListener {
                if(sexoMasc.isChecked){
                    sexo = "M"
                }else {
                    sexo = "F"
                }

                doAsync {

                    val url = ipServidorComPorta() +"/api/v1/cliente/"+usuario.getId().toString()

                    val map = HashMap<String, String>()
                    map.put("nome", nomeCompleto)
                    map.put("login", user)
                    map.put("email", email)
                    map.put("senha", senhaUser)
                    map.put("datanasc", etDate!!.text.toString())
                    map.put("sexo", sexo)
                    map.put("telefone", txtTelefone.text.toString())
                    map.put("celular", txtCelular.text.toString())
                    map.put("cpf", txtCPF.text.toString())
                    map.put("rg", txtRG.text.toString())

                    val resultado = HttpConnection.post(url, map)

                    Log.d("API", resultado)

                    uiThread {
                        //Code
                    }
                }

                preferencias.edit().putString("login", user).apply()

                if(senhaUser != ""){
                    preferencias.edit().putString("senha", senhaUser).apply()
                }else{
                    preferencias.edit().putString("senha", senha).apply()
                }

                toast("Dados atualizados com sucesso!")


            }
        }else{
            botaoCadastrarUsuario.setOnClickListener {

                //TRATAMENTOS NAS CAIXAS DE TEXTO
                if(etDate!!.text.toString().equals("")){
                    etDate!!.setError("Esse campo é obrigatório")
                }else if(txtTelefone.text.toString().equals("")){
                    txtTelefone.setError("Esse campo é obrigatório")
                }else if(txtCelular.text.toString().equals("")){
                    txtCelular.setError("Esse campo é obrigatório")
                }else if(txtCPF.text.toString().equals("")){
                    txtCPF.setError("Esse campo é obrigatório")
                }else if(txtRG.text.toString().equals("")){
                    txtRG.setError("Esse campo é obrigatório")
                }else if(txtCEP.text.toString().equals("")){
                    txtCEP.setError("Esse campo é obrigatório")
                }else if(txtNumeroCasa.text.toString().equals("")){
                    txtNumeroCasa.setError("Esse campo é obrigatório")
                }else if(txtLogradouro.text.toString().equals("")){
                    txtLogradouro.setError("Esse campo é obrigatório")
                }else if(txtBairro.text.toString().equals("")){
                    txtBairro.setError("Esse campo é obrigatório")
                }else if(txtComplemento.text.toString().equals("")){
                    txtComplemento.setError("Esse campo é obrigatório")
                }else if(txtCidade.text.toString().equals("")){
                    txtCidade.setError("Esse campo é obrigatório")
                }else if(txtEstado.text.toString().equals("")){
                    txtEstado.setError("Esse campo é obrigatório")
                }else {

                    if(sexoMasc.isChecked){
                        sexo = "M"
                    }else {
                        sexo = "F"
                    }

                    doAsync {

                        val url = ipServidorComPorta() +"/api/v1/cliente"

                        val map = HashMap<String, String>()
                        map.put("nome", nomeCompleto)
                        map.put("login", user)
                        map.put("email", email)
                        map.put("senha", senhaUser)
                        map.put("datanasc", etDate!!.text.toString())
                        map.put("sexo", sexo)
                        map.put("telefone", txtTelefone.text.toString())
                        map.put("celular", txtCelular.text.toString())
                        map.put("cpf", txtCPF.text.toString())
                        map.put("rg", txtRG.text.toString())

                        val resultado = HttpConnection.post(url, map)

                        Log.d("API", resultado)

                        uiThread {
                            //Code
                        }
                    }

                    Toast.makeText(applicationContext, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(applicationContext, MainActivity::class.java))

                }

            }
        }

        //Verificando se o EditText perdeu o foco
        /*txtCEP.setOnFocusChangeListener{ v, hasFocus ->
            if (!hasFocus) {
                val cep = txtCEP.text.toString()

                //executar tarefas em segundo plano
                doAsync {

                    //Lendo a internet e retornando o resultado em json
                    val resultado = URL(ipServidorComPorta() +"/api/v1/busca_endereco/:cep?$cep").readText()

                    //transformando json pra objeto
                    val retornoJson = JSONObject(resultado)

                    //pegando cada variavel
                    val logradouro = retornoJson.getString("logradouro")
                    val cidade = retornoJson.getString("localidade")
                    val bairro = retornoJson.getString("bairro")

                    Log.d("API", resultado)

                    //executa na Thread principal
                    uiThread {
                        txtBairro.setText(bairro.toString())
                    }
                }
            }
        }*/

    }

    //METODO PARA CRIAR MASCARAS PARA AS CAIXAS DE TEXTO
    public fun mascaras(){
        txtTelefone.addTextChangedListener(MaskEditUtil.mask(txtTelefone, "### ####-####"))
        txtCelular.addTextChangedListener(MaskEditUtil.mask(txtCelular, "### #####-####"))
        txtCPF.addTextChangedListener(MaskEditUtil.mask(txtCPF, "###.###.###-##"))
        txtRG.addTextChangedListener(MaskEditUtil.mask(txtRG, "##.###.###-#"))
        txtCEP.addTextChangedListener(MaskEditUtil.mask(txtCEP, "#####-###"))
    }

    fun preencherCampos(usuario: Usuario){

        etDate!!.setText(converterDataParaSistema(usuario.getDatanasc().toString()))
        txtTelefone.setText(usuario.getTelefone().toString())
        txtCelular.setText(usuario.getCelular().toString())
        txtCPF.setText(usuario.getCpf().toString())
        txtRG.setText(usuario.getRg().toString())

        if(usuario.getSexo().toString().equals("M")){
            sexoMasc.isChecked = true
        }else{
            sexoFem.isChecked = true
        }
    }

}
