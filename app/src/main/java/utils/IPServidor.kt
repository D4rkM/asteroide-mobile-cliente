package utils

/**
 * Created by 16254868 on 02/05/2018.
 * Classe para retornar IP do Servidor com porta e sem porta
 */
fun ipServidorComPorta():String{

    //var ip = "http://10.107.144.4:3000"
    var ip = "http://192.168.0.1:3000"

    return ip
}

fun ipServidorSemPorta():String{

    //var ip = "http://10.107.1449.4"
    var ip = "http://192.168.0.1"

    return ip
}

fun ipServidorBlog():String{
    var ip = "http://10.0.2.2/inf4m/asteroide/views/interacao.php"

    return ip
}