<?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';

        $idAnuncio = $_POST["id"];      
        $tituloAnuncio = $_POST["tituloAnuncio "];
        $descripcion = $_POST["descripcion"];
        $fecha = $_POST["fecha"];
        $img = $_POST["img"];

        
        $my_query = "UPDATE anuncio SET tituloAnuncio= '".$tituloAnuncio."', descripcion= '".$descripcion."', fecha= '".$fecha."', img= '".$img."', estado= 2 WHERE id=".$idAnuncio;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Anuncio editado exitosamente';
        } else { 
            echo 'Error';
        }
    } else {
        echo 'Error desconocido';
    }

?>