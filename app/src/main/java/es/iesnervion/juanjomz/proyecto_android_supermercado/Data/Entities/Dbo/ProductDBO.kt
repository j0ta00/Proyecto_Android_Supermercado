package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDBO(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val codigo: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "precio") val precio: Double,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "urlImagen") val urlImagen : String
)