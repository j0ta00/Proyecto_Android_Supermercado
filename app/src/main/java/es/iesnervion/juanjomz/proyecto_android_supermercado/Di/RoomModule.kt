package es.iesnervion.juanjomz.proyecto_android_supermercado.Di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Room.SupermarketDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val SUPERMARKET_DATABASE_NAME = "supermarket_database"
    //Modulo para construir
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SupermarketDatabase::class.java, SUPERMARKET_DATABASE_NAME).allowMainThreadQueries().build()//TODO COMO HACER CON CORRUTINAS EL ALLOWMAINTHREADQUERIES

    //Modulo parq getUser
    @Singleton
    @Provides
    fun provideUserDao(db: SupermarketDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun provideProductDao(db: SupermarketDatabase) = db.getProductDao()

    @Singleton
    @Provides
    fun provideShoppingCartDao(db: SupermarketDatabase) = db.getShoppingCartDao()
}