package com.example.ud5caso1.domain

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.ud5caso1.ComunidadAutonoma

class ComunidadDAO {
    fun cargarLista(context: Context?): MutableList<ComunidadAutonoma> {
        lateinit var res: MutableList<ComunidadAutonoma>
        lateinit var c: Cursor
        try {
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            val sql = "SELECT * FROM comunidades WHERE estado = 'activo';"
            c = db.rawQuery(sql, null)
            res = mutableListOf()
            // Leer resultados del cursor e insertarlos en la lista
            while (c.moveToNext()) {
                val nueva = ComunidadAutonoma(
                    c.getInt(0), c.getString(1),
                    c.getInt(2), c.getInt(3), c.getString(4),
                    c.getDouble(5), c.getDouble(6), c.getInt(7)
                )
                res.add(nueva)
            }
        } finally {
            c.close()
        }
        return res
    }

    fun borrarDeBBDD(context: Context?, nombre: String) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.execSQL("UPDATE comunidades " + "SET estado='eliminado' " + "WHERE nombre='$nombre';")

        /*
        val contentValues = ContentValues()
        contentValues.put(ComunidadContract.Companion.Entrada.COLUMNA_ID, comunidad.id)
        contentValues.put(ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE,comunidad.nombre)
        contentValues.put(ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN,comunidad.imagen)
        db.delete(ComunidadContract.Companion.Entrada.NOMBRE_TABLA, "id=?", arrayOf(comunidad.id.toString()))
        */
        db.close()
    }

    fun actualizarBBDD(context: Context?, comunidad: ComunidadAutonoma) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase

        /*
        db.execSQL(
            "UPDATE frutas "
                    + "SET nombre='${fruta.nombre}' " +
                    "SET descripcion='${fruta.descripcion}'" +
                    "SET imagen='${fruta.imagen}'" +
                    "WHERE id=${fruta.id};"
        )
        */

        val values = ContentValues()
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_ID, comunidad.id)
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE, comunidad.nombre)
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN, comunidad.imagen)
        db.update(
            ComunidadContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id=?",
            arrayOf(comunidad.id.toString())
        )
        db.close()
    }

    /*
        fun borrarTodoBBDD(context: Context?) {
            val db = DBOpenHelper.getInstance(context)!!.writableDatabase
            db.delete(ComunidadContract.Companion.Entrada.NOMBRE_TABLA, null, null)
            db.close()
        }
     */

    fun cambiarEstadoActivo(context: Context?) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.beginTransaction()
        try {
            db.execSQL("UPDATE comunidades SET estado='activo'")
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        db.close()
    }

    fun cambiarEstadoEliminado(context: Context?) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.beginTransaction()
        try {
            db.execSQL("UPDATE comunidades SET estado='eliminado'")
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        db.close()
    }
}
