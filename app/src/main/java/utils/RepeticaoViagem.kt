package utils

import android.content.Context
import android.icu.lang.UCharacter
import android.util.Log
import android.widget.Toast
import models.Viagem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

/**
 * Created by 16254868 on 29/05/2018.
 */
fun repetirViagem(idViagem:String, context: Context, f:(u: Viagem)->Unit){

    var viagem = Viagem()

    UCharacter.GraphemeClusterBreak.T.doAsync {

        val url = ipServidorComPorta() +"/api/v1/buscaviagemporid"

        val map = HashMap<String, String>()
        map.put("id", idViagem)

        val resultado = HttpConnection.post(url, map)

        Log.d("APIBusca", resultado)

        uiThread {

            val jsonarray = JSONArray(resultado)

            //Verificando se a senha ou usuário estão corretas
            if(jsonarray.isNull(0)){
                Toast.makeText(context, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
            }else{
                for (i in 0 until jsonarray.length()) {

                    val jsonobject = jsonarray.getJSONObject(i)

                    viagem = Viagem(jsonobject.getInt("id"), jsonobject.getString("preco"), jsonobject.getString("origem"), jsonobject.getString("destino"), jsonobject.getString("hora_saida"), jsonobject.getString("hora_chegada"),
                            converterDataParaSistema(jsonobject.getString("data_saida").substring(0, 10)), converterDataParaSistema(jsonobject.getString("data_chegada").substring(0, 10)), jsonobject.getString("km"), jsonobject.getString("endereco_chegada"), jsonobject.getString("endereco_saida"), jsonobject.getString("img"),
                            jsonobject.getString("classe"), jsonobject.getString("poltronas"))

                    f.invoke(viagem)//CHAMANDO FUNÇÃO DE CALLBACK PARA RETORNAR DADOS DA UITHREAD

                }
            }
        }
    }
}

fun poltronasSelecionadas(idViagem:String, context: Context, f:(u: Int)->Unit){

    var numPoltrona = 0

    UCharacter.GraphemeClusterBreak.T.doAsync {

        val url = ipServidorComPorta() +"/api/v1/registro_poltronas"

        val map = HashMap<String, String>()
        map.put("id_viagem", idViagem)

        val resultado = HttpConnection.post(url, map)

        Log.d("APITEste", resultado)

        uiThread {

            val jsonarray = JSONArray(resultado)

            //Verificando se a senha ou usuário estão corretas
            if(jsonarray.isNull(0)){
                Toast.makeText(context, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
            }else{
                for (i in 0 until jsonarray.length()) {

                    val jsonobject = jsonarray.getJSONObject(i)

                    numPoltrona = jsonobject.getInt("num_poltrona")

                    f.invoke(numPoltrona)//CHAMANDO FUNÇÃO DE CALLBACK PARA RETORNAR DADOS DA UITHREAD

                }
            }
        }
    }
}