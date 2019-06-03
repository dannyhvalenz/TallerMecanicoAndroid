<?php
    require ('../conexion/conexion.php');

    $M = $_GET["M"];

    $idC = $_GET["idC"];
    $idCliente = (int)$idC;

    $idA = $_GET["idA"];
    $idAdmin = (int)$idA;   


    $sql = "DELETE FROM automovil WHERE Matricula = '$M'";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location: ../../paginas/controlAutos/controlAutos.php?id=$idCliente&idA=$idAdmin");    
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>