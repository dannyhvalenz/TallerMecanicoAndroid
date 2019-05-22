<?php
    require ('../conexion/conexion.php');

    $idRefaccion = $_POST['idRefaccion'];
    $nombreRefaccion = $_POST['nombreRefaccion'];
    $descripcionRefaccion = $_POST['descripcionRefaccion'];
    $unidadRefaccion = $_POST['unidadRefaccion'];
    $precioRefaccion = $_POST['precioRefaccion'];
    
    //$matricula = $_POST['Matricula'];
    $idA = $_POST['idAdmin'];
    $idCliente = $_POST['idCliente'];

    $sql = "UPDATE refacciones SET nombreRefaccion = '$nombreRefaccion', descripcionRefaccion='$nombreRefaccion', unidadRefaccion='$unidadRefaccion', precioRefaccion='$precioRefaccion' WHERE idRefaccion= '$idRefaccion'";
    $resultado=mysqli_query($conexion, $sql);

    if($resultado == true){
        echo "idc= ".$idCliente;
        echo "idA= ".$idA;
        header("Location:../../paginas/controlRefacciones/controlRefacciones.php?id=$idCliente&idA=$idA&M=$matricula");
    }else{
        echo "ella no te ama, campeon :( ";
    }
?>