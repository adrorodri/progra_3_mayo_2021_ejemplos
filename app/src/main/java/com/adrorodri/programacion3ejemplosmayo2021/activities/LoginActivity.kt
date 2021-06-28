package com.adrorodri.programacion3ejemplosmayo2021.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.adrorodri.programacion3ejemplosmayo2021.model.Usuario
import com.adrorodri.programacion3ejemplosmayo2021.persistency.SharedPreferencesController
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


class LoginActivity : AppCompatActivity() {
    lateinit var buttonLogin: AppCompatButton

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText
    lateinit var textViewRegistrate: TextView

    val requestCodePermissions = 123

    val sharedPrefsController = SharedPreferencesController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this, getString(R.string.iniciar_sesion), Toast.LENGTH_SHORT).show()

        buttonLogin = findViewById(R.id.buttonLogin)

        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextPassword = findViewById(R.id.editTextPassword)
        textViewRegistrate = findViewById(R.id.textViewRegistrate)

        buttonLogin.setOnClickListener {
            val username = editTextUsuario.text.toString()
            val password = editTextPassword.text.toString()

            if(username.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Algun campo esta vacio!!", Toast.LENGTH_SHORT).show()

                val toastView = layoutInflater.inflate(R.layout.layout_toast, null)
                val toastTextView = toastView.findViewById<TextView>(R.id.textViewToast)
                toastTextView.text = "Usuario o password vacio!!"

                val toast = Toast(this)
                toast.duration = Toast.LENGTH_LONG
                toast.view = toastView
                toast.show()
            } else {
                val usuario = Usuario(editTextUsuario.text.toString(), editTextPassword.text.toString())
                sharedPrefsController.guardarUsuario(this, usuario)
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
            }
        }

        textViewRegistrate.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // One Time Login!
        if(sharedPrefsController.obtenerUsuario(this) != null) {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }

        EasyPermissions.requestPermissions(
            PermissionRequest.Builder(this,
                requestCodePermissions,
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .setRationale("La app requiere permisos para funcionar!")
                .setPositiveButtonText("OK")
                .setNegativeButtonText("Cancelar")
                .build()
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}