package com.example.examen_1_maria_calvo

class Movimiento (
    var id: Long,
    var cantidad: Float,
    var fechaIngreso:Long = 0L

){
companion object {
    const val TABLE_NAME = "Movimiento"

    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_CANTIDAD = "title"
    const val COLUMN_NAME_FECHA = "regada"
}

}