package com.example.ud5caso1.adapter

import com.example.ud5caso1.ComunidadAutonoma
import com.example.ud5caso1.R

class ComunidadAutonomaProvider {

    companion object {
            var listaComunidadAutonoma = mutableListOf(
                ComunidadAutonoma(0, "Andalucía", R.drawable.andalucia),
                ComunidadAutonoma(1, "Aragón", R.drawable.aragon),
                ComunidadAutonoma(2, "Asturias", R.drawable.asturias),
                ComunidadAutonoma(3, "Baleares", R.drawable.baleares),
                ComunidadAutonoma(4, "Canarias", R.drawable.canarias),
                ComunidadAutonoma(5, "Cantabria", R.drawable.cantabria),
                ComunidadAutonoma(6, "Castilla y León", R.drawable.castillaleon),
                ComunidadAutonoma(7, "Castilla La Mancha", R.drawable.castillamancha),
                ComunidadAutonoma(8, "Cataluña", R.drawable.catalunya),
                ComunidadAutonoma(9, "Ceuta", R.drawable.ceuta),
                ComunidadAutonoma(10, "Extremadura", R.drawable.extremadura),
                ComunidadAutonoma(11, "Galicia", R.drawable.galicia),
                ComunidadAutonoma(12, "La Rioja", R.drawable.larioja),
                ComunidadAutonoma(13, "Madrid", R.drawable.madrid),
                ComunidadAutonoma(14, "Melilla", R.drawable.melilla),
                ComunidadAutonoma(15, "Murcia", R.drawable.murcia),
                ComunidadAutonoma(16, "Navarra", R.drawable.navarra),
                ComunidadAutonoma(17, "País Vasco", R.drawable.paisvasco),
                ComunidadAutonoma(18, "Valencia", R.drawable.valencia)

            )
        var nuevaLista = listaComunidadAutonoma.toList().toMutableList()
    }
}