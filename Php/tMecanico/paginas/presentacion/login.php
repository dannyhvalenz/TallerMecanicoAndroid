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
                  <li>
                    <a class="nav-link" href="index.html#Servicios">Servicios</a>
                  </li>
                  <li>
                    <a class="nav-link" href="index.html#Contactanos">Contactanos</a>
                  </li>
                  <li class="nav-item active">
                   <a class="nav-link" href="#">Iniciar Sesion</a>
                  </li>
                </ul>                
              </div>
            </nav>
                
            <section>
            <div class="container pt-5 pb-5" style="background-color: #FFFFFF">
               <div  class="row">
                <div class="col-lg-6">
                    <img src="../../imagenes/reparaciones/imgMecanico.jpg" width="100%" height="500px">
                </div>
    
                <div class="col-lg-6 pt-5">
                   <div align="center">
                       <img src="../../imagenes/login/usuario2.png" width="150" height="150" class="rounded" style="background-color: #343A40">
                   </div>
                   
                    <form action="../../operacionesPhp/sesion/validar.php" method="post">
                      <div class="form-group">
                        <label for="exampleInputEmail1"><h5>Correo electronico</h5></label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="ejemplo@gmail.com" name="correo">
                        <small id="emailHelp" class="form-text text-muted">Necesitas ser administrador</small>
                      </div>
                      <hr class="my-3">
                      <div class="form-group">
                        <label for="exampleInputPassword1"><h5>Constraseña</h5></label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Contraseña" name="contraseña">
                      </div>
                      <hr class="my-3">
                      <div class="form-group form-check" align="center">
                       <button type="submit" class="btn btn-primary">Iniciar</button>
                      </div>
                    </form>
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