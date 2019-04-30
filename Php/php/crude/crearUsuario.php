<?php
    require ('../conexion/conexion.php');

    $nombre = $_POST['nombre'];
    $telefono = $_POST['telefono'];
    $direccion = $_POST['direccion'];
    $correo = $_POST['correo'];
    $idAdmin = $_POST['id'];
    

    $sql = "INSERT INTO cliente (Nombre, Telefono, Direccion, Correo, id_aministrador) VALUES ('$nombre','$telefono', '$direccion', '$correo', '$idAdmin')";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){    
        header("Location:http://localhost/tallerMecanico/control.php?id=$idAdmin");
    }else{
        echo "lo siento";
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>