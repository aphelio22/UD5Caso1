package com.example.ud5caso1.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ud5caso1.ComunidadAutonoma
import com.example.ud5caso1.R
import com.example.ud5caso1.adapter.ComunidadAutonomaAdapter
import com.example.ud5caso1.databinding.ActivityMainBinding
import com.example.ud5caso1.domain.ComunidadDAO
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var comunidadDAO: ComunidadDAO = ComunidadDAO()
    private lateinit var comunidadAfectada: ComunidadAutonoma
    private lateinit var listaComunidades: List<ComunidadAutonoma>
    private lateinit var binding: ActivityMainBinding
    private lateinit var intentLaunch: ActivityResultLauncher<Intent>
    private var nombreComunidad = "Sin nombre"
    private var id: Int = 0
    private lateinit var adapter: ComunidadAutonomaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }

        })

        Thread.sleep(2000)
        splashScreen.setKeepOnScreenCondition{false}
        listaComunidades = comunidadDAO.cargarLista(this)
        binding.rvComunidades.layoutManager = LinearLayoutManager(this)
        adapter = ComunidadAutonomaAdapter(listaComunidades) { comunidadAutonoma ->
                onItemSelected(comunidadAutonoma)
            }
        
        binding.rvComunidades.adapter = adapter
        this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })
        intentLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.rvComunidades.adapter = adapter
                nombreComunidad = result.data?.extras?.getString("nombre").toString()
                id = result.data?.extras?.getInt("id") as Int
                listaComunidades[id].nombre = nombreComunidad
                adapter.updateList(listaComunidades)
                comunidadDAO.actualizarBBDD(this, listaComunidades[id])
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.borrar -> {
                listaComunidades = listaComunidades.minus(listaComunidades)
                comunidadDAO.cambiarEstadoEliminado(this)
                listaComunidades = comunidadDAO.cargarLista(this)
                adapter.updateList(listaComunidades)
                true
            }

            R.id.recargar -> {
                listaComunidades = listaComunidades.plus(listaComunidades)
                comunidadDAO.cambiarEstadoActivo(this)
                listaComunidades = comunidadDAO.cargarLista(this)
                adapter.updateList(listaComunidades)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        lateinit var miIntent: Intent
        comunidadAfectada = listaComunidades[item.groupId]
        when (item.itemId) {

            0 -> {
                val alert =
                    AlertDialog.Builder(this).setTitle("Eliminar ${comunidadAfectada.nombre}")
                        .setMessage(
                            "¿Estás seguro de que quieres eliminar ${comunidadAfectada.nombre}?"
                        )
                        .setNeutralButton("Cerrar", null).setPositiveButton("Aceptar") { _, _ ->
                            display("Se ha eliminado ${comunidadAfectada.nombre}")
                            listaComunidades = listaComunidades.minus(comunidadAfectada)
                            comunidadDAO.borrarDeBBDD(this, comunidadAfectada.nombre)
                            adapter.updateList(listaComunidades)
                        }.create()
                alert.show()
            }

            1 -> {
                miIntent = Intent(this, InfoActivity::class.java)
                miIntent.putExtra("nombreComunidad", listaComunidades[item.groupId].nombre)
                miIntent.putExtra("id", item.groupId)
                miIntent.putExtra("imagen", listaComunidades[item.groupId].imagen)
                intentLaunch.launch(miIntent)
            }

            2 -> {
                val intent = Intent(this, CameraActivity::class.java)
                intent.putExtra("comunidad", comunidadAfectada.nombre)
                intent.putExtra("id", comunidadAfectada.id)
                this.startActivity(intent)
            }

            3 -> {
                val intent = Intent(this, ActivityImage::class.java)
                intent.putExtra("id", comunidadAfectada.id)
                this.startActivity(intent)
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    private fun display(s: String) {
        Snackbar.make(binding.root, s, Snackbar.LENGTH_SHORT).show()
    }

    private fun onItemSelected(comunidadAutonoma: ComunidadAutonoma) {
        /*Activity de Google Maps habilitada, la activity de OpenStreetMaps es 'MapasActivity'.*/
        Toast.makeText(this, "Yo soy de ${comunidadAutonoma.nombre}", Toast.LENGTH_SHORT).show()
        lateinit var miIntent: Intent
        miIntent = Intent(this, MapsActivity::class.java)
        miIntent.putExtra("nombre", comunidadAutonoma.nombre)
        miIntent.putExtra("capital", comunidadAutonoma.capital)
        miIntent.putExtra("latitud", comunidadAutonoma.latitud)
        miIntent.putExtra("longitud", comunidadAutonoma.longitud)
        startActivity(miIntent)
    }

    /*
    private fun crearListaNueva(){
        ComunidadAutonomaProvider.listaComunidadAutonoma.clear()
        binding.rvComunidades.adapter?.notifyDataSetChanged()
    }
    */

}