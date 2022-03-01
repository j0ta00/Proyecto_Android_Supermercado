package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao

import androidx.room.*
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.UserDBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.UserWithShoppingCarts

@Dao
interface UserDAO {

    //SELECT
    @Transaction
    @Query("SELECT count(*) FROM users")
    fun getUsersRows() : Int

    @Transaction
    @Query("SELECT * FROM users WHERE usuario = :usuario AND contrasenia = :contrasenia")
    fun getUserWithUsernameAndPassword(usuario : String, contrasenia : String) : UserWithShoppingCarts? //TODO DUDA DONDE SE PONE QUE PUEDA SER NULLABLE

    @Transaction
    @Query("SELECT usuario FROM users WHERE usuario = :user")
    fun getUsername(user : String) : String?

    //INSERT
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDBO: UserDBO)
}