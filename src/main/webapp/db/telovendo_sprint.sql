-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 24, 2022 at 07:04 PM
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
-- Database: `telovendo`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `confirmar_transaccion` ()   BEGIN
	CALL telovendo.debug_msg('TRANSACCION CONFIRMADA');
	COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `debug_msg` (`msg` VARCHAR(255))   BEGIN
    INSERT INTO logtable (message, fecha) VALUES (msg, NOW());
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `descartar_transaccion` ()   BEGIN
	CALL telovendo.debug_msg('TRANSACCION DESCARTADA');
	ROLLBACK;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `poblar` ()   BEGIN
  SELECT * FROM cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `transferir` (IN `id_cliente_origen` INT(11), IN `id_cliente_destino` INT(11), IN `monto` INT(11))   BEGIN 
	DECLARE nombre_origen VARCHAR(64);
	DECLARE nombre_destino VARCHAR(64);
	# Llama la funcion que comprueba que la cuenta tenga saldo suficiente.
	call telovendo.verificar_saldo(id_cliente_origen, monto, @resultado);
	IF (@resultado) THEN 
		START TRANSACTION;
		SET autocommit=0;
	
		SET nombre_origen = (SELECT nombres FROM cliente WHERE id=id_cliente_origen);
		SET nombre_destino = (SELECT nombres FROM cliente WHERE id=id_cliente_destino);
	
		INSERT INTO movimiento (fk_cuenta, tipo, monto, fecha) VALUES((SELECT cuenta FROM cuenta WHERE fk_id_cliente = id_cliente_origen), 'cargo', monto, now()) ;
		INSERT INTO movimiento (fk_cuenta, tipo, monto, fecha) VALUES((SELECT cuenta FROM cuenta WHERE fk_id_cliente = id_cliente_destino), 'abono', monto, now()) ;
	
		UPDATE cuenta SET saldo = saldo - monto WHERE fk_id_cliente = id_cliente_origen;
		UPDATE cuenta SET saldo = saldo + monto WHERE fk_id_cliente = id_cliente_destino;
		 
		call telovendo.debug_msg(CONCAT('SE HA TRANSFERIDO UN MONTO DE: ', monto, ' DESDE LA CUENTA DE: ', nombre_origen, ' A LA CUENTA DE: ', nombre_destino));
		call telovendo.debug_msg('EJECUTE "COMMIT" PARA CONFIRMAR O "ROLLBACK", PARA DESCARTAR LA TRANSACCION');
	ELSE
		call telovendo.debug_msg('TRANSACCION FALLIDA');
	END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_saldo` (IN `id_user` INT(11), IN `monto` INT(11), OUT `respuesta` BOOL)   BEGIN

	IF((SELECT saldo FROM cuenta WHERE fk_id_cliente=id_user) >= monto) THEN  
		call telovendo.debug_msg('SALDO SUFICIENTE PARA REALIZAR TRANZACCION');
		SET respuesta = true;
	ELSE 
		call telovendo.debug_msg('SALDO SUFICIENTE PARA REALIZAR TRANZACCION');
		SET respuesta = false;
	END IF;
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `myfuncion` (`credit` INT(11)) RETURNS INT(11) DETERMINISTIC BEGIN
	DECLARE numero INT(11);
	SELECT count(*) into numero FROM cliente; 
	RETURN (numero); 
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `verificar_saldo_old` (`id_user` INT(11), `monto` INT(11)) RETURNS TINYINT(1) DETERMINISTIC BEGIN 
	DECLARE vsaldo INT(11); 
	SELECT saldo INTO vsaldo FROM cuenta WHERE fk_id_cliente=id_user;

	IF (vsaldo >= monto) THEN 
		RETURN (true);
	ELSE 
		RETURN (false);
	END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nombre`) VALUES
(1, 'Historia Geo Politica'),
(2, 'Ciencia Ficcion'),
(3, 'Biografias'),
(4, 'Matematicas'),
(5, 'Historia'),
(6, 'Best seller'),
(7, 'Infantiles'),
(8, 'Economia');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombres` varchar(64) NOT NULL,
  `apellidos` varchar(64) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `direccion` varchar(64) NOT NULL,
  `comuna` varchar(32) NOT NULL,
  `correo` varchar(64) NOT NULL,
  `fecha_reg` date NOT NULL,
  `total_pagado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `nombres`, `apellidos`, `telefono`, `direccion`, `comuna`, `correo`, `fecha_reg`, `total_pagado`) VALUES
(1, 'MARY', 'SMITH', '525518075499', '47 MySakila Drive', 'Alberta', 'MARY.SMITH@sakilacustomer.org', '2005-05-26', 3670),
(2, 'PATRICIA', 'JOHNSON', '324346485054', '28 MySQL Boulevard', 'QLD', 'PATRICIA.JOHNSON@sakilacustomer.org', '2005-05-28', 15250),
(3, 'LINDA', 'WILLIAMS', '618156722572', '23 Workhaven Lane', 'Alberta', 'LINDA.WILLIAMS@sakilacustomer.org', '2005-06-01', 17110),
(4, 'BARBARA', 'JONES', '128499386727', '1411 Lillydale Drive', 'QLD', 'BARBARA.JONES@sakilacustomer.org', '2005-06-03', 24520),
(5, 'ELIZABETH', 'BROWN', '203804046132', '1913 Hanoi Way', 'Nagasaki', 'ELIZABETH.BROWN@sakilacustomer.org', '2005-06-02', 20790),
(6, 'JENNIFER', 'DAVIS', '770864062795', '1121 Loja Avenue', 'California', 'JENNIFER.DAVIS@sakilacustomer.org', '2005-05-27', 27920),
(7, 'MARIA', 'MILLER', '867287719310', '692 Joliet Street', 'Attika', 'MARIA.MILLER@sakilacustomer.org', '2005-05-29', 39950),
(8, 'SUSAN', 'WILSON', '844018348565', '1566 Inegl Manor', 'Mandalay', 'SUSAN.WILSON@sakilacustomer.org', '2005-05-27', 23460),
(9, 'MARGARET', 'MOORE', '315528269898', '53 Idfu Parkway', 'Nantou', 'MARGARET.MOORE@sakilacustomer.org', '2005-05-28', 25800),
(10, 'DOROTHY', 'TAYLOR', '14465669789', '1795 Santiago de Compostela Way', 'Texas', 'DOROTHY.TAYLOR@sakilacustomer.org', '2005-05-31', 18240),
(11, 'LISA', 'ANDERSON', '278669994384', '900 Santiago de Compostela Parkway', 'Central Serbia', 'LISA.ANDERSON@sakilacustomer.org', '2005-06-02', 44430),
(12, 'NANCY', 'THOMAS', '29341849811', '478 Joliet Way', 'Hamilton', 'NANCY.THOMAS@sakilacustomer.org', '2005-05-30', 15840),
(13, 'KAREN', 'JACKSON', '377633994405', '613 Korolev Drive', 'Masqat', 'KAREN.JACKSON@sakilacustomer.org', '2005-05-30', 22940),
(14, 'BETTY', 'WHITE', '954786054144', '1531 Sal Drive', 'Esfahan', 'BETTY.WHITE@sakilacustomer.org', '2005-05-26', 27010),
(15, 'HELEN', 'HARRIS', '165164761435', '1542 Tarlac Parkway', 'Kanagawa', 'HELEN.HARRIS@sakilacustomer.org', '2005-06-03', 30490),
(16, 'SANDRA', 'MARTIN', '294449058179', '808 Bhopal Manor', 'Haryana', 'SANDRA.MARTIN@sakilacustomer.org', '2005-05-26', 3890),
(17, 'DONNA', 'THOMPSON', '995527378381', '270 Amroha Parkway', 'Osmaniye', 'DONNA.THOMPSON@sakilacustomer.org', '2005-05-27', 8300),
(18, 'CAROL', 'GARCIA', '285710089439', '770 Bydgoszcz Avenue', 'California', 'CAROL.GARCIA@sakilacustomer.org', '2005-05-31', 33760),
(19, 'RUTH', 'MARTINEZ', '170115379190', '419 Iligan Lane', 'Madhya Pradesh', 'RUTH.MARTINEZ@sakilacustomer.org', '2005-05-31', 19410),
(20, 'SHARON', 'ROBINSON', '914466027044', '360 Toulouse Parkway', 'England', 'SHARON.ROBINSON@sakilacustomer.org', '2005-05-27', 35170),
(21, 'MICHELLE', 'CLARK', '182178609211', '270 Toulon Boulevard', 'Kalmykia', 'MICHELLE.CLARK@sakilacustomer.org', '2005-05-26', 1460),
(22, 'LAURA', 'RODRIGUEZ', '387448063440', '320 Brest Avenue', 'Kaduna', 'LAURA.RODRIGUEZ@sakilacustomer.org', '2005-05-26', 7270),
(23, 'SARAH', 'LEWIS', '338758048786', '1417 Lancaster Avenue', 'Northern Cape', 'SARAH.LEWIS@sakilacustomer.org', '2005-05-29', 44410),
(24, 'KIMBERLY', 'LEE', '154124128457', '1688 Okara Way', 'Nothwest Border Prov', 'KIMBERLY.LEE@sakilacustomer.org', '2005-05-27', 32730),
(25, 'DEBORAH', 'WALKER', '864392582257', '262 A Corua (La Corua) Parkway', 'Dhaka', 'DEBORAH.WALKER@sakilacustomer.org', '2005-05-27', 39610),
(26, 'JESSICA', 'HALL', '410877354933', '28 Charlotte Amalie Street', 'Rabat-Sal-Zammour-Z', 'JESSICA.HALL@sakilacustomer.org', '2005-05-31', 43710),
(27, 'SHIRLEY', 'ALLEN', '437829801725', '1780 Hino Boulevard', 'Liepaja', 'SHIRLEY.ALLEN@sakilacustomer.org', '2005-05-30', 12250),
(28, 'CYNTHIA', 'YOUNG', '137809746111', '96 Tafuna Way', 'Crdoba', 'CYNTHIA.YOUNG@sakilacustomer.org', '2005-05-26', 40680),
(29, 'ANGELA', 'HERNANDEZ', '640843562301', '934 San Felipe de Puerto Plata Street', 'Sind', 'ANGELA.HERNANDEZ@sakilacustomer.org', '2005-05-30', 6110),
(30, 'MELISSA', 'KING', '935952366111', '18 Duisburg Boulevard', 'Sind', 'MELISSA.KING@sakilacustomer.org', '2005-05-30', 37440);

-- --------------------------------------------------------

--
-- Table structure for table `contacto`
--

CREATE TABLE `contacto` (
  `id_contacto` int(11) NOT NULL,
  `fk_id_usuario` int(11) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `correo` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cuenta`
--

CREATE TABLE `cuenta` (
  `fk_id_cliente` int(11) NOT NULL,
  `cuenta` int(11) NOT NULL,
  `saldo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cuenta`
--

INSERT INTO `cuenta` (`fk_id_cliente`, `cuenta`, `saldo`) VALUES
(1, 100001, 1200),
(2, 100002, 1000),
(3, 100003, 500),
(4, 100004, 1300),
(5, 100005, 1000),
(6, 100006, 1000),
(7, 100007, 1000),
(8, 100008, 1000),
(9, 100009, 1000),
(10, 100010, 1000),
(11, 100011, 1000),
(12, 100012, 1000),
(13, 100013, 1000),
(14, 100014, 1000),
(15, 100015, 1000),
(16, 100016, 1000),
(17, 100017, 1000),
(18, 100018, 1000),
(19, 100019, 1000),
(20, 100020, 1000),
(21, 100021, 1000),
(22, 100022, 1000),
(23, 100023, 1000),
(24, 100024, 1000),
(25, 100025, 1000),
(26, 100026, 1000),
(27, 100027, 1000),
(28, 100028, 1000),
(29, 100029, 1000),
(30, 100030, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `fecha_hora`
--

CREATE TABLE `fecha_hora` (
  `id_ingreso` int(11) NOT NULL,
  `fk_id_user` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fecha_hora`
--

INSERT INTO `fecha_hora` (`id_ingreso`, `fk_id_user`, `fecha_hora`) VALUES
(1, 1, '2022-04-21 12:32:04'),
(2, 2, '2022-04-21 12:32:18'),
(3, 3, '2022-04-21 12:32:25'),
(4, 4, '2022-04-21 12:32:29'),
(5, 5, '2022-04-21 12:32:35'),
(6, 6, '2022-04-21 12:32:41'),
(7, 7, '2022-04-21 12:32:45'),
(8, 8, '2022-04-21 12:32:49');

-- --------------------------------------------------------

--
-- Table structure for table `logtable`
--

CREATE TABLE `logtable` (
  `id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `logtable`
--

INSERT INTO `logtable` (`id`, `message`, `fecha`) VALUES
(1, 'SALDO SUFICIENTE PARA REALIZAR TRANZACCION', '2022-04-17 22:46:46'),
(2, 'SE HA TRANSFERIDO UN MONTO DE: 200 DESDE LA CUENTA DE: MARY A LA CUENTA DE: PATRICIA', '2022-04-17 22:46:46'),
(3, 'EJECUTE \"COMMIT\" PARA CONFIRMAR O \"ROLLBACK\", PARA DESCARTAR LA TRANSACCION', '2022-04-17 22:46:46'),
(4, 'TRANSACCION DESCARTADA', '2022-04-17 22:56:18'),
(5, 'SALDO SUFICIENTE PARA REALIZAR TRANZACCION', '2022-04-17 22:58:12'),
(6, 'SE HA TRANSFERIDO UN MONTO DE: 150 DESDE LA CUENTA DE: PATRICIA A LA CUENTA DE: LINDA', '2022-04-17 22:58:12'),
(7, 'EJECUTE \"COMMIT\" PARA CONFIRMAR O \"ROLLBACK\", PARA DESCARTAR LA TRANSACCION', '2022-04-17 22:58:12'),
(8, 'TRANSACCION DESCARTADA', '2022-04-17 23:02:26'),
(9, 'SALDO SUFICIENTE PARA REALIZAR TRANZACCION', '2022-04-17 23:03:33'),
(10, 'SE HA TRANSFERIDO UN MONTO DE: 500 DESDE LA CUENTA DE: LINDA A LA CUENTA DE: BARBARA', '2022-04-17 23:03:33'),
(11, 'EJECUTE \"COMMIT\" PARA CONFIRMAR O \"ROLLBACK\", PARA DESCARTAR LA TRANSACCION', '2022-04-17 23:03:33'),
(12, 'TRANSACCION CONFIRMADA', '2022-04-17 23:05:00'),
(13, 'SALDO SUFICIENTE PARA REALIZAR TRANZACCION', '2022-04-17 23:06:05'),
(14, 'SE HA TRANSFERIDO UN MONTO DE: 200 DESDE LA CUENTA DE: BARBARA A LA CUENTA DE: MARY', '2022-04-17 23:06:05'),
(15, 'EJECUTE \"COMMIT\" PARA CONFIRMAR O \"ROLLBACK\", PARA DESCARTAR LA TRANSACCION', '2022-04-17 23:06:05'),
(16, 'TRANSACCION CONFIRMADA', '2022-04-17 23:08:40');

-- --------------------------------------------------------

--
-- Table structure for table `movimiento`
--

CREATE TABLE `movimiento` (
  `id_movimiento` int(11) NOT NULL,
  `fk_cuenta` int(11) NOT NULL,
  `tipo` enum('cargo','abono') NOT NULL,
  `monto` int(11) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `movimiento`
--

INSERT INTO `movimiento` (`id_movimiento`, `fk_cuenta`, `tipo`, `monto`, `fecha`) VALUES
(5, 100003, 'cargo', 500, '2022-04-17 23:03:33'),
(6, 100004, 'abono', 500, '2022-04-17 23:03:33'),
(7, 100004, 'cargo', 200, '2022-04-17 23:06:05'),
(8, 100001, 'abono', 200, '2022-04-17 23:06:05');

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `sku` int(11) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `fabricacion` varchar(16) NOT NULL,
  `stock` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `color` varchar(16) NOT NULL DEFAULT 'blanco',
  `fk_id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`sku`, `nombre`, `fabricacion`, `stock`, `precio`, `color`, `fk_id_categoria`) VALUES
(100001, ' Roma soy yo  ', 'chile', 10, 18900, 'blanco', 1),
(100002, ' El mir de Miguel. Crónicas de Memorias (Estuche 2 Vols)  ', 'chile', 10, 40500, 'blanco', 2),
(100003, ' (Preventa) la Patria en Sombras  ', 'chile', 10, 15210, 'blanco', 3),
(100004, ' (Preventa) Lascivia  ', 'chile', 10, 15300, 'blanco', 4),
(100005, ' Alto en salud  ', 'chile', 10, 14400, 'blanco', 5),
(100006, ' La lista de la suerte  ', 'chile', 10, 12600, 'blanco', 6),
(100007, ' La ciencia oscura  ', 'chile', 10, 10800, 'blanco', 7),
(100008, ' (Preventa) Astrología Lunar  ', 'chile', 10, 13410, 'blanco', 8),
(100009, ' Pruebas de apolo la maldicion del campa  ', 'chile', 10, 10800, 'blanco', 1),
(100010, ' Levantame los Pies  ', 'chile', 10, 9730, 'negro', 2),
(100011, ' (Preventa) el Hechizo del Agua  ', 'chile', 10, 17910, 'blanco', 3),
(100012, ' (Preventa) Visión Cromática  ', 'chile', 10, 15210, 'blanco', 4),
(100013, ' (Preventa) El mentalista  ', 'chile', 10, 8400, 'blanco', 5),
(100014, ' Jujutsu Kaisen 14 ', 'chile', 10, 13500, 'blanco', 6),
(100015, ' Casa de cielo y aliento  ', 'chile', 10, 11590, 'blanco', 7),
(100016, ' Manifiesto Para los Héroes de Cada Día: Activa tu Positivismo Maximiza tu Productividad Sirve al Mundo (Divulgación)  ', 'chile', 10, 13500, 'blanco', 8),
(100017, ' El Rey De La Máscara Oscura  ', 'chile', 10, 16560, 'blanco', 1),
(100018, ' (Preventa) el Mapa de los Anhelos  ', 'chile', 10, 8400, 'blanco', 2),
(100019, ' Desobediente  ', 'chile', 10, 6790, 'blanco', 3),
(100020, ' Fleur. Mi Desesperada Decisión  ', 'chile', 10, 16200, 'blanco', 4),
(100021, ' Cuando no queden más estrellas que contar  ', 'chile', 10, 10160, 'blanco', 5),
(100022, ' Kame Un Océanos De Emociones  ', 'chile', 10, 10500, 'blanco', 6),
(100023, ' Reino Animal ', 'chile', 10, 23800, 'blanco', 7),
(100024, ' Ojos que no ven  ', 'chile', 10, 15300, 'blanco', 8),
(100025, ' Muerte en el Nilo ', 'chile', 10, 4150, 'blanco', 1),
(100026, ' Diez Miradas Sobre el Sistema de Gobierno  ', 'chile', 10, 11700, 'blanco', 2),
(100027, ' Loca pelvis  ', 'chile', 10, 26970, 'blanco', 3),
(100028, ' Cuerpos  ', 'chile', 10, 10120, 'blanco', 4),
(100029, ' Al Paraiso  ', 'chile', 10, 13430, 'blanco', 1);

-- --------------------------------------------------------

--
-- Table structure for table `producto_proveedor`
--

CREATE TABLE `producto_proveedor` (
  `fk_id_producto` int(11) DEFAULT NULL,
  `fk_id_proveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto_proveedor`
--

INSERT INTO `producto_proveedor` (`fk_id_producto`, `fk_id_proveedor`) VALUES
(100001, 1),
(100002, 2),
(100003, 3),
(100004, 4),
(100005, 5),
(100006, 6),
(100007, 7),
(100008, 8),
(100009, 1),
(100010, 2),
(100011, 3),
(100012, 4),
(100013, 5),
(100014, 6),
(100015, 7),
(100016, 8),
(100017, 1),
(100018, 2),
(100019, 3),
(100020, 4),
(100021, 5),
(100022, 6),
(100023, 7),
(100024, 8),
(100025, 1),
(100026, 2),
(100027, 3),
(100028, 4),
(100029, 1);

-- --------------------------------------------------------

--
-- Table structure for table `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `representante_legal` varchar(64) NOT NULL,
  `nombre_corporativo` varchar(64) NOT NULL,
  `telefono_1` varchar(16) DEFAULT NULL,
  `telefono_2` varchar(16) DEFAULT NULL,
  `nombre_atencion` varchar(64) DEFAULT NULL,
  `casilla_facturas` varchar(64) DEFAULT NULL,
  `fk_id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `representante_legal`, `nombre_corporativo`, `telefono_1`, `telefono_2`, `nombre_atencion`, `casilla_facturas`, `fk_id_categoria`) VALUES
(1, 'KIRK STCLAIR', 'Azeta', '569-61002010', '569-61002018', 'ISAAC OGLESBY', 'casilla@Azeta.com', 1),
(2, 'SERGIO STANFIELD', 'Elkar', '569-61002011', '569-61002019', 'MORRIS MCCARTER', 'casilla@Elkar.com', 2),
(3, 'MARION OCAMPO', 'Arnoia', '569-61002012', '569-61002020', 'CLIFTON MALCOLM', 'casilla@Arnoia.com', 3),
(4, 'TRACY HERRMANN', 'Asturlibros', '569-61002013', '569-61002021', 'WILLARD LUMPKIN', 'casilla@Asturlibros.com', 4),
(5, 'SETH HANNON', 'Distriforma', '569-61002014', '569-61002022', 'DARYL LARUE', 'casilla@Distriforma.com', 5),
(6, 'KENT ARSENAULT', 'Les Punxes', '569-61002015', '569-61002023', 'ROSS GREY', 'casilla@Les.Punxes.com', 6),
(7, 'TERRANCE ROUSH', 'Grupo Machado', '569-61002016', '569-61002024', 'VIRGIL WOFFORD', 'casilla@Grupo.Machado.com', 7),
(8, 'RENE MCALISTER', 'Podiprint', '569-61002017', '569-61002025', 'ANDY VANHORN', 'casilla@Podiprint.com', 8);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `apellido` varchar(64) NOT NULL,
  `clave` varchar(32) NOT NULL,
  `zona_horaria` varchar(6) NOT NULL DEFAULT 'UTC-2',
  `genero` char(1) NOT NULL,
  `telefono` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `clave`, `zona_horaria`, `genero`, `telefono`) VALUES
(1, 'MARY', 'SMITH', 'XYZ123', 'UTC-3', 'f', '28303384290'),
(2, 'PATRICIA', 'JOHNSON', 'XYZ124', 'UTC-4', 'f', '838635286649'),
(3, 'LINDA', 'WILLIAMS', 'XYZ125', 'UTC-5', 'f', '448477190408'),
(4, 'BARBARA', 'JONES', 'XYZ126', 'UTC-6', 'f', '705814003527'),
(5, 'ELIZABETH', 'BROWN', 'XYZ127', 'UTC-7', 'f', '10655648674'),
(6, 'JENNIFER', 'DAVIS', 'XYZ128', 'UTC-8', 'f', '860452626434'),
(7, 'MARIA', 'MILLER', 'XYZ129', 'UTC-9', 'f', '716571220373'),
(8, 'SUSAN', 'WILSON', 'XYZ130', 'UTC-10', 'f', '716735712203');

-- --------------------------------------------------------

--
-- Table structure for table `vendedor`
--

CREATE TABLE `vendedor` (
  `run` varchar(12) NOT NULL,
  `nombres` varchar(64) NOT NULL,
  `apellidos` varchar(64) NOT NULL,
  `fecha_nac` date NOT NULL,
  `seccion` varchar(16) NOT NULL,
  `salario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vendedor`
--

INSERT INTO `vendedor` (`run`, `nombres`, `apellidos`, `fecha_nac`, `seccion`, `salario`) VALUES
('100706050-1', 'James', 'Butt', '1986-12-23', 'libreria', 900000),
('101716151-2', 'Josephine', 'Darakjy', '2000-01-15', 'libreria', 900000),
('102726252-3', 'Art', 'Venere', '2001-04-19', 'libreria', 800000),
('103736353-4', 'Lenna', 'Paprocki', '2001-02-09', 'libreria', 1100000),
('104746454-5', 'Donette', 'Foller', '1999-10-01', 'libreria', 800000),
('105756555-6', 'Simona', 'Morasca', '1980-11-11', 'libreria', 700000),
('106766656-7', 'Mitsue', 'Tollner', '2002-12-05', 'libreria', 1100000),
('107776757-8', 'Leota', 'Dilliard', '1981-05-02', 'libreria', 700000),
('108786858-9', 'Sage', 'Wieser', '1984-10-07', 'libreria', 800000),
('109796959-k', 'Kris', 'Marrier', '2002-03-17', 'libreria', 700000),
('110807060-1', 'Minna', 'Amigon', '1994-01-25', 'libreria', 1000000),
('111817161-2', 'Abel', 'Maclead', '1996-11-03', 'libreria', 800000),
('112827262-3', 'Kiley', 'Caldarera', '1998-09-12', 'libreria', 700000),
('113837363-4', 'Graciela', 'Ruta', '2003-11-22', 'libreria', 1000000),
('114847464-5', 'Cammy', 'Albares', '2001-10-01', 'libreria', 1100000),
('115857565-6', 'Mattie', 'Poquette', '1995-04-30', 'libreria', 1100000),
('116867666-7', 'Meaghan', 'Garufi', '1987-08-27', 'libreria', 800000),
('117877767-8', 'Gladys', 'Rim', '1980-10-04', 'libreria', 1000000),
('118887868-9', 'Yuki', 'Whobrey', '1992-12-31', 'libreria', 900000),
('119897969-k', 'Fletcher', 'Flosi', '1984-03-14', 'libreria', 800000),
('120908070-1', 'Bette', 'Nicka', '1983-10-09', 'libreria', 900000),
('121918171-2', 'Veronika', 'Inouye', '2002-11-25', 'libreria', 1100000),
('122928272-3', 'Willard', 'Kolmetz', '1988-03-08', 'libreria', 1000000),
('123938373-4', 'Maryann', 'Royster', '2004-05-16', 'libreria', 800000),
('124948474-5', 'Alisha', 'Slusarski', '1995-10-28', 'libreria', 1100000),
('125958575-6', 'Allene', 'Iturbide', '1998-09-05', 'libreria', 800000),
('126968676-7', 'Chanel', 'Caudy', '1982-12-02', 'libreria', 800000),
('127978777-8', 'Ezekiel', 'Chui', '2000-11-07', 'libreria', 1000000),
('128988878-9', 'Willow', 'Kusko', '1981-06-25', 'libreria', 1000000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contacto`
--
ALTER TABLE `contacto`
  ADD PRIMARY KEY (`id_contacto`),
  ADD KEY `fk_id_usuario` (`fk_id_usuario`);

--
-- Indexes for table `cuenta`
--
ALTER TABLE `cuenta`
  ADD UNIQUE KEY `cuenta` (`cuenta`),
  ADD KEY `fk_id_cliente` (`fk_id_cliente`);

--
-- Indexes for table `fecha_hora`
--
ALTER TABLE `fecha_hora`
  ADD PRIMARY KEY (`id_ingreso`);

--
-- Indexes for table `logtable`
--
ALTER TABLE `logtable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD KEY `fk_cuenta` (`fk_cuenta`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`sku`),
  ADD KEY `fk_id_categoria` (`fk_id_categoria`);

--
-- Indexes for table `producto_proveedor`
--
ALTER TABLE `producto_proveedor`
  ADD KEY `fk_id_producto` (`fk_id_producto`),
  ADD KEY `fk_id_proveedor` (`fk_id_proveedor`);

--
-- Indexes for table `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`),
  ADD KEY `fk_id_categoria` (`fk_id_categoria`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`run`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `contacto`
--
ALTER TABLE `contacto`
  MODIFY `id_contacto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fecha_hora`
--
ALTER TABLE `fecha_hora`
  MODIFY `id_ingreso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `logtable`
--
ALTER TABLE `logtable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `movimiento`
--
ALTER TABLE `movimiento`
  MODIFY `id_movimiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contacto`
--
ALTER TABLE `contacto`
  ADD CONSTRAINT `contacto_ibfk_1` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Constraints for table `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`fk_id_cliente`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `movimiento`
--
ALTER TABLE `movimiento`
  ADD CONSTRAINT `fk_cuenta` FOREIGN KEY (`fk_cuenta`) REFERENCES `cuenta` (`cuenta`);

--
-- Constraints for table `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`fk_id_categoria`) REFERENCES `categoria` (`id_categoria`);

--
-- Constraints for table `producto_proveedor`
--
ALTER TABLE `producto_proveedor`
  ADD CONSTRAINT `producto_proveedor_ibfk_1` FOREIGN KEY (`fk_id_producto`) REFERENCES `producto` (`sku`),
  ADD CONSTRAINT `producto_proveedor_ibfk_2` FOREIGN KEY (`fk_id_proveedor`) REFERENCES `proveedor` (`id_proveedor`);

--
-- Constraints for table `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`fk_id_categoria`) REFERENCES `categoria` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
