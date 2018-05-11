package utils

import java.text.SimpleDateFormat

/**
 * Created by 16254868 on 18/04/2018.
 */
fun converterDataParaSistema(data:String): String {

    var data = data
    var f = SimpleDateFormat("yyyy-MM-dd")
    var dataParse = f.parse(data)

    var f2 = SimpleDateFormat("dd/MM/yyyy")

    var dataFormatada = f2.format(dataParse)

    return dataFormatada
}