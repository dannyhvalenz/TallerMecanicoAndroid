<?php
    require ('../conexion/conexion.php');

    $tipo = $_POST['tipo'];
    $costo = $_POST['costo'];
    $kilometraje = $_POST['kilometraje'];
    $falla = $_POST['falla'];
    $reparacion = $_POST['reparacion'];
    $matricula = $_POST['matricula'];
    $idAdmin = $_POST['idA'];
    $idCliente = $_POST['idC'];
    

    $sql = "INSERT INTO reparacion (Tipo, Costo, Kilometraje, Descripcion_falla, Descripcion_reparacion, Matricula_auto, id_administrador) VALUES ('$tipo','$costo', '$kilometraje', '$falla', '$reparacion', '$matricula', '$idAdmin')";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){    
        header("Location: ../../paginas/controlReparaciones/controlReparaciones.php?M=$matricula&id=$idCliente&idA=$idAdmin");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>