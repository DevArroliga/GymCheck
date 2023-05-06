<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once '../conexion.php';
    $usuario = $_POST["usuario"];
    $clave = $_POST["clave"];
    $activo= $_POST["activo"];
    $idPersona= $_POST["idPersona"];
    $my_query = "insert into usuario(usuario, clave, activo, idPersona) 
    values('".$usuario."', '".$clave."','".$activo."', '".$idPersona."')";
    $result = $mysql -> query($my_query);
    if($result == true){
        echo "Registro guardado satisfactoriamente...";
    }else{
        echo "Error al guardar registro...";
    }
}else{
    echo"Error desconocido";
}
?>


