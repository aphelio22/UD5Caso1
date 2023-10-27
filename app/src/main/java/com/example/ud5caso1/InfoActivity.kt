package com.example.ud5caso1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.ud5caso1.databinding.ActivityInfoBinding
import com.example.ud5caso1.databinding.ActivityMainBinding

class InfoActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityInfoBinding
    private lateinit var btnCambiar: Button
    private lateinit var btnCancelar: Button
    private lateinit var infoNombre: EditText
    private lateinit var infoImagen: ImageView
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombreComunidad")
        val imagen = intent.getIntExtra("imagen", 0)
        btnCambiar = binding.btCambiar
        btnCambiar.setOnClickListener(this)
        btnCancelar = binding.btCancelar
        btnCancelar.setOnClickListener(this)
        infoNombre = binding.etInfoActivity
        infoImagen = binding.ivInfoActivity
        infoImagen.setImageResource(imagen)
        infoNombre.hint = nombre
        id = intent.getIntExtra("id", 0)

    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.btCambiar -> {
                val intent = Intent(this, MainActivity::class.java)
                val name = infoNombre.text.toString()

                if(name != "") {
                    intent.putExtra("nombre", name)
                    intent.putExtra("id", id)
                    setResult(RESULT_OK, intent)
                }
                finish()
            }
            R.id.btCancelar -> {
                finish()
            }
        }
    }
}