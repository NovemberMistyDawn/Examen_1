package com.example.examen_1_maria_calvo

import android.content.ContentValues
import android.util.Log
import com.example.examen_1_maria_calvo.adapters.DatabaseManager

class MovimientoDAO(private val databaseManager: DatabaseManager) {

    fun insert(movimiento: Movimiento) {
        val db = databaseManager.writableDatabase

        val values = ContentValues().apply {
            put(Movimiento.COLUMN_NAME_CANTIDAD, movimiento.cantidad)
            put(Movimiento.COLUMN_NAME_FECHA, movimiento.fechaIngreso)
        }

        db.insert(Movimiento.TABLE_NAME, null, values)
        db.close()
    }


    fun update(movimiento: Movimiento) {
        val db = databaseManager.writableDatabase
        val values = ContentValues().apply {
            put(Movimiento.COLUMN_NAME_CANTIDAD, movimiento.cantidad)
            put(Movimiento.COLUMN_NAME_FECHA, movimiento.cantidad)
        }

        try {
            db.update(Movimiento.TABLE_NAME, values, "${Movimiento.COLUMN_NAME_ID} = ?", arrayOf(movimiento.id.toString()))
            Log.i("DATABASE", "Updated plant with id: ${movimiento.id}")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun delete(movimiento: Movimiento) {
        val db = databaseManager.writableDatabase

        try {
            db.delete(Movimiento.TABLE_NAME, "${Movimiento.COLUMN_NAME_ID} = ?", arrayOf(movimiento.id.toString()))
            Log.i("DATABASE", "Deleted movimiento with id: ${movimiento.id}")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    fun findById(id: Long): Movimiento? {
        val db = databaseManager.readableDatabase

        val projection = arrayOf(
            Movimiento.COLUMN_NAME_ID,
            Movimiento.COLUMN_NAME_CANTIDAD,
            Movimiento.COLUMN_NAME_FECHA
        )

        val selection = "${Movimiento.COLUMN_NAME_ID} = ?"
        val selectionArgs = arrayOf(id.toString())

        var movimiento: Movimiento? = null

        try {
            val cursor = db.query(
                Movimiento.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            if (cursor.moveToNext()) {
                val movimientoId = cursor.getLong(cursor.getColumnIndexOrThrow(Movimiento.COLUMN_NAME_ID))
                val cantidad = cursor.getFloat(cursor.getColumnIndexOrThrow(Movimiento.COLUMN_NAME_CANTIDAD))
                val fecha = cursor.getLong(cursor.getColumnIndexOrThrow(Movimiento.COLUMN_NAME_FECHA))

                movimiento = Movimiento(movimientoId,cantidad,fecha)
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }

        return movimiento
    }

    fun findAll(): List<Movimiento> {
        val db = databaseManager.readableDatabase
        val cursor = db.query(
            Movimiento.TABLE_NAME,
            null, null, null, null, null,
            "${Movimiento.COLUMN_NAME_FECHA} DESC"
        )

        val items = mutableListOf<Movimiento>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(Movimiento.COLUMN_NAME_ID))
                val cantidad = getFloat(getColumnIndexOrThrow(Movimiento.COLUMN_NAME_CANTIDAD))
                val fecha = getLong(getColumnIndexOrThrow(Movimiento.COLUMN_NAME_FECHA))
                items.add(Movimiento(id, cantidad, fecha))
            }
        }
        cursor.close()
        db.close()
        return items
    }
}