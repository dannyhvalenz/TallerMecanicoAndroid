<!-- Obtenemos la conexion-->
<?php 
    require ('../../operacionesPhp/conexion/conexion.php');
    
    $where = "";
    $sql = "SELECT * FROM refacciones";
    
    $resultado = mysqli_query($conexion, $sql);
?>

<!--Comenzamos con lo que es la pagina web -->
<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Agegamos el bootstrap-->
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

         <script type = "text/javascript">
            function validaCampos(){
                var nombre = $("nombre").val();
                var descripcion = $("descripcion").val();
                var unidad = $("unidad").val();
                var precio = $("precio").val();

                if($.trim(nombre) == ""){
                    var respuesta = confirm("No se ingreso el nombre del usuario");
                    return false;
                }if($.trim(descripcion) == ""){
                    var respuesta = confirm("No se ingreso el nombre del usuario");
                    return false;
                }if($.trim(unidad) == ""){
                    var respuesta = confirm("No se ingreso el nombre del usuario");
                    return false;
                }if($.trim(precio) == ""){
                    var respuesta = confirm("No se ingreso el nombre del usuario");
                    return false;
                }
            }
         </script>

     <title>Refacciones</title>
</head>

<body>

    <!-- Un poco de php para no variar -->
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
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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

    <!-- Aqui va la seccion alv -->
    <section>
        <div class="container">
            <div class="row pt-5 pb-5">
                <div class="col-lg-2" style="background-color: #FFFFFF" aling="center">
                    <div>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Nuevo</button>
                    </div>

                    <div class="row" aling="left">
                     <div>
                         <form method="post" action="../buscar/buscarRefaccion.php?id=<?php echo $_GET['id']?>">
                              <div class="form-group">
                              <h5>Buscando la refaccion que deseas</h5>
                               <input type="text" id="BRefaccion" placeholder="Buscar refaccion" name="refac">
                               <div class="mt-2">
                               <button type="submit" class="btn btn-primary">Buscar</button>
                               </div>
                               </div>                      
                            </form>
                     </div>
                     </div>

                    
                    <!-- Aqui esta el form para editar las cosas -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" onsubmit="validaCampos()">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Crear Refaccion</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <div class="modal-body">
                                    <!--De donde se estara el crude -->
                                    <form method="post" action="../../operacionesPhp/crudeRefaccion/crearRefaccion.php">
                                        <div class="form-row">

                                            <div class="form-group ">
                                                <label for="inputEmail">Nombre</label>
                                                <input class="form-control" placeholder="Nombre" id="nombre" name="nombre">
                                            </div>

                                            <div class="form-group ">
                                                <label for="inputPassword">Descripcion</label>
                                                <input class="form-control" placeholder="Descripcion" id="descripcion" name="descripcion">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputAddress">Unidad</label>
                                            <input type="text" class="form-control" placeholder="Unidad" id="unidad" name="unidad">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputAddress">Precio</label>
                                            <input type="text" class="form-control" placeholder="Precio" id="precio" name="precio">
                                        </div>
                                        <div>
                                            <input type='hidden' name='id' value='<?php echo $_GET['id']?>'>
                                        </div>

<!--                                         <div>
                                            <input type='hidden' name='idA' value='<?php echo $_GET['idA']?>'>
                                        </div>  -->

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
                                    <th>Descripcion</th>
                                    <th>Unidad</th>
                                    <th>Precio</th>
                                    <!-- <th>Matricula</th> --> 
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                                <?php while($row = mysqli_fetch_array($resultado)){ ?>
                                <tr>
                                    <td>
                                        <?php echo $r = $row ['idRefaccion']?>
                                    </td> 
                                    <td>
                                        <?php echo $row['nombreRefaccion']?>
                                    </td>
                                    <td>
                                        <?php echo $row['descripcionRefaccion']?>
                                    </td>
                                    <td>
                                        <?php echo $row['unidadRefaccion']?>
                                    </td>
                                    <td>
                                        <?php echo $row['precioRefaccion']?>
                                    </td>

                                    <td>
                                        <a href="actualizarRefacciones.php?id=<?php echo $r?>">
                                            <button type="button" class="btn btn-dark">M</button>
                                        </a>
                                    </td>

                                    <td>
                                        <a href="../../operacionesPhp/crudeRefaccion/eliminarRefaccion.php?id=<?php echo $row['idRefaccion']?>">
                                            <button type="button" class="btn btn-dark" onclick="return confirmar()">E</button>
                                        </a>
                                    </td>

                                    <td>
                                        <a href="../controlClientes/control.php?id=<?php echo $row['idRefaccion']?>&idA=<?php echo $_GET['id']?>">
                                            <button type="button" class="btn btn-success">Clientes</button>
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