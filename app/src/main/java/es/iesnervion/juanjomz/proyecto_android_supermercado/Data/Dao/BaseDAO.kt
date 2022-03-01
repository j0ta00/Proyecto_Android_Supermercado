package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao

import androidx.room.Delete
import androidx.room.Insert

interface BaseDAO<T> {

    @Insert
    fun insertT(o : T)

    @Delete
    fun deleteT(o : T)

    //TODO VER COMO HACER ESTO AL TERMINAR EL PROGRAMA
}