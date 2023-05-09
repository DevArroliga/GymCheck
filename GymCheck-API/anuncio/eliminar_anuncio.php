<?php
    
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $idAnuncio = $_POST["idAnuncio"];
        $my_query = "UPDATE anuncio SET estado = 3 WHERE idAnuncio=".$idAnuncio;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Anuncio eliminado exitosamente';
        } else { 
            echo 'Error';
        }
    } else {
        echo 'Error desconocido';
    }

?>