-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 27, 2021 at 12:55 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `skambuciu_centras`
--

-- --------------------------------------------------------

--
-- Table structure for table `anketa`
--

CREATE TABLE `anketa` (
  `id` int(10) UNSIGNED NOT NULL,
  `anketos_data` int(11) NOT NULL,
  `paslaugos_vertinimas` int(11) NOT NULL,
  `imones_vertinimas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `anketa`
--

INSERT INTO `anketa` (`id`, `anketos_data`, `paslaugos_vertinimas`, `imones_vertinimas`) VALUES
(1, 20210221, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `klientai`
--

CREATE TABLE `klientai` (
  `id` int(10) UNSIGNED NOT NULL,
  `vardas` varchar(255) NOT NULL,
  `pavarde` varchar(255) NOT NULL,
  `gimimo_data` int(11) NOT NULL,
  `telefono_numeris` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `klientai`
--

INSERT INTO `klientai` (`id`, `vardas`, `pavarde`, `gimimo_data`, `telefono_numeris`) VALUES
(1, 'Tadas', 'Razbadauskas', 19900728, 612121212);

-- --------------------------------------------------------

--
-- Table structure for table `klientai_apklausa`
--

CREATE TABLE `klientai_apklausa` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_kliento` int(10) UNSIGNED NOT NULL,
  `id_anketos` int(10) UNSIGNED NOT NULL,
  `kliento_vardas` varchar(255) NOT NULL,
  `kliento_pavarde` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anketa`
--
ALTER TABLE `anketa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `klientai`
--
ALTER TABLE `klientai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `klientai_apklausa`
--
ALTER TABLE `klientai_apklausa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_apklausos` (`id_anketos`),
  ADD KEY `id_kliento` (`id_kliento`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anketa`
--
ALTER TABLE `anketa`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `klientai`
--
ALTER TABLE `klientai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `klientai_apklausa`
--
ALTER TABLE `klientai_apklausa`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `klientai_apklausa`
--
ALTER TABLE `klientai_apklausa`
  ADD CONSTRAINT `klientai_apklausa_ibfk_1` FOREIGN KEY (`id_anketos`) REFERENCES `anketa` (`id`),
  ADD CONSTRAINT `klientai_apklausa_ibfk_2` FOREIGN KEY (`id_kliento`) REFERENCES `klientai` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
