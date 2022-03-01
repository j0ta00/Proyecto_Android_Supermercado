package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo

data class ProductBO(
    val codigo: Int = 0,
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val urlImagen: String
)