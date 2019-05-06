<?php
   require ('../../operacionesPhp/conexion/conexion.php');
    
   
   $where="";
   
   $sql = "SELECT * FROM herramientas";
   
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
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Nuevo</button>  
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
                                 <form method="post" action="../../operacionesPhp/crudeHerramientas/crearHerramienta.php">
                                    <div class="form-row">
                                       <div class="form-group col-md-6">
                                          <label for="inputEmail4">Nombre</label>
                                          <input class="form-control" placeholder="Herramienta" name="nombre">
                                       </div>
                                       <div class="form-group col-md-6">
                                          <label for="inputPassword4">Marca</label>
                                          <input class="form-control" placeholder="Marca" name="marca">
                                       </div>
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress">Descripcion</label>
                                       <input type="text" class="form-control" placeholder="Descipcion" name="descripcion">
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress2">Cantidad</label>
                                       <input type="cantidad" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Cantidad" name="cantidad">
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
                              <th>Marca</th>
                              <th>Descripcion</th>
                              <th>Cantidad</th>
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
                                 <?php echo $r=$row['Id']?>
                              </td>
                              <td>
                                 <?php echo $row['Nombre']?>
                              </td>
                              <td>
                                 <?php echo $row['Marca']?>
                              </td>
                              <td>
                                 <?php echo $row['Descripcion']?>
                              </td>
                              <td>
                                 <?php echo $row['Cantidad']?>
                              </td>
                              <td>
                                 <a href="actualizarHerramienta.php?id=<?php echo $r?>">
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
                                          <p>¿Seguro que desea eliminar la herramienta?</p>
                                       </div>
                                       <div class="modal-footer">
                                          <button type="button" class="btn btn-dark" >
                                          <a onClick="return eliminar(
                                             &id=<?php echo $row['Id']?>);" style="text-decoration: none" href="../../operacionesPhp/crudeHerramientas/eliminarHerramienta.php?
                                             id=<?php echo $row['Id'];?>">Eliminar
                                          </a>
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
             </section>   

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>