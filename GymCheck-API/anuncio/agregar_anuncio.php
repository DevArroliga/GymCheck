<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $tituloAnuncio = $_POST["tituloAnuncio"];
    $descripcion = $_POST["descripcion"];
    $fecha = $_POST["fecha"];
    
    
    // Obtiene la ruta temporal del archivo de imagen
    $imagen_temporal = $_FILES["img"]["tmp_name"];
    
    // Lee el archivo de imagen como un string binario
    $imagen_binaria = file_get_contents($imagen_temporal);

    // Escapa caracteres especiales y convierte el string binario a un string hexadecimal
    $imagen_hexadecimal = $mysql->real_escape_string(bin2hex($imagen_binaria));

    $my_query = "INSERT INTO anuncio (tituloAnuncio, descripcion, fecha, img, estado) VALUES ('".$tituloAnuncio."','".$descripcion."','".$fecha."',UNHEX('".$imagen_hexadecimal."'), 1)";

    $result = $mysql -> query($my_query);
    if($result == true){
        echo "El anuncio guardado satisfactoriamente...";
    }
    else{
        echo "Error al guardar anuncio...";
    }
}
else{
    echo"Error desconocido";
}



?>
