package Entidades

data class Anuncio (
    val idAnuncio: Int?,
    val tituloAnuncio: String,
    val descripcion: String,
    val fecha: String, // Tipo de dato String, pero sera insertado con un formato de tipo Date en la base de datos.
    val img: ByteArray? // Por ver este tipo de dato.
)