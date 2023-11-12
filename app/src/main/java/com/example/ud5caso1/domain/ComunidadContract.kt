package com.example.ud5caso1.domain

import android.os.Build.VERSION
import android.provider.BaseColumns

class ComunidadContract {
    companion object{
        const val NOMBRE_BD = "comunidadesAutónomas"
        const val VERSION = 1
        class Entrada: BaseColumns{
            companion object {
                const val NOMBRE_TABLA = "comunidades"
                const val COLUMNA_ID = "id"
                const val COLUMNA_NOMBRE = "nombre"
                const val COLUMNA_IMAGEN = "imagen"
                const val COLUMNA_ESTADO = "estado"
            }
        }
    }
}