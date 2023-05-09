<?php
    
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        require_once '../conexion.php';
        $idProducto = $_POST["idProducto"];
        $my_query = "UPDATE producto SET estado = 3 WHERE idProducto =".$idProducto;
        $result = $mysql->query($my_query);

        if($result == true){
            echo 'Producto eliminado exitosamente';
        } else { 
            echo 'Error';
        }
    } else {
        echo 'Error desconocido';
    }

?>