<?php
    require ('../conexion/conexion.php');

    $idHer = $_GET["idH"];
   
    

    $sql = "DELETE FROM herramientas WHERE Id=".$idHer;

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){  
        
        header("Location: ../../paginas/controlHerramientas/controlHerramientas.php");   
        
    }else{
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>