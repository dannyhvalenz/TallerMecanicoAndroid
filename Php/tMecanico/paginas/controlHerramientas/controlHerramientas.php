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
         <script type="text/javascript">
         function confirmar(){
        var respuesta = confirm("¿Seguro que desea eliminar este elemento?");
        if(respuesta == true){
            return true;
        }else{
            return false;
        }
         }
     </script>
     
     <script type="text/javascript">
         function validaCampos(){
             var nombre = $("#nombre").val();
             var marca = $("#marca").val();
             var descripcion = $("#descripcion").val();
             var cantidad = $("#cantidad").val();
             
             if ($.trim(nombre)==""){
                 var respuesta = confirm("No se ingreso el nombre");
                 return false;
             }
              
             if ($.trim(marca)==""){
                 var respuesta = confirm("No se ingreso la marca");
                 return false;
             }
             if ($.trim(descripcion)==""){
                 var respuesta = confirm("No se ingreso la descripción");
                 return false;
             }
             if ($.trim(cantidad)==""){
                 var respuesta = confirm("No se ingreso la cantidad");
                 return false;
             }
         }
      </script>
     
     
    <title>Login</title>
  </head>
  <bodys>
              
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
                <a class="nav-link" href="../controlHerramientas/controlHerramientas.php?">Herramientas</a>
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
                <div class="col-lg-2" style="background-color: #FFFFFF" align="center">
                    <div>
                    <div class "row" aling = "left">
                     <!-- Button trigger modal -->
                     <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Nuevo
                     </button>
                     </div>
                     </div>
                     <div class="row" aling="left">
                     <div>
                         <form method="post" action="../buscar/buscarHerramienta.php?">
                            <div class="form-group">
                            <h5>Introduzca la herramienta que desea buscar</h5> 
                               <input type="text" id="BHerramienta" placeholder="Buscar herramienta" name="buscarH">
                               <div class="mt-2">
                               <button type="submit" class="btn btn-primary">Buscar</button>
                               </div>
                               </div>                      
                            </form>
                     </div>         
                     </div>
                     <!-- Modal -->
                     <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" onsubmit="return validaCampos()">
                        <div class="modal-dialog" role="document">
                           <div class="modal-content">
                              <div class="modal-header">
                                 <h5 class="modal-title" id="exampleModalLabel">Crear Herramienta</h5>
                                 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                 <span aria-hidden="true">&times;</span>
                                 </button>
                              </div>
                              <div class="modal-body">
                                 <form method="post" action="../../operacionesPhp/crudeHerramientas/crearHerramienta.php">
                                    <div class="form-row">
                                       <div class="form-group col-md-6">
                                          <label for="inputEmail4">Nombre</label>
                                          <input class="form-control" placeholder="Nombre" name="nombre" id="nombre">
                                       </div>
                                       <div class="form-group col-md-6">
                                          <label for="inputPassword4">Marca</label>
                                          <input class="form-control" placeholder="Marca" name="marca" id="marca">
                                       </div>
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress">Descripcion</label>
                                       <input type="text" class="form-control" placeholder="Descripcion" name="descripcion" id="descripcion">
                                    </div>
                                    <div class="form-group">
                                       <label for="inputAddress">Cantidad</label>
                                       <input type="text" class="form-control" placeholder="Cantidad" name="cantidad" id="cantidad">
                                    </div>
                                      <!-- <div>
                                       <input type='hidden' name='id' value='<?php echo $_GET['id']?>'>
                                    </div> -->
                
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
                                 <a href="actualizarHerramienta.php?id=<?php echo $row['Id']?>">
                                 <button type="button" class="btn btn-dark">M</button>
                                 </a>
                              </td>
                              <td>
                                 <a href="../../operacionesPhp/crudeHerramientas/eliminarHerramienta.php?idH=<?php echo $row['Id']?>"><button button onclick="return confirmar()" type="button" class="btn btn-dark">E</button></a>
                              </td>
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