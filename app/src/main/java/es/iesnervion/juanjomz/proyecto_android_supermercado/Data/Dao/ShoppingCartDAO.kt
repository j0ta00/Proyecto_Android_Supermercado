package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.ShoppingCartDBO


@Dao
interface ShoppingCartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingCart(shoppingCart: ShoppingCartDBO)
}