package com.example.a16254868.usuarioasteroide

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker

import kotlinx.android.synthetic.main.activity_cadastro_usuario_segunda_parte.*
import kotlinx.android.synthetic.main.content_cadastro_usuario_segunda_parte.*
import java.text.SimpleDateFormat
import java.util.*
import android.widget.EditText
import android.widget.Toast
import org.jetbrains.anko.editText
import org.jetbrains.anko.toast


class CadastroUsuarioSegundaParteActivity : AppCompatActivity() {

    private val myCalendar = Calendar.getInstance()
    private var etDate: EditText? = null

    var BRAZIL = Locale("pt", "BR")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario_segunda_parte)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        botaoCadastrarUsuario.setOnClickListener {

            var sexo = ""

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
                txtCEP.setError("Esse campo é obrigatório")
            }else if(txtLogradouro.text.toString().equals("")){
                txtNumeroCasa.setError("Esse campo é obrigatório")
            }else if(txtBairro.text.toString().equals("")){
                txtNumeroCasa.setError("Esse campo é obrigatório")
            }else if(txtComplemento.text.toString().equals("")){
                txtNumeroCasa.setError("Esse campo é obrigatório")
            }else if(txtCidade.text.toString().equals("")){
                txtNumeroCasa.setError("Esse campo é obrigatório")
            }else if(txtEstado.text.toString().equals("")){
                txtNumeroCasa.setError("Esse campo é obrigatório")
            }else {

                if(sexoMasc.isChecked){
                    sexo = "M"
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }else {
                    sexo = "F"
                }

                Toast.makeText(applicationContext, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show()
            }

        }

    }

    //METODO PARA CRIAR MASCARAS PARA AS CAIXAS DE TEXTO
    fun mascaras(){
        txtTelefone.addTextChangedListener(MaskEditUtil.mask(txtTelefone, "### ####-####"))
        txtCelular.addTextChangedListener(MaskEditUtil.mask(txtCelular, "### #####-####"))
        txtCPF.addTextChangedListener(MaskEditUtil.mask(txtCPF, "###.###.###-##"))
        txtRG.addTextChangedListener(MaskEditUtil.mask(txtRG, "##.###.###-#"))
        txtCEP.addTextChangedListener(MaskEditUtil.mask(txtCEP, "#####-###"))
    }

}
