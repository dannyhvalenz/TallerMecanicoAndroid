<?php
    require ('../conexion/conexion.php');

    $matricula = $_POST['matricula'];
    $marca = $_POST['marca'];
    $modelo = $_POST['modelo'];
    $linea = $_POST['linea'];
    $color = $_POST['color'];
    $idCliente = $_POST['id'];
    $idAdmin = $_POST['idA'];
    

    $sql = "INSERT INTO automovil (Matricula, Marca, Modelo, Linea, Color, id_cliente, id_administrador) VALUES ('$matricula','$marca', '$modelo', '$linea', '$color', '$idCliente', '$idAdmin')";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){    
        header("Location: ../../paginas/controlAutos/controlAutos.php?id=$idCliente&idA=$idAdmin");
    }else{
        echo "lo siento";
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>