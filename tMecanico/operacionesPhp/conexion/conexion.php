<?php
    $servidor = "bdphp.ctxtxmvx7oms.us-east-1.rds.amazonaws.com";
    $nombreusuario = "admin123";
    $password = "admin123";
    $db = "administrador";

    $conexion = new mysqli($servidor, $nombreusuario, $password, $db);

    if($conexion->connect_error){        
        header("location: ../../paginas/presentacion/nodisponible.html");
    }

?>