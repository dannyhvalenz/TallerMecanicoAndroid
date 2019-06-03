<?php
   require ('../../operacionesPhp/conexion/conexion.php');
   $matricula = $_GET['M'];
   
   $where="";
   
   $sql = "SELECT * FROM automovil WHERE Matricula='$matricula'";
   
   $resultado = mysqli_query($conexion, $sql);
   $row = mysqli_fetch_array($resultado)  
?>
   <!doctype html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Actualizar automovil</title>
  </head>
  <body>
                  
                   
              
               <?php
                   session_start();


                   if(isset($_SESSION['usuario'])){                   
                   }else{
                       header("Location:iniciarSesion.php");
                   }
               ?>
   <header>
         <div>
             <img src="../../imagenes/cabecera/nav3.png" width="100%">
         </div>
      </header>
           
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
              <img src="../../imagenes/nav/logoTaller.png" height="50" width="120">
              <a class="navbar-brand" href="index.html">Taller Mecanico</a>
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                  <li class="nav-item active">
                   <a class="nav-link" href="../../operacionesPhp/sesion/cerrarSesion.php">Cerrar Sesion</a>
                  </li>
                </ul>                
              </div>
            </nav>  
            
            <div class="container pt-5 pb-5" style="background-color: #FFFFFF">
            
            <div class="row" > 
               <div class="col-lg-2" align="right">
                <img src="../../imagenes/actualizar/auto.png" width="120px" height="120px">
                </div>
                <div class="col-lg-10 pr-5" align="left">
                    <h3>Panel de actualizacion</h3>
                    <h5>En esta pagina usted puede modificar los datos de los automoviles</h5>
                    <h5>Modifique los datos y para finalizar precione "Actualizar datos"</h5>
                </div>
            </div>
            <hr class="my-5">
            <form method="post" action="../../operacionesPhp/crudeAuto/modificarAuto.php">
         <div class="form-row">
            <div class="form-group col-md-12">
               <label for="inputEmail4">Marca</label>
               <input type="text" class="form-control" id="nombre" placeholder="Nomrbre" name="marca" value="<?php echo $row['Marca']?>" required>
            </div>
            <div class="form-group col-md-12">
               <label for="inputPassword4">Modelo</label>
               <input type="text" class="form-control" id="telefono" name="modelo" value="<?php echo $row['Modelo']?>" required>
            </div>
         </div>
         <div class="form-group">
            <label for="inputAddress">Linea</label>
            <input type="text" class="form-control" id="direccion" placeholder="linea" name="linea" value="<?php echo $row['Linea']?>" required>
         </div>
         <div class="form-group">
            <label for="inputAddress2">Color</label>
            <input t type="text" class="form-control" id="inputAddress2" placeholder="color" name="color" value="<?php echo $row['Color']?>" required>
         </div>
         <div>
            <input type="hidden" id="id" name="matricula" value="<?php echo $row['Matricula']?>" required>
         </div>
         <div>
            <input type="hidden" id="idCliente" name="idCliente" value="<?php echo $row['id_cliente']?>" required>
         </div>
         <div>
            <input type="hidden" id="idAdmin" name="idAdmin" value="<?php echo $row['id_administrador']?>" required>
         </div>
         <hr class="my-5">
         <div align="center">
         <button type="submit" class="btn btn-primary">Actualizar datos</button>
         <button type="submit" class="btn btn-secondary"><a href="controlAutos.php"></a>Cancelar</button>
         </div>
      </form>
</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>