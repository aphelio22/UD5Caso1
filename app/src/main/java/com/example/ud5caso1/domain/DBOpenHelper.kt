package com.example.ud5caso1.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ud5caso1.adapter.ComunidadAutonomaProvider
import java.lang.Exception

class DBOpenHelper private constructor(context: Context?) :
            SQLiteOpenHelper(context, ComunidadContract.NOMBRE_BD, null, ComunidadContract.VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(
                    "CREATE TABLE ${ComunidadContract.Companion.Entrada.NOMBRE_TABLA}"
                            + "(${ComunidadContract.Companion.Entrada.COLUMNA_ID} INTEGER NOT NULL"
                            + ",${ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE} NVARCHAR(20) NOT NULL"
                            + ",${ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN} INTEGER NOT NULL" +
                            ",${ComunidadContract.Companion.Entrada.COLUMNA_HABITANTES} INTEGER NOT NULL" +
                            ",${ComunidadContract.Companion.Entrada.COLUMNA_CAPITAL} NVARCHAR(100)" +
                            ",${ComunidadContract.Companion.Entrada.COLUMNA_LATITUD} REAL NOT NULL" +
                            ",${ComunidadContract.Companion.Entrada.COLUMNA_LONGITUD} REAL NOT NULL" +
                            ",${ComunidadContract.Companion.Entrada.COLUMNA_ICONO} INTEGER NOT NULL" +
                            ",${ComunidadContract.Companion.Entrada.COLUMNA_ESTADO} TEXT CHECK(${ComunidadContract.Companion.Entrada.COLUMNA_ESTADO} IN ('activo', 'eliminado')) DEFAULT 'activo'" +
                             ",${ComunidadContract.Companion.Entrada.COLUMNA_URI} NVARCHAR(100) NOT NULL);")

                // Insertar datos en la tabla
                inicializarBBDD(sqLiteDatabase)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${ComunidadContract.Companion.Entrada.NOMBRE_TABLA};")
            onCreate(sqLiteDatabase)
        }

        private fun inicializarBBDD(db: SQLiteDatabase) {
            val lista = ComunidadAutonomaProvider.listaComunidadAutonoma
            for (comunidad in lista) {
                db.execSQL(
                    ("INSERT INTO ${ComunidadContract.Companion.Entrada.NOMBRE_TABLA}(" +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_ID}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_HABITANTES}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_CAPITAL}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_LATITUD}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_LONGITUD}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_ICONO}," +
                            "${ComunidadContract.Companion.Entrada.COLUMNA_URI})" +
                            " VALUES (${comunidad.id},'${comunidad.nombre}',${comunidad.imagen},${comunidad.habitantes},'${comunidad.capital}',${comunidad.latitud},${comunidad.longitud},${comunidad.icono},'${comunidad.uri}');")
                )
            }
        }

        companion object {
            private var dbOpen: DBOpenHelper? = null
            fun getInstance(context: Context?): DBOpenHelper? {
                if (dbOpen == null) dbOpen = DBOpenHelper(context)
                return dbOpen
            }
        }
    }

