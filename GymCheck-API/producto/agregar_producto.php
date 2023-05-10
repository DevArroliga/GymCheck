<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $nombre = $_POST["nombre"];
    $descripcion = $_POST["descripcion"];
    $precio = $_POST["precio"];
    $stock = $_POST["stock"];

    // Leer la imagen desde la solicitud POST
    $imagen = $_FILES['img']['tmp_name'];
    $tipo_imagen = $_FILES['img']['type'];

    // Convertir la imagen a bytes para almacenarla en la base de datos
    $imagen_binaria = addslashes(file_get_contents($imagen));

    $my_query = "INSERT INTO producto (nombre, descripcion, precio, stock, img, estado) VALUES ('".$nombre."','".$descripcion."','".$precio."','".$stock."','".$imagen_binaria."', 1)";

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
?>
