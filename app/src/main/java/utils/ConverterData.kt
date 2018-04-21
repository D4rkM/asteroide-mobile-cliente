package utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 16254868 on 18/04/2018.
 */
fun converterDataParaSistema(data:String): Date {

    val df1 = SimpleDateFormat("dd/MM/yyyy")

    val dataSistema = df1.parse(data)

    return  dataSistema
}