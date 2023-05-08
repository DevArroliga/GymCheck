package Entidades

data class Usuario (
    val idUsuario: Int,
    val usuario: String,
    val clave: String,
    val activo: Boolean,
    val idPersona: Int
)