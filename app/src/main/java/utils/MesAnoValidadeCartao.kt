package utils

import java.util.*

fun retornarAnoValidadeCartao():MutableList<Int>{
    val cal = Calendar.getInstance()
    var year = cal.get(Calendar.YEAR)
    var arrayAnos = mutableListOf<Int>()


    for( i in year..2030 ){

        arrayAnos.add(i)

    }

    return arrayAnos
}

fun retornaMêsValidadeCartao():Array<String>{
    val meses = arrayOf("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro")

    return meses

}