CREATE DATABASE  IF NOT EXISTS `medialicensinglab` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `medialicensinglab`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 35.163.135.77    Database: medialicensinglab
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AdminUser`
--

DROP TABLE IF EXISTS `AdminUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AdminUser` (
  `id` int(11) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `college` varchar(200) DEFAULT NULL,
  `level` varchar(200) DEFAULT NULL,
  `gender` varchar(200) DEFAULT NULL,
  `preference` varchar(200) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `major` varchar(200) DEFAULT NULL,
  `minor` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `AdminUser_ibfk_1` FOREIGN KEY (`id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Aruser`
--

DROP TABLE IF EXISTS `Aruser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Aruser` (
  `id` int(11) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `college` varchar(200) DEFAULT NULL,
  `level` varchar(200) DEFAULT NULL,
  `gender` varchar(200) DEFAULT NULL,
  `preference` varchar(200) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `major` varchar(200) DEFAULT NULL,
  `minor` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `Aruser_ibfk_1` FOREIGN KEY (`id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Band`
--

DROP TABLE IF EXISTS `Band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Band` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `musician_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `musician_id` (`musician_id`),
  CONSTRAINT `Band_ibfk_1` FOREIGN KEY (`musician_id`) REFERENCES `Musician` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Musician`
--

DROP TABLE IF EXISTS `Musician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Musician` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `gender` varchar(200) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `folder_id` varchar(200) DEFAULT NULL,
  `added_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `added_by` (`added_by`),
  CONSTRAINT `Musician_ibfk_1` FOREIGN KEY (`id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Musician_ibfk_2` FOREIGN KEY (`added_by`) REFERENCES `Aruser` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Owner`
--

DROP TABLE IF EXISTS `Owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `song_id` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `division_of_ownership` varchar(50) DEFAULT NULL,
  `owner_type` enum('WRITER','RECORDING') NOT NULL,
  `primary_phone_no` varchar(15) NOT NULL,
  `secondary_phone_no` varchar(15) DEFAULT NULL,
  `primary_email_id` varchar(50) NOT NULL,
  `secondary_email_id` varchar(50) DEFAULT NULL,
  `owner_percent` int(11) DEFAULT NULL,
  `contribution` varchar(200) DEFAULT NULL,
  `band_id` int(11) DEFAULT NULL,
  `role` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `band_id` (`band_id`),
  CONSTRAINT `Owner_ibfk_1` FOREIGN KEY (`band_id`) REFERENCES `Band` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Playlist`
--

DROP TABLE IF EXISTS `Playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Playlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playlist_id` int(11) NOT NULL,
  `song_id` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniquePlay` (`playlist_id`,`song_id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PlaylistReference`
--

DROP TABLE IF EXISTS `PlaylistReference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PlaylistReference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `playlist_name` varchar(200) NOT NULL,
  `is_shared` tinyint(1) NOT NULL,
  `creation_date` date NOT NULL,
  `user_name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PlaylistReference_User_fk` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=500 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Song`
--

DROP TABLE IF EXISTS `Song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Song` (
  `asset_id` varchar(200) NOT NULL,
  `musician_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`asset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Token`
--

DROP TABLE IF EXISTS `Token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(20) NOT NULL,
  `email_id` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `issue_date` date NOT NULL,
  `is_used` tinyint(1) NOT NULL,
  `inviteType` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `Token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email_id` varchar(100) NOT NULL,
  `usertype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `asset_id` varchar(200) NOT NULL DEFAULT '',
  `asset_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`asset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-19 14:43:55
LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'akhil0','25d55ad283aa400af464c76d713c07ad','akhil0@ccs.neu.edu','user'),(2,'akhil00','25d55ad283aa400af464c76d713c07ad','akhil0@ccs.neu.edu',NULL),(3,'aruser','dbb2ad553b54536d308f7ade07cefbe5','mahanths2@gmail,com','ARUser'),(4,'aruser1','dbb2ad553b54536d308f7ade07cefbe5','mahanths2@gmail,com',NULL),(5,'mahanth','dbb2ad553b54536d308f7ade07cefbe5','mahanths2@gmail.com','musician'),(6,'medhavi','dbb2ad553b54536d308f7ade07cefbe5','mahansaria.m@husly.neu.edu','musician'),(7,'dishant8','7adfca2f2aba4cd301a02b9f33ca9037','dishant.nmims@gmail.com','admin'),(10,'shivani','6c3373379d877442b49bb17e065f2658','shivanigowri@gmail.com','musician'),(11,'naomi','25d55ad283aa400af464c76d713c07ad','joshi.nao@husky.neu.edu','musician'),(12,'kumar','25d55ad283aa400af464c76d713c07ad','kumar.as@husky.neu.edu','musician'),(32,'sai','dbb2ad553b54536d308f7ade07cefbe5','mahanth@zimbra.ccs.neu.edu','musician'),(35,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(36,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(37,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(38,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(39,'naomi25','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(40,'naomi26','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(41,'kotakvishal','25d55ad283aa400af464c76d713c07ad','vishalkt07@gmail.com','ARUser'),(42,'dishantshah','25d55ad283aa400af464c76d713c07ad','dishant.nmims@gmail.com','user'),(43,'dishant822','25d55ad283aa400af464c76d713c07ad','shah.dis@husky.neu.edu','admin'),(44,'dishant833','25d55ad283aa400af464c76d713c07ad','shah.dis@husky.neu.edu','musician'),(45,'ashish123','25d55ad283aa400af464c76d713c07ad','shoutingcanvases@gmail.com','admin'),(46,'Ashish_as','25d55ad283aa400af464c76d713c07ad','ashish9@ccs.neu.edu','admin'),(54,'sam_sam','25d55ad283aa400af464c76d713c07ad','asm@sam.com','admin'),(64,'mahanth01','dbb2ad553b54536d308f7ade07cefbe5','mahanth@zimbra.ccs.neu.edu','aruser'),(65,'mahanth02','dbb2ad553b54536d308f7ade07cefbe5','mahanth@zimbra.ccs.neu.edu','musician'),(70,'dishant077','25d55ad283aa400af464c76d713c07ad','dishant.nmims@gmail.com','musician'),(72,'dishant566','25d55ad283aa400af464c76d713c07ad','dishant.nmims@gmail.com','musician'),(73,'shivanigowri','6c3373379d877442b49bb17e065f2658','gowrishankar.s@husky.neu.edu','musician'),(74,'dishant1992','25d55ad283aa400af464c76d713c07ad','shah.dis@husky.neu.edu','aruser'),(75,'shivani123','16a729caaab66c55e6800ab111f32ed4','gshivani@ccs.neu.edu','musician'),(76,'johndoe','25d55ad283aa400af464c76d713c07ad','johndoe@gmail.com','musician'),(78,'isignisign','25f9e794323b453885f5181f1b624d0b','guo.wi@husky.neu.edu','musician'),(79,'Trevyn Langsford','9d04b961364c35bc39e33bf73a80a2ff','langsford.t@husky.neu.edu','aruser'),(80,'Adam Beairsto','833ffa7296275e8c2db6b52851159a82','beairsto.a@husky.neu.edu','aruser'),(82,'chenbor','e1d9c067e4d318fbd8fad0730d839f14','chen.bor@husky.neu.edu','musician'),(83,'rickoneal','922cee0da5480e09dd93d2678d269fa7','rickonealmusic@gmail.com','musician'),(85,'isignisi','25f9e794323b453885f5181f1b624d0b','275030039bh@gmail.com','aruser'),(86,'eclendaniel','d7cd1c4d7d746ed353fca49f28e9be3d','clendaniel.e@husky.neu.edu','musician'),(87,'Trevyn Langsford Musician','3581fe7432c0f14af009e86d372340c2','tlangsford@gmail.com','musician'),(90,'testMusician1','25d55ad283aa400af464c76d713c07ad','testMusician@gmail.com','musician'),(91,'testAdmin','25d55ad283aa400af464c76d713c07ad','testAdmin@gmail.com','admin'),(92,'krintos','25f9e794323b453885f5181f1b624d0b','william.wenzhou.guo@gmail.com','musician'),(98,'narayanan.a@husky.neu.edu','25d55ad283aa400af464c76d713c07ad','narayanan.a@husky.neu.edu','musician');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `AdminUser` WRITE;
/*!40000 ALTER TABLE `AdminUser` DISABLE KEYS */;
INSERT INTO `AdminUser` VALUES (1,'akhil','k','NEU','MS','M','Rock',22,'MS','CS'),(7,'Dishant','Shah','College of Computer and Information Science','Graduate Student','Male','Christian & Gospel',24,' ',' '),(43,'Dishant','Shah','College of Engineering','Graduate Student','Male','Latino',59,' ',' '),(45,'ashish','kumar','College of Arts, Media and Design','Graduate Student','Male','Blues',12,' ',' '),(46,'Ashish','Kumar','College of Engineering','Undergraduate Student','Male','Folk / Americana',34,' ',' '),(54,'sam','sam','College of Arts, Media and Design','Graduate Student','Male','Blues',12,' ',' '),(91,'Test','Test','NEU','PhD','Male','Metal',22,'CS',NULL);
/*!40000 ALTER TABLE `AdminUser` ENABLE KEYS */;
UNLOCK TABLES;
