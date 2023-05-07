<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';
    $uId = $_POST["uId"];
    $idMembresia = $_POST["idMembresia"];
    $fechaMembresia= $_POST["fechaMembresia"];
   
    $my_query = "insert into usuarioMembresia(uId, idMembresia, fechaMembresia) 
    values('".$uId."', '".$idMembresia."','".$fechaMembresia."')";
    $result = $mysql -> query($my_query);
    if($result == true){
        echo "La membresia del usuario ha sido guardado satisfactoriamente...";
    }else{
        echo "Error al guardar registro...";
    }
}else{
    echo"Error desconocido";
}
?>


