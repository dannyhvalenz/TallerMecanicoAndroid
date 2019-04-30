-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2019 at 02:29 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `administrador`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrador`
--

CREATE TABLE `administrador` (
  `Identificador` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `Contraseña` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrador`
--

INSERT INTO `administrador` (`Identificador`, `Nombre`, `Correo`, `Contraseña`) VALUES
(1, 'luis', 'luisparada@gmail.com', '12345'),
(2, 'sebas', 'sebas@gmail.com', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `automovil`
--

CREATE TABLE `automovil` (
  `Matricula` varchar(20) NOT NULL,
  `Marca` varchar(30) NOT NULL,
  `Modelo` varchar(10) NOT NULL,
  `Linea` varchar(30) NOT NULL,
  `Color` varchar(20) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_administrador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `automovil`
--

INSERT INTO `automovil` (`Matricula`, `Marca`, `Modelo`, `Linea`, `Color`, `id_cliente`, `id_administrador`) VALUES
('s160122', 'ford', '2021', 'sasas', 'rojo', 14, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `Identificador` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Direccion` varchar(150) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `id_aministrador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`Identificador`, `Nombre`, `Telefono`, `Direccion`, `Correo`, `id_aministrador`) VALUES
(14, 'karla', '2282817261', 'av. de los alamos, la pradera, cipres edif:e-8 depto:22, xalapa ver', 'sebas@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reparacion`
--

CREATE TABLE `reparacion` (
  `Identificador` int(11) NOT NULL,
  `Tipo` varchar(20) NOT NULL,
  `Costo` double NOT NULL,
  `Kilometraje` double NOT NULL,
  `Descripcion_falla` varchar(500) NOT NULL,
  `Descripcion_reparacion` varchar(500) NOT NULL,
  `Matricula_auto` varchar(20) NOT NULL,
  `id_administrador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`Identificador`),
  ADD UNIQUE KEY `Correo` (`Correo`);

--
-- Indexes for table `automovil`
--
ALTER TABLE `automovil`
  ADD PRIMARY KEY (`Matricula`),
  ADD KEY `id_administrador` (`id_administrador`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Identificador`),
  ADD KEY `id_aministrador` (`id_aministrador`);

--
-- Indexes for table `reparacion`
--
ALTER TABLE `reparacion`
  ADD PRIMARY KEY (`Identificador`),
  ADD KEY `id_administrador` (`id_administrador`),
  ADD KEY `reparacion_ibfk_2` (`Matricula_auto`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrador`
--
ALTER TABLE `administrador`
  MODIFY `Identificador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `Identificador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `reparacion`
--
ALTER TABLE `reparacion`
  MODIFY `Identificador` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `automovil`
--
ALTER TABLE `automovil`
  ADD CONSTRAINT `automovil_ibfk_1` FOREIGN KEY (`id_administrador`) REFERENCES `administrador` (`Identificador`),
  ADD CONSTRAINT `automovil_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`Identificador`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_aministrador`) REFERENCES `administrador` (`Identificador`);

--
-- Constraints for table `reparacion`
--
ALTER TABLE `reparacion`
  ADD CONSTRAINT `reparacion_ibfk_1` FOREIGN KEY (`id_administrador`) REFERENCES `administrador` (`Identificador`),
  ADD CONSTRAINT `reparacion_ibfk_2` FOREIGN KEY (`Matricula_auto`) REFERENCES `automovil` (`Matricula`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
