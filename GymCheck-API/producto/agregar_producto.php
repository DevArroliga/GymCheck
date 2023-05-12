<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $nombre = $_POST["nombre"];
    $descripcion = $_POST["descripcion"];
    $precio = $_POST["precio"];
    $stock = $_POST["stock"];
<<<<<<< HEAD

    // Leer la imagen desde la solicitud POST
    $imagen = $_FILES['img']['tmp_name'];
    $tipo_imagen = $_FILES['img']['type'];

    // Convertir la imagen a bytes para almacenarla en la base de datos
    $imagen_binaria = addslashes(file_get_contents($imagen));

    $my_query = "INSERT INTO producto (nombre, descripcion, precio, stock, img, estado) VALUES ('".$nombre."','".$descripcion."','".$precio."','".$stock."','".$imagen_binaria."', 1)";
=======
    
    // Obtiene la ruta temporal del archivo de imagen
    $imagen_temporal = $_FILES["img"]["tmp_name"];
    
    // Obtiene el nombre original del archivo de imagen
    $nombre_imagen = $_FILES["img"]["name"];

    // Crea la ruta completa de la imagen
    $img= "../imagenes/".$nombre_imagen;

    // Mueve el archivo de imagen de la ruta temporal al directorio de tu proyecto
    move_uploaded_file($imagen_temporal, $img);

    // Codifica la imagen en base64
    $base_datos= "data:image/jpeg;base64," . base64_encode(file_get_contents($img));

    $my_query = "INSERT INTO producto (nombre, descripcion, precio, stock, img, estado) VALUES ('".$nombre."','".$descripcion."','".$precio."','".$stock."','".$base_datos."', 1)";
>>>>>>> 878051de7e56c90658176ab7f7599c53fb42bfb3

    $result = $mysql -> query($my_query);
    if($result == true){
        echo "Producto guardado satisfactoriamente...";
    }
    else{
        echo "Error al guardar producto...";
    }
}
else{
    echo"Error desconocido";
}
<<<<<<< HEAD
=======


>>>>>>> 878051de7e56c90658176ab7f7599c53fb42bfb3
?>
