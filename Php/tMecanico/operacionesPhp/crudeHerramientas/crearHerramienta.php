<?php
    require ('../conexion/conexion.php');

    $nombre = $_POST['nombre'];
    $marca = $_POST['marca'];
    $descripcion = $_POST['descripcion'];
    $cantidad = $_POST['cantidad'];
    $idAdmin = $_POST['id'];
    

    $sql = "INSERT INTO herramientas (Nombre, Marca,Descripcion, Cantidad,Id) VALUES ('$nombre','$marca', '$descripcion', '$cantidad', '$idAdmin')";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){    
        header("Location: ../../paginas/controlHerramientas/controlHerramientas.php?");
    }else{
        echo "lo siento";
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>