package com.example.ud5caso1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class ComunidadDAO {
    fun cargarLista (context: Context?): MutableList<ComunidadAutonoma> {
        lateinit var res: MutableList<ComunidadAutonoma>
        lateinit var c: Cursor
        try{
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            // val sql = "SELECT * FROM comunidades;"
            // c = db.rawQuery(sql, null)
            val columnas = arrayOf(ComunidadContract.Companion.Entrada.COLUMNA_ID,
                ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE,
                ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN)
            c = db.query(ComunidadContract.Companion.Entrada.NOMBRE_TABLA,
                columnas,null,null,null,null,null)
            res= mutableListOf()
            // Leer resultados del cursor e insertarlos en la lista
            while (c.moveToNext()) {
                val nueva = ComunidadAutonoma(c.getInt(0),c.getString(1),
                    c.getInt(2))
                res.add(nueva)
            }
        } finally {
            c.close()
        }
        return res
    }

    fun borrarDeBBDD(context: Context?, comunidad: ComunidadAutonoma){
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ComunidadContract.Companion.Entrada.COLUMNA_ID, comunidad.id)
        contentValues.put(ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE,comunidad.nombre)
        contentValues.put(ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN,comunidad.imagen)
        db.delete(ComunidadContract.Companion.Entrada.NOMBRE_TABLA, "id=?", arrayOf(comunidad.id.toString()))

        db.close()
    }

    fun actualizarBBDD(context: Context?, comunidad: ComunidadAutonoma) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        /*db.execSQL(
            "UPDATE frutas "
                    + "SET nombre='${fruta.nombre}' " +
                    "SET descripcion='${fruta.descripcion}'" +
                    "SET imagen='${fruta.imagen}'" +
                    "WHERE id=${fruta.id};"
        )
        */
        val values = ContentValues()
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_ID,comunidad.id)
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE,comunidad.nombre)
        values.put(ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN,comunidad.imagen)
        db.update(ComunidadContract.Companion.Entrada.NOMBRE_TABLA,values,"id=?",arrayOf(comunidad.id.toString()))
        db.close()
    }

    fun borrarTodoBBDD(context: Context?) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase

        // Eliminar todos los registros de la tabla
        db.delete(ComunidadContract.Companion.Entrada.NOMBRE_TABLA, null, null)
        db.close()
    }
}