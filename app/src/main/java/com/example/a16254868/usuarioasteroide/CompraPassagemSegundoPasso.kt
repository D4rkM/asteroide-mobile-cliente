package com.example.a16254868.usuarioasteroide

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import kotlinx.android.synthetic.main.activity_compra_passagem_segundo_passo.*
import org.jetbrains.anko.toast

class CompraPassagemSegundoPasso : AppCompatActivity() {

    //var numeroList = arrayOf("1", "2")
    lateinit var numeroList:IntArray

    //internal var imagemList = intArrayOf(R.drawable.square_green, R.drawable.square_green)
    lateinit var imagemList:IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_passagem_segundo_passo)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val gridview: GridView = findViewById(R.id.gridview)

        var adapter =  GridAdapter (applicationContext, retornaArrayImagens(500))

        gridview.setAdapter(adapter)

        gridview.setOnItemClickListener { adapterView, view, i, l ->
            toast(i.toString())

            adapter =  GridAdapter (applicationContext, retornaArrayImagens(i))
            gridview.setAdapter(adapter)

            //startActivity(Intent(applicationContext, CompraPassagemTerceiroPassoActivity::class.java) )

        }

        /*gridview.adapter = ImageAdapter(this, 500)//500 é um número padrão para o position quando for iniciado o sistema

        gridview.onItemClickListener =
                AdapterView.OnItemClickListener { parent, v, position, id ->
                    Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
                    gridview.adapter = ImageAdapter(this, position)
                }*/

    }

    fun retornaArrayImagens(position: Int) : Poltrona{

        var poltrona = Poltrona()

        var x = 0

        while (x <= 3){

            if(x != position){//verificando se a poltrona está selecionada
                poltrona = Poltrona(R.drawable.square_green, x)
            }else{//verificando se a poltrona foi selecionada
                poltrona = Poltrona(R.drawable.square_red, x)
            }

            x = x + 1

        }

        return poltrona


        /*var x = 0

        var arrayImagens = arrayOf<Int>()
        var arrayNumeros = arrayOf<Int>()

        var green = R.drawable.square_green//quando a poltrona estiver livre
        var red = R.drawable.square_red//quando a poltrona estiver ocupada
        var yellow = R.drawable.square_yellow//quando a poltrona for selecionada

        while (x <= 10){

            if(x != position){//verificando se a poltrona está selecionada
                arrayImagens = arrayImagens + green
                arrayNumeros = arrayNumeros + x
            }else{//verificando se a poltrona foi selecionada
                arrayImagens = arrayImagens + yellow
                arrayNumeros = arrayNumeros + x
            }

            x = x + 1

        }

        return arrayOf(arrayImagens, arrayNumeros)*/
    }

    /*class ImageAdapter(private val mContext: Context, val position: Int) : BaseAdapter() {

        fun retornaArrayImagens(position: Int) : Array<Int>{
            var x = 0

            var array = arrayOf<Int>()

            var green = R.drawable.square_green//quando a poltrona estiver livre
            var red = R.drawable.square_red//quando a poltrona estiver ocupada
            var yellow = R.drawable.square_yellow//quando a poltrona for selecionada

            while (x < 32){

                if(x != position){//verificando se a poltrona está selecionada
                    array = array + green

                }else{//verificando se a poltrona foi selecionada
                    array = array + yellow

                }

                x = x + 1

            }

            return array
        }

        val imagens = retornaArrayImagens(position)

        override fun getCount(): Int = imagens.size

        override fun getItem(position: Int): Any? = null

        override fun getItemId(position: Int): Long = 0L

        // create a new ImageView for each item referenced by the Adapter
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val imageView: ImageView
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = ImageView(mContext)
                imageView.layoutParams = ViewGroup.LayoutParams(250, 250)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.setPadding(8, 8, 8, 8)
            } else {
                imageView = convertView as ImageView
            }

            if (convertView == null) {
                inflater
            }

            imageView.setImageResource(imagens[position])
            return imageView
        }
    }*/
}
