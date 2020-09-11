-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 19, 2018 at 01:59 PM
-- Server version: 5.7.21-0ubuntu0.16.04.1
-- PHP Version: 7.0.25-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cakeshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cake`
--

CREATE TABLE `cake` (
  `cakeno` int(11) NOT NULL,
  `cakedescription` text NOT NULL,
  `cakeprice` float NOT NULL,
  `stock` int(11) NOT NULL,
  `shopno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cakeorder`
--

CREATE TABLE `cakeorder` (
  `orderno` int(11) NOT NULL,
  `cakeno` int(11) NOT NULL,
  `customerno` int(11) NOT NULL,
  `orderdescription` int(11) NOT NULL,
  `orderdate` varchar(50) NOT NULL,
  `delivarydate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewno` int(11) NOT NULL,
  `orderno` int(11) NOT NULL,
  `reviewdate` varchar(50) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `shopno` int(11) NOT NULL,
  `shopname` varchar(255) NOT NULL,
  `shopcontact` varchar(15) NOT NULL,
  `shopaddress` varchar(255) NOT NULL,
  `userno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`shopno`, `shopname`, `shopcontact`, `shopaddress`, `userno`) VALUES
(1, 'CU CakeShop', '123456', '', 1),
(2, 'JustCake', '0123456', '', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userno` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userno`, `username`, `email`, `password`, `contact`, `address`) VALUES
(1, 'User1', 'user@gmail.com', '123456', '123456', 'Chittagong'),
(2, 'Sakifa', 'sakifa@gmail.com', '123', '123', 'CU'),
(3, 'Progga', 'progga@gmail.com', '123', '123', 'CU');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cake`
--
ALTER TABLE `cake`
  ADD PRIMARY KEY (`cakeno`);

--
-- Indexes for table `cakeorder`
--
ALTER TABLE `cakeorder`
  ADD PRIMARY KEY (`orderno`),
  ADD KEY `fk_cakeshop_customerno` (`customerno`),
  ADD KEY `fk_cakeshop_cakeno` (`cakeno`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewno`),
  ADD KEY `fk_review_orderno` (`orderno`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`shopno`),
  ADD UNIQUE KEY `userno` (`userno`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userno`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cake`
--
ALTER TABLE `cake`
  MODIFY `cakeno` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cakeorder`
--
ALTER TABLE `cakeorder`
  MODIFY `orderno` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewno` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `shopno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cake`
--
ALTER TABLE `cake`
  ADD CONSTRAINT `fk_cake_shopno` FOREIGN KEY (`cakeno`) REFERENCES `shop` (`shopno`);

--
-- Constraints for table `cakeorder`
--
ALTER TABLE `cakeorder`
  ADD CONSTRAINT `fk_cakeshop_cakeno` FOREIGN KEY (`cakeno`) REFERENCES `cake` (`cakeno`),
  ADD CONSTRAINT `fk_cakeshop_customerno` FOREIGN KEY (`customerno`) REFERENCES `user` (`userno`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_review_orderno` FOREIGN KEY (`orderno`) REFERENCES `cakeorder` (`orderno`);

--
-- Constraints for table `shop`
--
ALTER TABLE `shop`
  ADD CONSTRAINT `fk_shop_userno` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
