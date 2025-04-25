package com.example.examen_1_maria_calvo.adapters

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.examen_1_maria_calvo.Movimiento


//CAMBIAR A FLOAT

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "myplants.db"
        const val DATABASE_VERSION = 5

        private const val SQL_CREATE_TABLE_MYPLANTS =
            "CREATE TABLE ${Movimiento.TABLE_NAME} (" +
                    "${Movimiento.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Movimiento.COLUMN_NAME_CANTIDAD} REAL," +
                    "${Movimiento.COLUMN_NAME_FECHA} INTEGER"+ ")"


        private const val SQL_DROP_TABLE_TASK = "DROP TABLE IF EXISTS ${Movimiento.TABLE_NAME}"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MYPLANTS)
        Log.i("DATABASE", "Created table Movimientos")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onDestroy(db)
        onCreate(db)
    }

    private fun onDestroy(db: SQLiteDatabase) {
        db.execSQL(SQL_DROP_TABLE_TASK)
    }
}
