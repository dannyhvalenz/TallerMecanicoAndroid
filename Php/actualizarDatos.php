<?php
   require ('php/conexion/conexion.php');
   $identificador = $_GET['id'];
   
   
   $where="";
   
   $sql = "SELECT * FROM cliente WHERE Identificador = '$identificador'";
   
   $resultado = mysqli_query($conexion, $sql);
   $row = mysqli_fetch_array($resultado)                      
   ?>
<!DOCTYPE html>
<html lang="es">
   <head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="css/bootstrap.css">
      <!--stilos personalizados de css-->
      <title>Actualizar datos de usuarios</title>
   </head>
   <body>
      <form method="post" action="php/crude/modificar.php">
         <div class="form-row">
            <div class="form-group col-md-6">
               <label for="inputEmail4">Nombre</label>
               <input type="text" class="form-control" id="nombre" placeholder="Nomrbre" name="nombre" value="<?php echo $row['Nombre']?>" required>
            </div>
            <div class="form-group col-md-6">
               <label for="inputPassword4">Telefono</label>
               <input type="text" class="form-control" id="telefono" name="telefono" value="<?php echo $row['Telefono']?>" required>
            </div>
         </div>
         <div class="form-group">
            <label for="inputAddress">Direccion</label>
            <input type="text" class="form-control" id="direccion" placeholder="direccion" name="direccion" value="<?php echo $row['Direccion']?>" required>
         </div>
         <div class="form-group">
            <label for="inputAddress2">Correo</label>
            <input type="email" class="form-control" id="inputAddress2" placeholder="Correo" name="correo" value="<?php echo $row['Correo']?>" required>
         </div>
         <div>
            <input type="hidden" id="id" name="id" value="<?php echo $row['Identificador']?>" required>
         </div>
         <div>
            <input type="hidden" id="idAdmin" name="idAdmin" value="<?php echo $row['id_aministrador']?>" required>
         </div>
         <button type="submit" class="btn btn-primary">Actualizar datos</button>
         <button type="submit" class="btn btn-secondary"><a href="control.php"></a>Cancelar</button>
      </form>
      <?php
         session_start();
                 
         
         if(isset($_SESSION['usuario'])){
             echo "Sesion creada con exito";
         }else{
             header("Location:iniciarSesion.php");
         }
         
         ?>
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
      <script src="js/bootstrap.js" ></script>
   </body>
</html>