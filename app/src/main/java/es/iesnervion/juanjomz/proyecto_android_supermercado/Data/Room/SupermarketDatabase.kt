package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao.ProductDAO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao.ShoppingCartDAO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao.UserDAO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.ProductDBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.ShoppingCartDBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.UserDBO


@Database (entities = [UserDBO::class, ProductDBO::class, ShoppingCartDBO::class], version = 2)
abstract class SupermarketDatabase : RoomDatabase(){

    abstract fun getUserDao(): UserDAO

    abstract fun getProductDao() : ProductDAO

    abstract fun getShoppingCartDao() : ShoppingCartDAO
}