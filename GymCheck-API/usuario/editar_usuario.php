<?php

    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $idUsuario = $_POST["idUsuario"];
        $clave = $_POST["clave"];
        $activo = $_POST["activo"];
        $my_query = "update usuario set clave= '".$clave."', activo= '".$activo."' 
        where idUsuario=".$idUsuario;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Registro editado exitosamente';
        } else { 
            echo 'ERROR';
        }
    } else {
        echo 'Error desconocido';
    }

?>