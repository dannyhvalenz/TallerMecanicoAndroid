<?php
   require ('../../operacionesPhp/conexion/conexion.php');
    $Matricula = $_GET["M"];
   
   $where="";
   
   $sql = "SELECT * FROM reparacion WHERE Matricula_auto = '$Matricula'";
   
   $resultado = mysqli_query($conexion, $sql);
   
   ?>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Login</title>
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
                 <li class="nav-item">
                           <a class="nav-link" href="../controlAutos/controlAutos.php?id=<?php echo $_GET['id']?>&idA=<?php echo $_GET['idA']?>">Autos</a>
                        </li>
                  <li class="nav-item active">
                   <a class="nav-link" href="../../operacionesPhp/sesion/cerrarSesion.php">Cerrar Sesion</a>
                  </li>
                </ul>                
              </div>
            </nav>
              
               <section>
            <div class="container">
               <div class="row pt-5 pb-5">
                <div class="col-lg-2" style="background-color: #FFFFFF">
                    <div class="row" align="left">
                     <!-- Button trigger modal -->
                     <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                     Nuevo
                     </button>
                     </div>
                     
                     <div class="row" aling="left">
                     <div>
                         <form method="post" action="../buscar/buscarReparacion.php?M=<?php echo $_GET['M']?>&id=<?php echo $_GET['id']?>&idA=<?php echo $_GET['idA']?>">
                              <div class="form-group">
                              <h5>Introdusca el tipo de reparacion que desee buscar</h5>
                               <input type="text" id="BReparacion" placeholder="Buscar reparacion" name="tipo">
                               <div class="mt-2">
                               <button type="submit" class="btn btn-primary">Burcar</button>
                               </div>
                               </div>                      
                            </form>
                     </div>
                     </div>
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
                                 <form method="post" action="../../operacionesPhp/crudeReparacion/crearReparacion.php">
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
                <div class="col-lg-10" style="background-color: #FFFFFF">
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
                           </tr>
                           <?php } ?>
                        </tbody>
                     </table>
                  </div>
                </div>
               </div>
            </div>
             </section>   

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>