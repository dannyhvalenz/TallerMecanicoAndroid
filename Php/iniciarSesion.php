<!doctype html>
<html lang="es">
   <head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="css/bootstrap.css">
      <!--stilos personalizados de css-->
      <link rel="stylesheet" href="css/login.css">
      <title>Iniciar Sesion</title>

   </head>
   <body>
      <!-- Div contendor de toda la pagina Web -->
      <div id="contenedor">
         <div>
            <!-- Logo de la empresa -->
            <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
               <a class="navbar-brand" href="index.html">
               <img src="imagenes/logo/logoTaller.png" width="60" height="30" class="d-inline-block align-top" alt="">
               Taller mecanico
               </a>
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class=" ml-auto collapse navbar-collapse" id="navbarNavDropdown">
                  <div class="ml-auto">
                     <ul class="navbar-nav">
                        <li class="nav-item active">
                           <a class="nav-link" href="index.html">Inicio <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                           <a class="nav-link dropdown-toggle" href="servicios.html" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           Servicios
                           </a>
                           <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="servicios.html">Mecanico</a>
                              <a class="dropdown-item" href="servicios.html">Electrico</a>
                              <a class="dropdown-item" href="servicios.html#Lubricacion">Lubricacion</a>
                              <a class="dropdown-item" href="servicios.html#Hojalateria">Hojalateria y pintura</a>
                              <a class="dropdown-item" href="servicios.html#Llantas">Reparacion de llantas</a>
                              <a class="dropdown-item" href="servicios.html#Vestidura">Vestidura</a>
                              <a class="dropdown-item" href="servicios.html#Mofles">Mofles</a>
                           </div>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="contacto.html">Contactanos</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="iniciarSesion.php">Iniciar Sesion</a>
                        </li>
                     </ul>
                  </div>
               </div>
            </nav>
         </div>
         
         <section class="contenedorLogin">
        
         <div class="d-flex justify-content-center">
          
          
<form action="php/sesion/validar.php" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Direccion de correo</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Correo electronico" name="correo">
    <small id="emailHelp" class="form-text text-muted">Necesita ser administrador para iniciar sesion</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Contraseña</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Contraseña" name="contraseña">
  </div>
  <button type="submit" class="btn btn-primary">Iniciar</button>
</form>
       
         </div>
         </section>
      </div>
      
      <!-- Optional JavaScript -->
      <!-- jQuery first, then Popper.js, then Bootstrap JS -->
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
      <script src="js/bootstrap.js" ></script>
   </body>
</html>