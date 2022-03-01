package es.iesnervion.juanjomz.proyecto_android_supermercado.Data.Entities.Dbo

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class UserDBO(
    @PrimaryKey @ColumnInfo(name = "usuario") val dni: String,
    @ColumnInfo(name = "contrasenia") val contrasenia: String,
    @Nullable @ColumnInfo(name = "nombre") val nombre: String?,
    @Nullable @ColumnInfo(name = "apellido1") val apellido1: String?,
    @Nullable @ColumnInfo(name = "apellido2") val apellido2: String?,
    @ColumnInfo(name = "email") val email: String,
    @Nullable @ColumnInfo(name = "telefono") val telefono: String?
)