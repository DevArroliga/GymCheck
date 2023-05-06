<?php

    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $idMembresia = $_POST["idMembresia"];
        $nombre = $_POST["nombre"];
        $duracion = $_POST["duracion"];
        $precio = $_POST["precio"];
       
        $my_query = "update membresia set nombre= '".$nombre."', duracion= '".$duracion."' 
        , precio= '".$precio."' where idMembresia=".$idMembresia;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Membresia editado exitosamente';
        } else { 
            echo 'ERROR';
        }
    } else {
        echo 'Error desconocido';
    }

?>