package com.example.pruebaclubdeportivo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var dbHelper = UserDBHelper(this)

        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editDni = findViewById<EditText>(R.id.editDNI)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val cardShowForm = findViewById<TextView>(R.id.cardShowForm)
        val formLayout = findViewById<LinearLayout>(R.id.formLayout)

        cardShowForm.setOnClickListener{
            formLayout.visibility = View.VISIBLE
            cardShowForm.visibility = View.GONE
        }

        btnGuardar.setOnClickListener{
            val nombre = editNombre.text.toString().trim()
            val dni = editDni.text.toString().trim()

            if(dbHelper.insertarSocio(nombre,dni)){
                Toast.makeText(this, "Socio agregado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Error al agregar socio", Toast.LENGTH_SHORT).show()
            }
            editNombre.text.clear()
            editDni.text.clear()
            }
        }
    }
