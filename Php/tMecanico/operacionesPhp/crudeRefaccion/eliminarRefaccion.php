<?php
    require ('../conexion/conexion.php');

    $id = $_GET["id"];
    $idA = $_GET["idA"];

    $sql = "DELETE FROM refacciones WHERE idRefaccion=".$id;

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location: ../../paginas/controlRefacciones/controlRefacciones.php?id=$id");    
    }else{
        echo "lo siento";
        }

    mysqli_free_result($resultado);
    mysqli_close($conexion);

?>