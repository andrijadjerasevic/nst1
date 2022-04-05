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
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `admin` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `lastName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `adminId` int(11) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  KEY `adminId` (`adminId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `admin` (`adminId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `employee` */

/*Table structure for table `participate` */

DROP TABLE IF EXISTS `participate`;

CREATE TABLE `participate` (
  `employeeId` int(11) NOT NULL,
  `projectId` int(11) NOT NULL,
  PRIMARY KEY (`employeeId`,`projectId`),
  KEY `participateFk2` (`projectId`),
  CONSTRAINT `participateFk1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `participateFk2` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `participate` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `adminId` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectId`),
  KEY `adminId` (`adminId`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `admin` (`adminId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `project` */

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `taskName` varchar(100) COLLATE utf16_bin DEFAULT NULL,
  `description` varchar(1000) COLLATE utf16_bin DEFAULT NULL,
  `projectId` int(11) DEFAULT NULL,
  PRIMARY KEY (`taskId`),
  KEY `taskFk1` (`projectId`),
  CONSTRAINT `taskFk1` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

/*Data for the table `task` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
