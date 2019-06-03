<?php
    require ('../conexion/conexion.php');

    $id = $_POST['id'];
    $nombre = $_POST['nombre'];
    $marca = $_POST['marca'];
    $descripcion = $_POST['descripcion'];
    $cantidad = $_POST['cantidad'];
    
    
    $sql = "UPDATE herramientas SET Nombre='$nombre', Marca='$marca', Descripcion='$descripcion' , Cantidad='$cantidad' WHERE Id='$id'";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){  
       header("Location:../../paginas/controlHerramientas/controlHerramientas.php?");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }
?>