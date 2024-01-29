package com.example.ud5caso1.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.example.ud5caso1.R
import com.example.ud5caso1.databinding.ActivityImageBinding
import com.example.ud5caso1.domain.ComunidadDAO

class ActivityImage : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.extras!!.getInt("id")
        val miDao = ComunidadDAO()
        val comunidadElegida = miDao.getComunidadById(this, id)
        if (comunidadElegida.uri.isNotEmpty()) {
            val uri = Uri.parse(comunidadElegida.uri)
            binding.ivImageVisualizer.load(uri)
        } else {
            Toast.makeText(this, "${comunidadElegida.nombre} no tiene una foto asociada", Toast.LENGTH_SHORT).show()
        }
    }
}