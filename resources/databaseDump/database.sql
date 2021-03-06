/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.20-MariaDB : Database - nst1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nst1` /*!40100 DEFAULT CHARACTER SET utf16 COLLATE utf16_bin */;

USE `nst1`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  `adminPassword` varchar(100) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`adminEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `admin` */

insert  into `admin`(`adminEmail`,`adminPassword`) values 
('andrija.djerasevic@gmail.com','andrija'),
('pera.peric@gmail.com','pera'),
('pera.peric@gmail1.com','pera'),
('pera.peric@gmail10.com','1');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  `firstName` varchar(100) COLLATE utf16_bin NOT NULL,
  `lastName` varchar(100) COLLATE utf16_bin NOT NULL,
  `adminEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`employeeEmail`),
  KEY `adminEmail` (`adminEmail`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`adminEmail`) REFERENCES `admin` (`adminEmail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `employee` */

insert  into `employee`(`employeeEmail`,`firstName`,`lastName`,`adminEmail`) values 
('andrija.djerasevic@gmail.com','Andrija','Djerasevic','andrija.djerasevic@gmail.com'),
('pera.peric@gmail.com','Pera Updated','Peric1','andrija.djerasevic@gmail.com');

/*Table structure for table `participate` */

DROP TABLE IF EXISTS `participate`;

CREATE TABLE `participate` (
  `employeeEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  `projectEventId` varchar(100) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`employeeEmail`,`projectEventId`),
  KEY `projectId` (`projectEventId`),
  CONSTRAINT `participate_ibfk_1` FOREIGN KEY (`employeeEmail`) REFERENCES `employee` (`employeeEmail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `participate_ibfk_2` FOREIGN KEY (`projectEventId`) REFERENCES `projectevent` (`projectEventId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `participate` */

/*Table structure for table `projectevent` */

DROP TABLE IF EXISTS `projectevent`;

CREATE TABLE `projectevent` (
  `projectEventId` varchar(100) COLLATE utf16_bin NOT NULL,
  `projectEventName` varchar(100) COLLATE utf16_bin NOT NULL,
  `projectEventLocation` varchar(100) COLLATE utf16_bin NOT NULL,
  `projectEventDescription` varchar(1000) COLLATE utf16_bin DEFAULT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `adminEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`projectEventId`),
  KEY `adminEmail` (`adminEmail`),
  CONSTRAINT `projectevent_ibfk_1` FOREIGN KEY (`adminEmail`) REFERENCES `admin` (`adminEmail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `projectevent` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
