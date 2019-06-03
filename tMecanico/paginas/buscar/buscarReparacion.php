<?php
   require ('../../operacionesPhp/conexion/conexion.php');
    
  $R = $_POST['tipo'];
   
   $where="";

    $sql = "SELECT * FROM reparacion INNER JOIN automovil ON reparacion.matricula_auto = automovil.Matricula and reparacion.Tipo ='$R'";
   
   $resultado = mysqli_query($conexion, $sql);
   
   ?>
<!doctype html>
<html lang="es">
   <head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
      <!--stilos personalizados de css-->
      <title>Control de reparaciones</title>
   </head>
   <body>
      <!-- Div contendor de toda la pagina Web -->
      <div id="contenedor">
         <!-- Div contendor del header -->
         <div id="header">
            <div id="presentacion">
               <img src="imagenes/cabeza/nav4.png" width="100%">
            </div>
            <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark sticky-top">
               <a class="navbar-brand" href="">
               <img src="imagenes/logo/logoTaller.png" width="60" height="30" class="d-inline-block align-top" alt=" ">
               Taller mecanico
               </a>
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class=" ml-auto collapse navbar-collapse" id="navbarNavDropdown">
                  <div class="ml-auto">
                     <ul class="navbar-nav">
                        <li class="nav-item">
                           <a class="nav-link" href="../controlReparaciones/controlReparaciones.php?M=<?php echo $_GET['M']?>&id=<?php echo $_GET['id']?>&idA=<?php echo $_GET['idA']?>">Reparaciones</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="php/sesion/cerrarSesion.php">Cerrar Sesion</a>
                        </li>
                     </ul>
                  </div>
               </div>
            </nav>
            <?php
               session_start();
                       
               
               if(isset($_SESSION['usuario'])){         
               }else{
                   header("Location:iniciarSesion.php");
               }               
            ?>
            <div id="contenido">
               <div class="container">
                  <div class="row">
                     <h2>Control de usuarios</h2>
                  </div>
                  <div class="row table-responsive">
                     <table class="table table-striped">
                        <thead>
                           <tr>
                              <th>Tipo</th>
                              <th>Matricula</th>
                              <th>Marca</th>
                              <th>Costo</th>
                              <th>Falla</th>
                              <th>Reparacion</th>
                           </tr>
                        </thead>
                        <tbody>
                           <?php while($row = mysqli_fetch_array($resultado)){
                              ?>
                           <tr>
                              <td>
                                 <?php echo $row['Tipo']?>
                              </td>
                              <td>
                                 <?php echo $row['Matricula']?>
                              </td>
                              <td>
                                 <?php echo $row['Marca']?>
                              </td>
                              <td>
                                 <?php echo $row['Costo']?>
                              </td>
                              <td>
                                 <?php echo $row['Descripcion_falla']?>
                              </td>
                              <td>
                                 <?php echo $row['Descripcion_reparacion']?>
                              </td>
                           </tr>
                           <?php } ?>
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Optional JavaScript -->
      <!-- jQuery first, then Popper.js, then Bootstrap JS -->
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
      <script src="js/bootstrap.js" ></script>
   </body>
</html>