<?php
    require ('../conexion/conexion.php');

    $id = $_POST["id"];
    $nombreRefaccion = $_POST['nombre'];
    $descripcionRefaccion = $_POST['descripcion'];
    $unidadRefaccion = $_POST['unidad'];
    $precioRefaccion = $_POST['precio'];
    $idAdmin = $_POST['idAdmin'];

    $sql = "UPDATE refacciones SET nombreRefaccion='$nombreRefaccion', descripcionRefaccion='$descripcionRefaccion', unidadRefaccion='$unidadRefaccion', precioRefaccion='$precioRefaccion' WHERE idRefaccion='$id'";
    
    $resultado=mysqli_query($conexion, $sql);

    if($resultado == true){
        header("Location:../../paginas/controlRefacciones/controlRefacciones.php?id=$idA");
    }else{
        echo "Algo paso y no se quien fue :x";
    }
?>