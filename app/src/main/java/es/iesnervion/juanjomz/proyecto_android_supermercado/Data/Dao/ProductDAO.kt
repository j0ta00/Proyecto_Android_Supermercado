package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.ProductDBO


@Dao
interface ProductDAO {

    //SELECT
    @Query("SELECT count(*) FROM products")
    fun getProductsRows(): Int

//    @Query("SELECT categoria FROM products")
//    fun getProductsCategories(): List<String>

    @Query("SELECT * FROM products" )//TODO HACER SUSPEND O NO
    fun getAllProducts() : List<ProductDBO>

    //INSERT
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products : List<ProductDBO> )

}