<?php
    require ('../conexion/conexion.php');

    $id = $_POST["id"];
    $nombre = $_POST['nombre'];
    $telefono = $_POST['telefono'];
    $direccion = $_POST['direccion'];
    $correo = $_POST['correo'];
    $idAdmin = $_POST['idAdmin'];

    $sql = "UPDATE cliente SET Nombre='$nombre', Telefono='$telefono', Direccion='$direccion', Correo='$correo' WHERE Identificador =".$id;

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location:../../paginas/controlClientes/control.php?id=$idAdmin");
    }else{
        echo "lo siento";
    }
?>