<?php
    require ('../conexion/conexion.php');

    $id = $_GET["id"];
    $idA = $_GET["idA"];

    $sql = "DELETE FROM refacciones WHERE idRefaccion=".$id;

    $resultado = mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location: ../../paginas/controlRefacciones/controlRefacciones.php?id=$idA");    
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);

?>