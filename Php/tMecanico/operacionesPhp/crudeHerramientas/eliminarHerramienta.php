<?php
    require ('../conexion/conexion.php');

    $id = $_GET["id"];
   
    

    $sql = "DELETE FROM herramientas WHERE Id =".$id;

    $resultado=mysqli_query($conexion, $sql);

    if($resultado==true){   
        header("Location: ../../paginas/controlHerramientas/controlHerramientas.php?");    
    }else{
        echo "lo siento";
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);
?>