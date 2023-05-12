<?php
<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $nombre = $_POST["nombre"];
    $descripcion = $_POST["descripcion"];
    $precio = $_POST["precio"];
    $stock = $_POST["stock"];
    
    // Obtiene la ruta temporal del archivo de imagen
    $imagen_temporal = $_FILES["img"]["tmp_name"];
    
    // Lee el archivo de imagen como un string binario
    $imagen_binaria = file_get_contents($imagen_temporal);

    // Escapa caracteres especiales y convierte el string binario a un string hexadecimal
    $imagen_hexadecimal = $mysql->real_escape_string(bin2hex($imagen_binaria));

    $my_query = "INSERT INTO producto (nombre, descripcion, precio, stock, img, estado) VALUES ('".$nombre."','".$descripcion."','".$precio."','".$stock."',UNHEX('".$imagen_hexadecimal."'), 1)";

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


?>
