<?php
    require ('../conexion/conexion.php');

    $id = $_POST["id"];
    $nombre = $_POST['nombre'];
    $fecha = $_POST['fecha'];
    $hora = $_POST['hora'];
    $motivo = $_POST['motivo'];
    $idAdmin = $_POST['idAdmin'];

    $sql = "UPDATE citas SET Nombre='$nombre', Fecha='$fecha', Hora='$hora', Motivo='$motivo' WHERE Identificador =".$id;

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location:../../paginas/controlCitas/controlCitas.php?id=$idAdmin");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }
?>