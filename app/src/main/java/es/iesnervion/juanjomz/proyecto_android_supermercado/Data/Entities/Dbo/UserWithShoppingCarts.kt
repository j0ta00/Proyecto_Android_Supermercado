package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
@Entity
data class UserWithShoppingCarts(
    @Embedded val user : UserDBO,
    @Relation(
        parentColumn = "usuario",//TODO
        entityColumn = "idUser"
    )
    val shoppingCarts : List<ShoppingCartDBO>
)