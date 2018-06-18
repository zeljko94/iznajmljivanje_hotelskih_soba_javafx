-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2018 at 04:47 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iznajmljivanje_hotelskih_soba`
--

CREATE DATABASE IF NOT EXISTS  `iznajmljivanje_hotelskih_soba` 
DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE `rezervacija` (
  `id` int(11) NOT NULL,
  `pocetni_datum` date DEFAULT NULL,
  `zavrsni_datum` date DEFAULT NULL,
  `id_korisnika` int(11) DEFAULT NULL,
  `id_sobe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `rezervacija` (`id`, `pocetni_datum`, `zavrsni_datum`, `id_korisnika`, `id_sobe`) VALUES
(4, '2018-06-01', '2018-06-03', 2, 2),
(5, '2018-06-01', '2018-06-03', 2, 3),
(6, '2018-06-04', '2018-06-05', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `soba`
--

CREATE TABLE `soba` (
  `id` int(11) NOT NULL,
  `broj_sobe` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `opis` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `broj_kreveta` int(11) DEFAULT NULL,
  `cijena` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `soba`
--

INSERT INTO `soba` (`id`, `broj_sobe`, `opis`, `broj_kreveta`, `cijena`) VALUES
(2, '1', 'Jednokrevetna soba', 1, 50),
(3, '2', 'Jednokrevetna soba', 1, 50),
(4, '3', 'Dvokrevetna soba', 2, 100);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `prezime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `privilegije` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `ime`, `prezime`, `privilegije`) VALUES
(1, 'admin', 'asd', 'admin@gmail.com', 'admin', 'admin', 'admin'),
(2, 'korisnik1', 'asd', 'korisnik1@gmail.com', 'korisnik1', 'korisnik1', 'korisnik');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rezervacija_korisnik` (`id_korisnika`),
  ADD KEY `fk_rezervacija_soba` (`id_sobe`);

--
-- Indexes for table `soba`
--
ALTER TABLE `soba`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `rezervacija`
--
ALTER TABLE `rezervacija`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `soba`
--
ALTER TABLE `soba`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `fk_rezervacija_korisnik` FOREIGN KEY (`id_korisnika`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_rezervacija_soba` FOREIGN KEY (`id_sobe`) REFERENCES `soba` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
