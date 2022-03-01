package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
@Entity
data class ShoppingCartWithProducts(
    @Embedded val shoppingCart : ShoppingCartDBO,
    @Relation(
        parentColumn = "shoppingCartId",
        entityColumn = "productId",
        associateBy = Junction(ProductShoppingCartCrossRef::class)
    )
    val productList : List<ProductDBO> = emptyList()
)