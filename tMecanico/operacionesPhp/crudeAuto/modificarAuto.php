<?php
    require ('../conexion/conexion.php');

    $matricula = $_POST['matricula'];
    $marca = $_POST['marca'];
    $modelo = $_POST['modelo'];
    $linea = $_POST['linea'];
    $color = $_POST['color'];
    $idCliente = $_POST['idCliente'];
    $idAdmin = $_POST['idAdmin'];

    
    $sql = "UPDATE automovil SET Marca='$marca', Modelo='$modelo', Linea='$linea' , Color='$color' WHERE Matricula = '$matricula'";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){  
       header("Location: ../../paginas/controlAutos/controlAutos.php?id=$idCliente&idA=$idAdmin");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }
?>