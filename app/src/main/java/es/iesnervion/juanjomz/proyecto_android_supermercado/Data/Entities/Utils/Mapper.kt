package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Utils

import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ProductBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ShoppingCartBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.UserBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.ProductDBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.ShoppingCartDBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.UserDBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo.UserWithShoppingCarts

fun UserDBO.toBO(): UserBO = UserBO(
    id = this.dni,
    nombre = this.nombre,
    contrasenia = this.contrasenia,
    apellido1 = this.apellido1,
    apellido2 = this.apellido2,
    email = this.email,
    telefono = this.telefono
)

fun UserBO.toDBO(): UserDBO = UserDBO(
    dni = this.id,
    nombre = this.nombre ?: "",
    contrasenia = this.contrasenia,
    apellido1 = this.apellido1 ?: "",
    apellido2 = this.apellido2 ?: "",
    email = this.email,
    telefono = this.telefono ?: ""
)

fun ProductDBO.toBO(): ProductBO = ProductBO(
    codigo = this.codigo,
    nombre = this.nombre,
    precio = this.precio,
    categoria = this.categoria,
    urlImagen = this.urlImagen
)

fun ProductBO.toDBO(): ProductDBO = ProductDBO(
    codigo = this.codigo,
    nombre = this.nombre,
    precio = this.precio,
    categoria = this.categoria,
    urlImagen = this.urlImagen
)

//MAPPER CHETO
fun ShoppingCartDBO.toBO() = ShoppingCartBO(
    id = this.id,
    state = this.state,
    idUser = this.idUser
)

fun ShoppingCartBO.toDBO() = ShoppingCartDBO(
    id = this.id,
    state = this.state,
    idUser = this.idUser
)

fun UserWithShoppingCarts.toBO(): UserBO = UserBO(
    id = this.user.dni,
    nombre = this.user.nombre,
    contrasenia = this.user.contrasenia,
    apellido1 = this.user.apellido1,
    apellido2 = this.user.apellido2,
    email = this.user.email,
    telefono = this.user.telefono,
    shoppingCartList = this.shoppingCarts.map { it.toBO() }
)