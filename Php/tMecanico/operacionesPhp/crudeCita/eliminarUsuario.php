<?php
    require ('../conexion/conexion.php');

    $id = $_GET["id"];
    $idA = $_GET["idA"];

    $sql = "DELETE FROM cliente WHERE Identificador =".$id;

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location: ../../paginas/controlClientes/control.php?id=$idA");    
    }else{
        echo "lo siento";
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>