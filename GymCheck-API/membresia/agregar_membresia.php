<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $nombre = $_POST["nombre"];
    $duracion = $_POST["duracion"];
    $precio= $_POST["precio"];
   

    $my_query = "INSERT INTO membresia (nombre, duracion, precio) VALUES ('".$nombre."', '".$duracion."','".$precio."')";

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