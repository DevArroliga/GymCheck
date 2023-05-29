<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    require_once '../conexion.php';

    $idProducto = $_POST["idProducto"];
    $nombre = $_POST["nombre"];
    $descripcion = $_POST["descripcion"];
    $precio = $_POST["precio"];
    $stock = $_POST["stock"];

    // Establecer la conexión a la base de datos utilizando MySQLi
    $mysqli = new mysqli("localhost", "root", "", "gymcheck");

    // Verificar si la conexión fue exitosa
    if ($mysqli->connect_errno) {
        echo "Error al conectar a la base de datos: " . $mysqli->connect_error;
        exit;
    }

    // Escapar los valores y construir la consulta SQL
    $nombre = $mysqli->real_escape_string($nombre);
    $descripcion = $mysqli->real_escape_string($descripcion);
    $precio = $mysqli->real_escape_string($precio);
    $stock = $mysqli->real_escape_string($stock);

    // Preparar la consulta de actualización
    $query = "UPDATE producto SET nombre = '$nombre', descripcion = '$descripcion', precio = '$precio', stock = '$stock', estado = 2 WHERE idProducto = '$idProducto'";

    // Ejecutar la consulta
    if ($mysqli->query($query)) {
        echo 'Producto editado exitosamente';
    } else {
        echo 'Error en la consulta de actualización: ' . $mysqli->error;
    }

    // Cerrar la conexión
    $mysqli->close();
} else {
    echo 'Error desconocido';
}
?>
