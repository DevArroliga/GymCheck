<?php
    require 'vendor/autoload.php';

    $mail = new PHPMailer\PHPMailer\PHPMailer(true);
    $usuario = $_POST["usuario"];
    $clave = $_POST["clave"];
    $email= $_POST["email"];

    //Configuración del servidor SMTP
    $mail->isSMTP();
    $mail->Host = 'smtp.gmail.com';
    $mail->SMTPAuth = true;
    $mail->Username = 'riverasoto.marcelo@gmail.com';
    $mail->Password = 'nnaiotomjjolwzer';
    $mail->SMTPSecure = 'tls';
    $mail->Port = 587;

    //Configuración del correo electrónico
    $mail->setFrom('riverasoto.marcelo@gmail.com', 'GymCheck');
    $mail->addAddress($email, 'GymCheck Bienvenida');
    $mail->Subject = 'Bienvenida';
    $mail->Body = "Bienvenido a GymCheck\nTu usuario es: \"".$usuario. "\" y la contraseña es: \"" .$clave. "\"";



    //Envío del correo electrónico
    if ($mail->send()) {
        echo 'El correo electrónico se envió correctamente.';
    } else {
        echo 'Error al enviar el correo electrónico: ' . $mail->ErrorInfo;
    }

?>