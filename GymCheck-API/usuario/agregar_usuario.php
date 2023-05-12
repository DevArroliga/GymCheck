<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';
    $usuario = $_POST["usuario"];
    $clave = $_POST["clave"];
    $cedula= $_POST["cedula"];
    $fechaMembresia = $_POST["fechaMembresia"];

    $my_query = "INSERT INTO usuario (usuario, clave, activo, fechaMembresia, cedula) 
    VALUES('".$usuario."', '".$clave."', 1 , '".$fechaMembresia."', '".$cedula."')";
    $result = $mysql -> query($my_query);
    if($result == true){
        echo "Usuario guardado satisfactoriamente...";
    }else{
        echo "Error al guardar registro...";
    }
}else{
    echo"Error desconocido";
}
?>


