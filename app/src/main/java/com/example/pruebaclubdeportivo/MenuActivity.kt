package com.example.pruebaclubdeportivo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MenuActivity : AppCompatActivity() {

    private lateinit var dbHelper:UserDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        dbHelper = UserDBHelper(this)
        mostrarSocios()

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
                mostrarSocios()

            } else {
                Toast.makeText(this, "Error al agregar socio", Toast.LENGTH_SHORT).show()
            }
            editNombre.text.clear()
            editDni.text.clear()
            }
        }

    private fun mostrarSocios() {
        val listView = findViewById<ListView>(R.id.listSocios)
        val lista = dbHelper.obtenerSocios()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { _, _, position, _ ->
          val item = lista[position]
          val dni = item.substringAfterLast("-").trim()

          AlertDialog.Builder(this)
              .setTitle("Eliminar")
              .setMessage("¿Estás seguro de eliminar al socio: $item")
              .setPositiveButton("Si"){ _, _ ->
                  dbHelper.eliminarSocioPorDni(dni)
                  mostrarSocios()
                  Toast.makeText(this, "Socio eliminado", Toast.LENGTH_SHORT).show()
              }
              .setNegativeButton("no", null)
              .show()
            true
        }   // Juan Pérez -         36666552

      }
    }
