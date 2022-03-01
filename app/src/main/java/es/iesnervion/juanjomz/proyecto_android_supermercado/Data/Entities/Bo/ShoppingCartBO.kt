package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo

data class ShoppingCartBO(
    val id: Int = 0,
    val state : Int,
    val idUser : String,
    val productList : List<ProductBO> = emptyList()
) {


}