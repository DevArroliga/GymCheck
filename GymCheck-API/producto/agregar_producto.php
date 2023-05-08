<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $nombre = $_POST["nombre"];
    $descripcion = $_POST["descripcion"];
    $precio = $_POST["precio"];
    $stock = $_POST["stock"];
    $img = $_POST["img"];

    $my_query = "INSERT INTO producto (nombre, descripcion, precio, stock, img, estado) VALUES ('".$nombre."','".$descripcion."','".$precio."','".$stock."','".$img."', 1)";

    $result = $mysql -> query($my_query);
    if($result == true){
        echo "Registro guardado satisfactoriamente...";
    }
    else{
        echo "Error al guardar registro...";
    }
}
else{
    echo"Error desconocido";
}
?>