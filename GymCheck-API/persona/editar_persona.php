<?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';

        $idPersona = $_POST["idPersona"];      
        $correo = $_POST["correo"];

        $my_query = "UPDATE persona SET correo= '".$correo."' WHERE idPersona=".$idPersona;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Persona editada exitosamente';
        } else { 
            echo 'Error';
        }
    } else {
        echo 'Error desconocido';
    }

?>