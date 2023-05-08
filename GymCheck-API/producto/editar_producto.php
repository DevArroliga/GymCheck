<?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';

        $idProducto = $_POST["id"];      
        $nombre = $_POST["nombre"];  
        $descripcion = $_POST["descripcion"];    
        $precio = $_POST["precio"];
        $stock= $_POST["stock"];
        $img = $_POST["img"];

        
        $my_query = "UPDATE producto SET nombre= '".$nombre."', descripcion= '".$descripcion."', precio= '".$precio."', stock= '".$stock."', img= '".$img."', estado= 2 WHERE id=".$idProducto;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Producto editado exitosamente';
        } else { 
            echo 'Error';
        }
    } else {
        echo 'Error desconocido';
    }

?>