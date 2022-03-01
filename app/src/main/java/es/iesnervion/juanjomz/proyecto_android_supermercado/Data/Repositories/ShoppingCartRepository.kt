package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Repositories


import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao.ShoppingCartDAO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ShoppingCartBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Utils.toDBO
import javax.inject.Inject

class ShoppingCartRepository @Inject constructor(
    private val shoppingCartDAO: ShoppingCartDAO
) {
    fun insertShoppingCartDao(shoppingCartBO : ShoppingCartBO) {
        shoppingCartDAO.insertShoppingCart(shoppingCartBO.toDBO())
    }
}