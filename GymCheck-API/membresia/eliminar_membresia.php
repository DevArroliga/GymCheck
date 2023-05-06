<?php
    
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $idMembresia = $_POST["idMembresia"];
        $my_query = "delete from membresia where idMembresia =".$idMembresia;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Membresia eliminado exitosamente';
        } else { 
            echo 'ERROR';
        }
    } else {
        echo 'unknown error';
    }

?>