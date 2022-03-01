package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "shopping_carts")
class ShoppingCartDBO(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val state: Int,
    val idUser: String
)
