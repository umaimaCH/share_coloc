-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 11, 2022 at 01:58 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shareloc`
--

-- --------------------------------------------------------

--
-- Table structure for table `collocation`
--

DROP TABLE IF EXISTS `collocation`;
CREATE TABLE IF NOT EXISTS `collocation` (
  `idCollocation` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `idUser` varchar(255) NOT NULL,
  PRIMARY KEY (`idCollocation`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `collocation`
--

INSERT INTO `collocation` (`idCollocation`, `name`, `idUser`) VALUES
(1, 'skiski', '1'),
(5, 'Collocation de zeineb', '13'),
(6, 'Collocation ', '13'),
(7, 'Collocation ', '13'),
(8, 'Collocation ', '13'),
(9, 'Collocation de adam ', '13');

-- --------------------------------------------------------

--
-- Table structure for table `collocation_service`
--

DROP TABLE IF EXISTS `collocation_service`;
CREATE TABLE IF NOT EXISTS `collocation_service` (
  `collocationName` varchar(255) DEFAULT NULL,
  `serviceName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `idservice` int(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `cost` int(255) NOT NULL,
  `idcollocation` int(255) DEFAULT NULL,
  `admin` varchar(255) NOT NULL,
  PRIMARY KEY (`idservice`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `score` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `email`, `password`, `firstname`, `lastname`, `score`, `token`) VALUES
(1, 'oumaima.chtioui@uha.fr', 'oumaima', 'chtioui', 'oumaima', 0000000000, '0'),
(2, 'Zeineb@uha.fr', 'oumaima', 'chtioui', 'oumaima', 0000000000, '0'),
(3, 'Zeineb@uha.fr', 'oumaima', 'chtioui', 'oumaima', 0000000000, '0'),
(4, 'Zeineb@uha.fr', 'oumaima', 'chtioui', 'oumaima', 0000000000, '0'),
(5, 'o', 'o', 'o', 'o', 0000000000, '0'),
(6, 'zdhpi', 'dzpihzd', 'zidh', 'ouzdg', 0000000000, '0'),
(7, 'zid', 'zdni', 'kzdjz', 'oumaimaa', 0000000000, '0'),
(8, 'jj', 'jj', 'jj', 'jj', 0000000000, '0'),
(9, 'eosj', 'ed,oj', 'zpdj', 'azrour', 0000000000, '0'),
(10, 'ZDCSZ', 'KZC.M', 'KZCDC', 'ZSMKZ', 0000000000, '0'),
(12, 'oumaimazeine@gmail.com', '123', 'Oumaima', 'chtioui', 0000000000, 'U1cGbhsGZb9Eya9ncZGOIyq7fXmyiKet'),
(13, 'hello@', 'zeinebzelleg', 'zeineb', 'zelleg', 0000000000, 'Zi6Yi2HFxLuP5LNFE6EMi-9nQbf1E2Sw');

-- --------------------------------------------------------

--
-- Table structure for table `user_collocation`
--

DROP TABLE IF EXISTS `user_collocation`;
CREATE TABLE IF NOT EXISTS `user_collocation` (
  `Collocationid` int(255) NOT NULL,
  `Userid` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_collocation`
--

INSERT INTO `user_collocation` (`Collocationid`, `Userid`) VALUES
(0, 0),
(0, 0),
(0, 0),
(5, 1),
(5, 2),
(5, 3),
(1, 3),
(5, 12),
(5, 12),
(5, 12),
(5, 12),
(5, 12),
(5, 12);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
