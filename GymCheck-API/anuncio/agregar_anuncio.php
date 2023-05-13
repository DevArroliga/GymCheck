<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $tituloAnuncio = $_POST["tituloAnuncio "];
    $descripcion = $_POST["descripcion"];
    $fecha = $_POST["fecha"];
    $img = $_POST["img"];

    $my_query = "INSERT INTO anuncio (tituloAnuncio, descripcion, fecha, img, estado) VALUES ('".$tituloAnuncio."','".$descripcion."','".$fecha."', '".$img."', 1)";

    $result = $mysql -> query($my_query);
    if($result == true){
        echo "Anuncio guardado satisfactoriamente...";
    }
    else{
        echo "Error al guardar anuncio...";
    }
}
else{
    echo"Error desconocido";
}
?>