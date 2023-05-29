<?php

    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $idUsuario = $_POST["idUsuario"];
        $my_query = "DELETE FROM usuario WHERE idUsuario= .$idUsuario. ";
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Registro eliminado exitosamente';
        } else {     
            echo 'ERROR';
        }
    } else {
        echo 'Error desconocido';
    }

?>