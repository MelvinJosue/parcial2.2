package com.example.parcial22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //se cre el boton donde se hara el pedido
        txtpedir.setOnClickListener {
            if(nombre.text.isEmpty()||producto.text.isEmpty()||numero.text.isEmpty()){
                //se verifica por medio de toast si estan llenos los cuadros o mejor dicho los parametros
                Toast.makeText(this,"Asegurese que esten llenios los cuadros",Toast.LENGTH_LONG).show()

            }
            else{
                //unas vez llenada los parametros se almacenan
                var nombre: String=nombre.text.toString()
                var producto: String=producto.text.toString()
                var numero: String=numero.text.toString()
                //se llama a la operacion hecha
                operacion(nombre,producto,numero)
                //si se han llenado enviara este mensaje cuando se hayan guardado en la base de datos.
                Toast.makeText(this,"Se ha logrado corectamente el pedido",Toast.LENGTH_LONG).show()



            }
            //se crea el boton que lleve a la poagina del creador en este caso yo
            btncreador.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity3::class.java)
                startActivity(intent)
            }


        }


    }
    //se envian los datos a la base de datos.
    fun  operacion (nombre:String, producto:String, numero: String){
        val orden = HashMap<String,String>()
        //los valores dados en la base de datos.
        orden["Nombre del cliente"]=nombre.toString()
        orden["Producto elegido"]=producto.toString()
        orden["Cantidad dicha"]=numero.toString()
        //se trae la base de datos y el nombre de ella
        val rootRef= FirebaseDatabase.getInstance().reference
        val tasksRef=rootRef.child("pedido").push()
        tasksRef.setValue(orden)
    }
    }





