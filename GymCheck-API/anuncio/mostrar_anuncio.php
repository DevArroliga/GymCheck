<?php
require_once '../conexion.php';

if ($mysql->connect_error) {
    die("Conexion fallida: " . $mysql->connect_error);
}

// Función para mostrar los registros de una tabla
function mostrar($anuncio) {
    global $mysql;
    $sql = "SELECT * FROM anuncio WHERE estado<>3";
    $result = $mysql->query($sql);
    $rows = array();
    while($r = mysqli_fetch_assoc($result)) {
        $rows[] = $r;
    }
    
    return json_encode($rows);
}

echo mostrar('anuncio');
?>