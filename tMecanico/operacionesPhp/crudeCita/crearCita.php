<?php
    require ('../conexion/conexion.php');

    $nombre = $_POST['nombre'];
    $fecha = $_POST['fecha'];
    $hora = $_POST['hora'];
    $motivo = $_POST['motivo'];
    $idAdmin = $_POST['idA'];
    
    

    $sql = "INSERT INTO citas (Nombre, Fecha, Hora, Motivo, id_administrador) VALUES ('$nombre','$fecha', '$hora', '$motivo','$idAdmin')";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){    
        header("Location: ../../paginas/controlCitas/controlCitas.php?id=$idAdmin");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>