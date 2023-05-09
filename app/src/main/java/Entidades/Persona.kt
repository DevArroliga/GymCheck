package Entidades

import java.util.Date

data class Persona (
    val nombre: String,
    val apellido: String,
    val fechaNac: String, // Tipo de dato String, pero sera insertado con un formato de tipo Date en la base de datos.
    val correo: String,
    val cedula: String
)