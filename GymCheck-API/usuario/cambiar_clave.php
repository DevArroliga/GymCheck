<?php

    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $cedula = $_POST["cedula"];
        $clave = $_POST["clave"];
        $my_query = "update usuario set clave= '".$clave."'
        where cedula= '".$cedula."'";
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