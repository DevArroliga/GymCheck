<?php
    require 'vendor/autoload.php';

    $mail = new PHPMailer\PHPMailer\PHPMailer(true);
    $usuario = $_POST["usuario"];
    $clave = $_POST["clave"];
    $email= $_POST["email"];

    $cuerpoEmail = "<div style='background-color: #F2F5FA; padding: 20px; height:450px; width: 730px;'>";
    $cuerpoEmail .= "<div style='background: linear-gradient(to top, #009933 0%, #003366 100%); padding: 20px; border-radius: 15px; height: 410px; position: relative;'>";
    $cuerpoEmail .= "<div>";
    $cuerpoEmail .= "<img src='https://i.imgur.com/XYdxYA4.png' width='80px' style='display: inline-block; vertical-align: middle;>'";
    $cuerpoEmail .= "</div>";
    $cuerpoEmail .= "<p style='font-size: 18px; color: #fff; margin: 30px 10px;'>Bienvenido a GYM-CHECK, se le ha asignado unas credenciales:</p>";
    $cuerpoEmail .= "<div style='background-color: rgba(220, 220, 220, 0.5); padding: 20px; border-radius: 15px; position: absolute; bottom: 0; width:650px; margin-bottom: 35px'>";
    $cuerpoEmail .= "<h2 style='color: #fff;'>DATOS DEL USUARIO</h2>";
    $cuerpoEmail .= "<p style='color: #fff;'><strong>USUARIO: </strong>\"" .$usuario. "\"</p>";
    $cuerpoEmail .= "<p style='color: #fff;'><strong>CONTRASEÑA: </strong>\"" .$clave. "\"</p>";
    $cuerpoEmail .= "</div>";
    $cuerpoEmail .= "</div>";
    $cuerpoEmail .= "</div>";

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
    $mail->Body = $cuerpoEmail;
    $mail->isHTML(true);



    //Envío del correo electrónico
    if ($mail->send()) {
        echo 'El correo electrónico se envió correctamente.';
    } else {
        echo 'Error al enviar el correo electrónico: ' . $mail->ErrorInfo;
    }

?>