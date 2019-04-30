<?php
    require ('../conexion/conexion.php');

    $Tipo = $_GET["T"];

    $Matricula = $_GET["M"];

    $idA = $_GET["idA"]; 

    $id = $_GET['idR'];

    $idC = $_GET['id'];

    $sql = "DELETE FROM reparacion WHERE Tipo = '$Tipo' and Identificador='$id'";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location:http://localhost/tallerMecanico/controlReparacion.php?M=$Matricula&id=$idC&idA=$idA");    
    }else{
        echo "lo siento";
        echo "idR= ".$id;
        echo "Matricula= ".$M;
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>