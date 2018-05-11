package com.example.a16254868.usuarioasteroide

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_qrcode_usuario.*
import kotlinx.android.synthetic.main.content_qrcode_usuario.*
import net.glxn.qrgen.android.QRCode

class QRCodeUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_usuario)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val text = "Passagem validada"//Texto que virá para confimarção da compra da passagem

        val bitmap = QRCode.from(text).withSize(1000, 1000).bitmap() //Criando Imagem do QRCODE

        (imgQRCode as ImageView).setImageBitmap(bitmap)//Setando imagem na tela

        //hideKeyBoard()

    }

    /*fun hideKeyBoard(){
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }*/

}
