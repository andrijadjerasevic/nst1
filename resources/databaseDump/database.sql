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
('pera.peric@gmail.com','pera1');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  `firstName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `lastName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `adminEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`employeeEmail`),
  KEY `adminEmail` (`adminEmail`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`adminEmail`) REFERENCES `admin` (`adminEmail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `employee` */

insert  into `employee`(`employeeEmail`,`firstName`,`lastName`,`adminEmail`) values 
('andrija.djerasevic@gmail.com','Andrija','Djerasevic','andrija.djerasevic@gmail.com');

/*Table structure for table `participate` */

DROP TABLE IF EXISTS `participate`;

CREATE TABLE `participate` (
  `employeeEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  `projectId` int(11) NOT NULL,
  PRIMARY KEY (`employeeEmail`,`projectId`),
  KEY `projectId` (`projectId`),
  CONSTRAINT `participate_ibfk_1` FOREIGN KEY (`employeeEmail`) REFERENCES `employee` (`employeeEmail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `participate_ibfk_2` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `participate` */

insert  into `participate`(`employeeEmail`,`projectId`) values 
('andrija.djerasevic@gmail.com',16);

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `projectLocation` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `projectDescription` varchar(1000) COLLATE utf16_bin DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `adminEmail` varchar(100) COLLATE utf16_bin NOT NULL,
  PRIMARY KEY (`projectId`),
  KEY `adminEmail` (`adminEmail`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`adminEmail`) REFERENCES `admin` (`adminEmail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `project` */

insert  into `project`(`projectId`,`projectName`,`projectLocation`,`projectDescription`,`startDate`,`endDate`,`adminEmail`) values 
(16,'Test','Belgrade','Testing','2022-04-23 18:37:53','2022-04-24 18:37:57','andrija.djerasevic@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
