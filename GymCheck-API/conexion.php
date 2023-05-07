<?php
    $mysql = new mysqli("localhost", "root", "", "gymcheck");
    if($mysql->connect_error){
        echo"Error: ";
        die("Error de conexion");
    }
?>