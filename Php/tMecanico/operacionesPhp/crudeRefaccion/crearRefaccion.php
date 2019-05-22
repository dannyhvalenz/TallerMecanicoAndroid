<?php
    require ('../conexion/conexion.php');

    /* $idRefaccion = $_POST['idRefaccion']; */
    $nombreRefaccion = $_POST['nombreRefaccion'];
    $descripcionRefaccion = $_POST['descripcionRefaccion'];
    $unidadRefaccion = $_POST['unidadRefaccion'];
    $precioRefaccion = $_POST['precioRefaccion'];
    $matricula = $_POST['matricula'];

    $sql = "INSERT INTO refacciones (nombreRefaccion, descripcionRefaccion, unidadRefaccion, precioRefaccion, Matricula) VALUES ('$nombreRefaccion', '$descripcionRefaccion', '$unidadRefaccion', '$precioRefaccion', '$matricula')"; 
    $resultado = mysqli_query($conexion, $sql);

    if($resultado == true){
        header ("Location: ../../paginas/controlRefacciones/controlRefacciones.php?M=$matricula&id=$idCliente&idA=$idAdmin");
    }else{
        /* echo "idRefaccion= ".$idRefaccion; */
        echo "nombreRefaccion= ".$nombreRefaccion;
        echo "descripcionRefaccion= ".$descripcionRefaccion;
        echo "unidadRefaccion= ".$unidadRefaccion;
        echo "precioRefaccion= ".$precioRefaccion;
        echo "Matricula= ".$matricula;
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>