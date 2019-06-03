<?php
    require ('../conexion/conexion.php');

    $tipo = $_POST['tipo'];
    $costo = $_POST['costo'];
    $kilometraje = $_POST['kilometraje'];
    $falla = $_POST['falla'];
    $reparacion = $_POST['reparacion'];
    $matricula = $_POST['matricula'];
    $idAdmin = $_POST['idAdmin'];
    $idR = $_POST['idReparacion'];
    $idClliente = $_POST['idCliente'];
    
    
    $sql = "UPDATE reparacion SET Costo='$costo', Kilometraje='$kilometraje', Descripcion_falla='$falla' , Descripcion_reparacion='$reparacion' WHERE Identificador='$idR'";

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){  
       header("Location:../../paginas/controlReparaciones/controlReparaciones.php?M=$matricula&id=$idClliente&idA=$idAdmin");
    }else{
        header("location: ../../paginas/presentacion/nodisponible.html");
    }
?>