<?php
   require ('php/conexion/conexion.php');

    $Matricula = $_GET["M"];
   
   $where="";
   
   $sql = "SELECT * FROM reparacion WHERE Matricula_auto = '$Matricula'";
   
   $resultado = mysqli_query($conexion, $sql);
   
   ?>
<!doctype html>
<html lang="es">
   <head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="css/bootstrap.css">
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
                           <a class="nav-link" href="controlAutos.php?id=<?php echo $_GET['id']?>&idA=<?php echo $_GET['idA']?>">Autos</a>
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
                  <div class="row">
                     <!-- Button trigger modal -->
                     <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                     Nuevo
                     </button>
                     <!-- Modal -->
                     <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                           <div class="modal-content">
                              <div class="modal-header">
                                 <h5 class="modal-title" id="exampleModalLabel">Ingresar reparacion</h5>
                                 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                 <span aria-hidden="true">&times;</span>
                                 </button>
                              </div>
                              <div class="modal-body">
                                 <form method="post" action="php/crudeReparacion/crearReparacion.php">
                                    <div class="form-row">
                                       <div class="form-group">
                                          <label for="exampleFormControlSelect1">Tipo</label>
                                          <select class="form-control" id="exampleFormControlSelect1" name="tipo">
                                             <option value="Mecanico">Mecanico</option>
                                             <option value="Electrico">Electrico</option>
                                             <option value="Lubricacion">Lubricacion</option>
                                             <option value="Hojalateria">Hojalateria</option>
                                             <option value="Llantas">Llantas</option>
                                             <option value="Vestiduras">Vestiduras</option>
                                             <option value="Mofles">Mofles</option>
                                          </select>
                                       </div>
                                       <div class="form-group col-md-6">
                                          <label for="inputPassword4">Costo</label>
                                          <input class="form-control" placeholder="Costo" name="costo">
                                       </div>
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress">Kilometraje</label>
                                       <input type="text" class="form-control" placeholder="Kilometraje" name="kilometraje">
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress">Falla</label>
                                       <input type="text" class="form-control" placeholder="Falla" name="falla">
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress2">Reparacion</label>
                                       <input type="text" class="form-control" placeholder="Reparacion" name="reparacion">
                                    </div>
                                    <div>
                                       <input type='hidden' name='matricula' value='<?php echo $_GET['M']?>'>
                                    </div>
                                    <div>
                                       <input type='hidden' name='idA' value='<?php echo $_GET['idA']?>'>
                                    </div>
                                    <div>
                                       <input type='hidden' name='idC' value='<?php echo $_GET['id']?>'>
                                    </div>
                                    <div>
                                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                       <button type="submit" class="btn btn-primary">Guardar</button>
                                    </div>
                                 </form>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="row table-responsive">
                     <table class="table table-striped">
                        <thead>
                           <tr>
                              <th>Tipo</th>
                              <th>Costo</th>
                              <th>Kilometraje</th>
                              <th>Falla</th>
                              <th>Reparacion</th>
                              <th></th>
                              <th></th>
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
                                 <?php echo $row['Costo']?>
                              </td>
                              <td>
                                 <?php echo $row['Kilometraje']?>
                              </td>
                              <td>
                                 <?php echo $row['Descripcion_falla']?>
                              </td>
                              <td>
                                 <?php echo $row['Descripcion_reparacion']?>
                              </td>
                              <td>
                                 <a href="actualizarReparacion.php?T=<?php echo $row['Tipo']?>&M=<?php echo $row['Matricula_auto']?>&id=<?php echo $_GET['id']?>&idA=<?php echo $row['id_administrador']?>">
                                 <button type="button" class="btn btn-dark">M</button>
                                 </a>
                              </td>
                              <td>
                                 <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#eliminar">E</button>
                              </td>
                              <div class="modal" tabindex="-1" role="dialog" id="eliminar">
                                 <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                       <div class="modal-header">
                                          <h5 class="modal-title">Eliminar</h5>
                                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                          <span aria-hidden="true">&times;</span>
                                          </button>
                                       </div>
                                       <div class="modal-body">
                                          <p>¿Seguro que desea eliminar este usuario?</p>
                                       </div>
                                       <div class="modal-footer">
                                          <button type="button" class="btn btn-dark" >
                                          <a onClick="return eliminar(<?php echo $row['Tipo'];?>);" style="text-decoration: none" href="php/crudeReparacion/eliminarReparacion.php?T=<?php echo $row['Tipo']?>&M=<?php echo $row['Matricula_auto']?>&id=<?php echo $_GET['id']?>&idA=<?php echo $row['id_administrador']?>&idR=<?php echo $row['Identificador']?>">Eliminar</a>
                                          </button>
                                          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                       </div>
                                    </div>
                                 </div>
                              </div>
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