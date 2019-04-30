<?php
    require ('../conexion/conexion.php');

    session_start();

    $_SESSION['usuario'] = 'adiministrador';

    $correo = $_POST['correo'];
    $contraseña = $_POST['contraseña'];


    $consulta = "SELECT Identificador FROM administrador WHERE Correo='$correo'";

    $resultado=mysqli_query($conexion, $consulta);
    
    $filas=mysqli_num_rows($resultado);

  while ($didmed = mysqli_fetch_row($resultado)) { 
        $iddom = $didmed['0']; 
    } 

        if($filas>0){
        $_SESSION['usuario'] = $correo;
      
            
        header("Location:http://localhost/tallerMecanico/control.php?id=$iddom");   
    }else{
        header("Location:http://localhost/tallerMecanico/iniciarSesion.php");
    }

    mysqli_free_result($resultado);
    mysqli_close($conexion);

?>
