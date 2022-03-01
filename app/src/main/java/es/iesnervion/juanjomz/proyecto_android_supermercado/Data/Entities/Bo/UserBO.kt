package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo

data class UserBO(
    val id : String,
    val contrasenia : String,
    val nombre : String?,
    val apellido1 : String?,
    val apellido2 : String?,
    val email : String,
    val telefono : String?,
    val shoppingCartList : List<ShoppingCartBO> = emptyList()
    ) {
}