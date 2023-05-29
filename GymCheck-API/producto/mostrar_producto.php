<?php
require_once '../conexion.php';

if ($mysql->connect_error) {
    die("Conexion fallida: " . $mysql->connect_error);
}

$sql = "SELECT idProducto, nombre, descripcion, precio, stock, img FROM producto WHERE estado = 1 OR estado = 2";
$result = $mysql->query($sql);

if ($result->num_rows >= 0) {
    $productos = array();
    while ($row = $result->fetch_assoc()) {
        $producto = array();
        $producto['idProducto'] = $row['idProducto'];
        $producto['nombre'] = $row['nombre'];
        $producto['descripcion'] = $row['descripcion'];
        $producto['precio'] = $row['precio'];
        $producto['stock'] = $row['stock'];
        $producto['img'] = base64_encode($row['img']);
        array_push($productos, $producto);
    }
    header('Content-Type: application/json');
    echo json_encode($productos);
} else {
    echo "No se encontraron productos";
}
$mysql->close();
?>
