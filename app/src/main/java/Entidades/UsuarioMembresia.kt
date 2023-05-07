package Entidades

data class UsuarioMembresia (
    val idPersonaMembresia: Int,
    val idUsuario: Int,
    val idMembresia: Int,
    val fechaMembresia: String // Tipo de dato String, pero sera insertado con un formato de tipo Date en la base de datos.
)