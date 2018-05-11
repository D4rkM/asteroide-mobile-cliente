package com.example.a16254868.usuarioasteroide

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_perfil_usuario.*
import kotlinx.android.synthetic.main.content_perfil_usuario.*


class PerfilUsuarioActivity : AppCompatActivity() {

    var PICK_IMAGE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        alterarPerfil.setOnClickListener {

            val intent = Intent(applicationContext, CadastroUsuarioActivity::class.java)

            intent.putExtra("tela", "perfil")

            startActivity(intent)
        }

        visualizarQRCode.setOnClickListener {

            startActivity(Intent(applicationContext, QRCodeUsuarioActivity::class.java))

        }

        alterarFoto.setOnClickListener {

            val i = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE)
        }

        foto_usuario.setOnClickListener {

            val i = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE) {
                val selectedImage = data.data

                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)//Transformando URI em bitmap

                (foto_usuario as ImageView).setImageBitmap(bitmap)//Setando imagem na tela
            }
        }
    }

    fun StringToBitMap(encodedString: String): Bitmap? {
        try {
            val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            return null
        }

    }

}
