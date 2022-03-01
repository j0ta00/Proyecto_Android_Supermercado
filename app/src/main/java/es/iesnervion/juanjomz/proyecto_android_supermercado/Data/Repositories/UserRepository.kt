package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Repositories


import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao.UserDAO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.UserBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Utils.toDBO
import javax.inject.Inject

//Aqui es donde hago los mapeados y llamo a la dao
class UserRepository @Inject constructor(
    private val userDao: UserDAO
) {

    fun getUsersRowsDao() : Int{
        return userDao.getUsersRows()
    }

    fun getUserWithUsernameAndPasswordDao(usuario: String, contrasenia: String): UserBO? {
        //En caso de no ser null devuelve un usuario, sino un null directamente
        return userDao.getUserWithUsernameAndPassword(usuario, contrasenia)?.toBO()
    }

    fun getUsernameDao(username : String): String? {
        return userDao.getUsername(username)
    }

    suspend fun insertUserDao(userBO: UserBO) {
        userDao.insertUser(userBO.toDBO())
    }
}