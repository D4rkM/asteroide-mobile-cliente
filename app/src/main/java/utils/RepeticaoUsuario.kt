package utils

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.widget.Toast
import com.example.a16254868.usuarioasteroide.HttpConnection
import com.example.a16254868.usuarioasteroide.Usuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray

/**
 * Created by 16254868 on 20/04/2018.
 */
fun repetirUsuario(loginUsuario:String, senhaUsuario:String, context:Context, f:(u: Usuario)->Unit){

    var usuario = Usuario()

    T.doAsync {

        val url = "http://10.107.144.9:3000/api/v1/autenticar/cliente"

        val map = HashMap<String, String>()
        map.put("login", loginUsuario)
        map.put("senha", senhaUsuario)

        val resultado = HttpConnection.post(url, map)

        Log.d("API", resultado)


        uiThread {

            val jsonarray = JSONArray(resultado)

            //Verificando se a senha ou usuário estão corretas
            if(jsonarray.isNull(0)){
                Toast.makeText(context, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
            }else{
                for (i in 0 until jsonarray.length()) {

                    val jsonobject = jsonarray.getJSONObject(i)

                    usuario = Usuario(jsonobject.getInt("id"), jsonobject.getString("nome"), jsonobject.getString("login"), jsonobject.getString("cpf"), jsonobject.getString("email"), jsonobject.getString("datanasc"),
                            jsonobject.getString("sexo"), jsonobject.getString("telefone"), jsonobject.getString("celular"), jsonobject.getString("rg"))

                    f.invoke(usuario)

                }
            }
        }
    }
}