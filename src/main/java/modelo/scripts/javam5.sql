-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 16, 2022 at 11:16 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javam5`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrativo`
--

CREATE TABLE `administrativo` (
  `fk_id_usuario` int(11) NOT NULL,
  `area` varchar(20) NOT NULL,
  `experiencia_previa` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `administrativo`
--

INSERT INTO `administrativo` (`fk_id_usuario`, `area`, `experiencia_previa`) VALUES
(4, 'Contabilidad', '1 año'),
(5, 'Contabilidad', '2 años'),
(6, 'Contabilidad', '3 años');

-- --------------------------------------------------------

--
-- Table structure for table `capacitacion`
--

CREATE TABLE `capacitacion` (
  `id_capacitacion` int(11) NOT NULL,
  `rut_empresa` varchar(12) NOT NULL,
  `dia` varchar(12) NOT NULL,
  `hora` time NOT NULL,
  `lugar` varchar(50) NOT NULL,
  `duracion` varchar(70) NOT NULL,
  `cantidad_asistentes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `capacitacion`
--

INSERT INTO `capacitacion` (`id_capacitacion`, `rut_empresa`, `dia`, `hora`, `lugar`, `duracion`, `cantidad_asistentes`) VALUES
(4, '98000001-k', 'Lunes', '00:00:00', 'Santiago', '4 horas', 12),
(5, '98000002-k', 'Martes', '18:00:00', 'Santiago', '4 horas', 14),
(6, '98000003-k', 'Miercoles', '18:00:00', 'Santiago', '4 horas', 16),
(7, '98000004-k', 'Jueves', '18:00:00', 'Santiago', '4 horas', 18),
(8, '98000005-k', 'Viernes', '18:00:00', 'Santiago', '4 horas', 20);

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `fk_id_usuario` int(11) NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `afp` varchar(30) NOT NULL,
  `sistema_salud` enum('0','1') NOT NULL,
  `direccion` varchar(70) NOT NULL,
  `comuna` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`fk_id_usuario`, `nombres`, `apellidos`, `telefono`, `afp`, `sistema_salud`, `direccion`, `comuna`) VALUES
(1, 'Li Tian', 'Wu Zhu', '333-333-333', 'habitad', '1', 'Ao', 'Zhi-Wen'),
(2, 'Yan yan', 'Liu Chen', '555-555-555', 'habitad', '1', 'Cao', 'Tong'),
(3, 'Hui ying', 'Xu Zhao', '777-777-777', 'habitad', '1', 'Pekin', 'Luguan');

-- --------------------------------------------------------

--
-- Table structure for table `profesional`
--

CREATE TABLE `profesional` (
  `fk_id_usuario` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `fecha_ingreso` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `profesional`
--

INSERT INTO `profesional` (`fk_id_usuario`, `titulo`, `fecha_ingreso`) VALUES
(7, 'Developer', '2020-07-01'),
(8, 'Software engineer', '2020-08-01'),
(9, 'QA Testing', '2020-09-01');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `correo` varchar(64) NOT NULL,
  `clave` varchar(64) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `run` varchar(12) NOT NULL,
  `type` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `correo`, `clave`, `nombre_usuario`, `fecha_nacimiento`, `run`, `type`) VALUES
(1, 'user1@domain.com', '123456', 'Li', '2000-01-01', '20001000-1', 'cliente'),
(2, 'user2@domain.com', '123456', 'Yan', '2000-02-01', '20002000-2', 'cliente'),
(3, 'user3@domain.com', '123456', 'Hui', '2000-03-01', '20003000-3', 'cliente'),
(4, 'user4@domain.com', '123456', 'Tao', '2000-04-01', '20004000-4', 'administrativo'),
(5, 'user5@domain.com', '123456', 'Siu', '2000-05-01', '20005000-5', 'administrativo'),
(6, 'user6@domain.com', '123456', 'Jun', '2000-06-01', '20006000-6', 'administrativo'),
(7, 'user7@domain.com', '123456', 'Ryu', '2000-07-01', '20007000-7', 'profesional'),
(8, 'user8@domain.com', '123456', 'Ken', '2000-08-01', '20008000-8', 'profesional'),
(9, 'user9@domain.com', '123456', 'Lin', '2000-09-01', '20009000-9', 'profesional');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrativo`
--
ALTER TABLE `administrativo`
  ADD KEY `administrativo_ibfk_1` (`fk_id_usuario`);

--
-- Indexes for table `capacitacion`
--
ALTER TABLE `capacitacion`
  ADD PRIMARY KEY (`id_capacitacion`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD KEY `cliente_ibfk_1` (`fk_id_usuario`);

--
-- Indexes for table `profesional`
--
ALTER TABLE `profesional`
  ADD KEY `profesional_ibfk_1` (`fk_id_usuario`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `capacitacion`
--
ALTER TABLE `capacitacion`
  MODIFY `id_capacitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrativo`
--
ALTER TABLE `administrativo`
  ADD CONSTRAINT `administrativo_ibfk_1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Constraints for table `profesional`
--
ALTER TABLE `profesional`
  ADD CONSTRAINT `profesional_ibfk_1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
