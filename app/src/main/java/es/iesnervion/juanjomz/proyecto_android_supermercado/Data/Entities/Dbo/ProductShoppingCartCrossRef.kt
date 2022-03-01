package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo

import androidx.room.Entity

@Entity(primaryKeys = ["shoppingCartId", "productId"])
data class ProductShoppingCartCrossRef(
    val productId: Int,
    val shoppingCartId: Int
)