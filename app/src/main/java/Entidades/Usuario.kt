package Entidades

import java.util.*

data class Usuario (
    val idUsuario: Int?,
    val usuario: String,
    val clave: String,
    val fechaMembresia: String?,
    val cedula: String,
    val idMembresia: Int?
)