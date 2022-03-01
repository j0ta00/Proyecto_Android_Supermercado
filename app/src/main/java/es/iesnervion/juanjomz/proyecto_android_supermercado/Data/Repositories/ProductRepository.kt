package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Repositories


import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Dao.ProductDAO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ProductBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Utils.toDBO
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDAO: ProductDAO
) {

    fun getProductsRowsDao(): Int{
        return productDAO.getProductsRows()
    }

    fun getProductsDao(): List<ProductBO> {
        val productsListDBO = productDAO.getAllProducts()
        return productsListDBO.map { it.toBO() }
    }

    suspend fun insertProductsDao(productList: List<ProductBO>) {//TODO COMO SE QUE SE HA INSERTADO CORRECTAMENTE? PQ EN C# DEVUELVE EL NÚMERO DE FILAS AFECTADAS
        productDAO.insertProducts(productList.map { it.toDBO() })
    }
}