package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ProductBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.ShoppingCartBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Bo.UserBO
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Repositories.ProductRepository
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Repositories.ShoppingCartRepository
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val shoppingCartRepository: ShoppingCartRepository
) : ViewModel() {
    //Atributos
    private val isLoading = MutableLiveData<Boolean>()
    private val userBO = MutableLiveData<UserBO>()
    val productsList: MutableLiveData<List<ProductBO>> = MutableLiveData()
    val productSelectedDetails: MutableLiveData<ProductBO> = MutableLiveData()

    fun onCreate() {//TODO MEJORAR FORMA DE MIRAR SI NO HAY DATOS QUE LOS INSERTE SINO QUE NO INSERTE NADA
        viewModelScope.launch(Dispatchers.IO) {
            if (userRepository.getUsersRowsDao() == 0) {
                insertarUsuarioDefecto()
            }
            if (productRepository.getProductsRowsDao() == 0) {
                insertarProductosDefecto()
            }
        }
    }

    //Métodos
    //Hace una consulta select y retorna un booleano en función de si el usuario existe o no
    fun loginExists(usuario: String, contrasenia: String): Boolean {
        var existe = false;
        isLoading.postValue(true)
        val userFromDB = userRepository.getUserWithUsernameAndPasswordDao(usuario, contrasenia)
        if (userFromDB != null) {
            userBO.postValue(userFromDB!!)
            existe = true
        }
        isLoading.postValue(true)
        return existe
    }

    fun userExists(username: String): Boolean {
        var existe = true;
        val userNameDB = userRepository.getUsernameDao(username)
        if (userNameDB == null) {
            existe = false;
        }
        return existe
    }

    //Dado un usuario lo inserta en la BBDD y actualiza su valor en el VM
    fun insertUser(user: UserBO) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUserDao(user)
            userBO.postValue(user)
        }
    }

    //Este método cambia el valor del LiveData llamando al Repositorio
    //y la actividad se encarga de observarlo
    fun loadProductsList() {
        viewModelScope.launch(Dispatchers.IO) {
            productsList.postValue(productRepository.getProductsDao())
        }
    }

    fun updateProductSelected(productBO: ProductBO) {
        productSelectedDetails.postValue(productBO)
    }

    private suspend fun insertarUsuarioDefecto() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUserDao(
                UserBO(
                    "29565540Y",
                    "123",
                    "German",
                    "BC",
                    "AD",
                    "trancho@gmail.com",
                    "111111111"
                )
            )
            shoppingCartRepository.insertShoppingCartDao(
                ShoppingCartBO(
                    state = 1,
                    idUser = "29565540Y"
                )
            )
        }
    }

    private suspend fun insertarProductosDefecto() {
        val products = mutableListOf<ProductBO>()
        products.add(
            ProductBO(
                nombre = "Albondigas",
                precio = 2.5,
                categoria = "Enlatados",
                urlImagen = "https://ekonomator.com/wp_17/wp-content/uploads/2017/12/046869.jpg"
            )
        )
        products.add(
            ProductBO(
                nombre = "Peras",
                precio = 1.5,
                categoria = "Frutas",
                urlImagen = "https://perfumesyfragancias.online/wp-content/uploads/2018/10/poire.jpg"
            )
        )
        products.add(
            ProductBO(
                nombre = "Lentejas",
                precio = 2.0,
                categoria = "Legumbres",
                urlImagen = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlUAdYuET0AzIlvffu9nuqwwi_YLnp_tOV4Q&usqp=CAU"
            )
        )
        products.add(
            ProductBO(
                nombre = "Melon",
                precio = 3.2,
                categoria = "Frutas",
                urlImagen = "https://www.semana.es/wp-content/uploads/2020/06/destacada-1.jpg"
            )
        )
        products.add(
            ProductBO(
                nombre = "Costillas de Cerdo",
                precio = 8.0,
                categoria = "Carnes",
                urlImagen = "https://www.surtimovil.com/wp-content/uploads/2020/07/carne-de-cerdo-costilla-san-luis-750g-empacado-al-vacio.png"
            )
        )
        products.add(
            ProductBO(
                nombre = "Sandia",
                precio = 4.8,
                categoria = "Frutas",
                urlImagen = "https://w7.pngwing.com/pngs/630/1007/png-transparent-green-watermelon-illustration-fruit-and-vegetables-for-kids-fruit-and-vegetables-for-kids-child-drawing-watermelon-food-sphere-melon.png"
            )
        )
        products.add(
            ProductBO(
                nombre = "Fabada Asturiana",
                precio = 2.15,
                categoria = "Frutas",
                urlImagen = "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202102/11/00118030200051____5__600x600.jpg"
            )
        )
        products.add(
            ProductBO(
                nombre = "Carne de Kobe",
                precio = 800.0,
                categoria = "Carnes",
                urlImagen = "https://storage.contextoganadero.com/s3fs-public/blog/field_image/2017-10/b_carne.jpg"
            )
        )
        products.add(
            ProductBO(
                nombre = "Piña",
                precio = 4.8,
                categoria = "Frutas",
                urlImagen = "https://www.frutality.es/wp-content/uploads/pi%C3%B1a.png"
            )
        )
        products.add(
            ProductBO(
                nombre = "Garbanzos",
                precio = 2.3,
                categoria = "Legumbres",
                urlImagen = "https://www.pequerecetas.com/wp-content/uploads/2021/02/cocinar-garbanzos.jpg"
            )
        )
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertProductsDao(products)
        }
    }

    fun insertProductToCart() {
        //TODO AÑADIR A SHOPPING CART
    }


}