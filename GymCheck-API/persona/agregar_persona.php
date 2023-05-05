<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'Conexion.php';

    $nombres = $_POST["nombres"];
    $apellido = $_POST["apellido"];
    $fechaNac= $_POST["fechaNac"];
    $correo= $_POST["correo"];
    $fechaMem =$_POST["fechaMem"];

    $my_query = "INSERT INTO Coordinador (nombres, apellidos, fechaNac, correo, fechaMem) VALUES ('".$nombres."', '".$apellidos."','".$fechaNac."', '".$correo."', '".$fechaMem."')";

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