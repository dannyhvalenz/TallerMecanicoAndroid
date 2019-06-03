<?php
    require ('../conexion/conexion.php');

   
    $nombreRefaccion = $_POST['nombre'];
    $descripcionRefaccion = $_POST['descripcion'];
    $unidadRefaccion = $_POST['unidad'];
    $precioRefaccion = $_POST['precio'];
    

    $sql = "INSERT INTO refacciones (nombreRefaccion, descripcionRefaccion, unidadRefaccion, precioRefaccion) VALUES ('$nombreRefaccion', '$descripcionRefaccion', '$unidadRefaccion', '$precioRefaccion')"; 
    $resultado = mysqli_query($conexion, $sql);

    if($resultado == true){
        header ("Location: ../../paginas/controlRefacciones/controlRefacciones.php?id=$idAdmin");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>