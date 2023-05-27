<?php
require_once '../conexion.php';

if ($mysql->connect_error) {
    die("Conexion fallida: " . $mysql->connect_error);
}


$sql = "SELECT idAnuncio, tituloAnuncio, descripcion, fecha, img FROM anuncio WHERE estado = 1";
$result = $mysql->query($sql);

if ($result->num_rows >= 0) {
    $anuncios = array();
    while ($row = $result->fetch_assoc()) {
        $anuncio = array();
        $anuncio['idAnuncio'] = $row['idAnuncio'];
        $anuncio['tituloAnuncio'] = $row['tituloAnuncio'];
        $anuncio['descripcion'] = $row['descripcion'];
        $anuncio['fecha'] = $row['fecha'];
        $anuncio['img'] = base64_encode($row['img']);
        array_push($anuncios, $anuncio);
    }
    header('Content-Type: application/json');
    echo json_encode($anuncios);
} else {
    echo "No se encontraron anuncios";
}
$mysql->close();
?>