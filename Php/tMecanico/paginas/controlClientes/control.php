<?php
   require ('../../operacionesPhp/conexion/conexion.php');
    
   
   $where="";
   
   $sql = "SELECT * FROM cliente";
   
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
     
     <script type="text/javascript">
         function confirmar(){
        var respuesta = confirm("¿Seguro que desea elimianr este elemento?");
        if(respuesta == true){
            return true;
        }else{
            return false;
        }
         }
     </script>
     
      
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
                  <li class="nav-item active">
                   <a class="nav-link" href="../controlHerramientas/controlHerramientas.php">Herramientas</a>
                  </li>
                </ul>                
              </div>
               <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                  <li class="nav-item active">
                   <a class="nav-link" href="../../operacionesPhp/sesion/cerrarSesion.php">Cerrar Sesion</a>
                  </li>
                </ul>                
              </div>
            </nav>
              
               <section>
            <div class="container">
               <div class="row pt-5 pb-5">
                <div class="col-lg-2" style="background-color: #FFFFFF" align="center">
                     <div>
                     <div class="row" align="left">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" >Nuevo</button>  
                     </div>
                     </div>
                     <div class="row" align="left">
                     <div>
                         <form method="post" action="../buscar/buscar.php?id=<?php echo $_GET['id']?>">
                              <div class="form-group">
                              <h5>Introdusca el correo que desee buscar</h5>
                               <input type="text" id="BCorreo" placeholder="Buscar correo" name="correo">
                               <div class="mt-2">
                               <button type="submit" class="btn btn-primary">Buscar</button>
                               </div>
                               </div>                      
                            </form>
                     </div>
                     </div>
                     <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                           <div class="modal-content">
                              <div class="modal-header">
                                 <h5 class="modal-title" id="exampleModalLabel">Crear cliente</h5>
                                 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                 <span aria-hidden="true">&times;</span>
                                 </button>
                              </div>
                              <div class="modal-body">
                                 <form method="post" action="../../operacionesPhp/crudeCliente/crearUsuario.php">
                                    <div class="form-row">
                                       <div class="form-group col-md-6">
                                          <label for="inputEmail4">Nombre</label>
                                          <input class="form-control" placeholder="Cliente" name="nombre">
                                       </div>
                                       <div class="form-group col-md-6">
                                          <label for="inputPassword4">Telefono</label>
                                          <input class="form-control" placeholder="Telefono" name="telefono">
                                       </div>
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress">Direccion</label>
                                       <input type="text" class="form-control" placeholder="Calle-Numero-Ciudad" name="direccion">
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress2">Correo</label>
                                       <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Correo electronico" name="correo">
                                    </div>
                                    <div>
                                       <input type='hidden' name='id' value='<?php echo $_GET['id']?>'>
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
                              <th>ID</th>
                              <th>Nombre</th>
                              <th>Telefono</th>
                              <th>Direccion</th>
                              <th>Correo</th>
                              <th></th>
                              <th></th>
                              <th></th>
                              <th></th>


                           </tr>
                        </thead>
                        <tbody>
                           <?php while($row = mysqli_fetch_array($resultado)){
                              ?>
                           <tr>
                              <td>
                                 <?php echo $row['Identificador']?>
                              </td>
                              <td>
                                 <?php echo $row['Nombre']?>
                              </td>
                              <td>
                                 <?php echo $row['Telefono']?>
                              </td>
                              <td>
                                 <?php echo $row['Direccion']?>
                              </td>
                              <td>
                                 <?php echo $row['Correo']?>
                              </td>
                              <td>
                                 <a href="actualizarCliente.php?id=<?php echo $row['Identificador']?>">
                                 <button type="button" class="btn btn-dark">M</button>
                                 </a>
                              </td>
                              
                              
                              <td>
                                  <a href="../../operacionesPhp/crudeCliente/eliminarUsuario.php?id=<?php echo $row['Identificador']?>&idA=<?php echo $row['id_aministrador']?>"><button onclick="return confirmar()" type="button" class="btn btn-dark">E</button></a>        
                              </td>
                              

                              <td>
                                 <a href="../controlAutos/controlAutos.php?id=<?php echo $row['Identificador']?>&idA=<?php echo $_GET['id']?>">
                                 <button type="button" class="btn btn-success">Autos</button>
                                 </a>
                              </td>
                              <td>
                                 <a href="../controlCitas/controlCitas.php?id=<?php echo $row['Identificador']?>&idA=<?php echo $_GET['id']?>">
                                 <button type="button" class="btn btn-warning">Citas</button>
                                 </a>
                              </td>
                              <td>
                                 <a href="../controlRefacciones/controlRefacciones.php?id=<?php echo $row['Identificador']?>&idA=<?php echo $_GET['id']?>">
                                    <button type="button" class="btn btn-warning">Refacciones</button>
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