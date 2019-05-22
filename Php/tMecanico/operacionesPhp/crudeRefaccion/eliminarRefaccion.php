<?php
    require ('../conexion/conexion.php');

    $idR = $_GET["idR"];
    $id = $_GET["id"];
    $idAdmin = $_GET["idA"];
    $m = $_GET["M"];

    $sql = "DELETE FROM refacciones WHERE idRefaccion=".$idR;

    $resultado = mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location: ../../paginas/controlRefacciones/controlRefacciones.php?M=$m&id=$id&idA=$idAdmin");    
    }else{
        echo "lo siento";
        echo "id= ".$id;
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);

?>