<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';

    $nombre = $_POST["nombre"];
    $apellido = $_POST["apellido"];
    $fechaNac= $_POST["fechaNac"];
    $correo= $_POST["correo"];
    $fechaMem =$_POST["fechaMem"];

    $my_query = "INSERT INTO persona (nombre, apellido, fechaNac, correo, fechaMem) VALUES ('".$nombre."', '".$apellido."','".$fechaNac."', '".$correo."', '".$fechaMem."')";

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