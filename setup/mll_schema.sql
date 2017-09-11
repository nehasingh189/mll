-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: medialicencinglab.chrkvax4awjg.us-west-2.rds.amazonaws.com    Database: medialicencinglab
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Dumping data for table `AdminUser`
--

LOCK TABLES `AdminUser` WRITE;
/*!40000 ALTER TABLE `AdminUser` DISABLE KEYS */;
INSERT INTO `AdminUser` VALUES (1,'akhil','k','NEU','MS','M','Rock',22,'MS','CS'),
(7,'Dishant','Shah','College of Computer and Information Science','Graduate Student','Male','Christian & Gospel',24,' ',' '),
(43,'Dishant','Shah','College of Engineering','Graduate Student','Male','Latino',59,' ',' '),
(45,'ashish','kumar','College of Arts, Media and Design','Graduate Student','Male','Blues',12,' ',' '),
(46,'Ashish','Kumar','College of Engineering','Undergraduate Student','Male','Folk / Americana',34,' ',' '),
(54,'sam','sam','College of Arts, Media and Design','Graduate Student','Male','Blues',12,' ',' '),
(91,'Test','Test','NEU','PhD','Male','Metal',22,'CS',NULL);
/*!40000 ALTER TABLE `AdminUser` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Aruser`
--

LOCK TABLES `Aruser` WRITE;
/*!40000 ALTER TABLE `Aruser` DISABLE KEYS */;
INSERT INTO `Aruser` VALUES (1,'sai','mahanth','ccis','graduate','male','rock',26,'cs','cs'),(3,'sai','mahanth','ccis','graduate','male','rock',26,'cs','cs'),(4,'sai','mahanth','ccis','graduate','male','rock',26,'cs','cs'),(41,'Vishal','Kotak','College of Computer and Information Science','Graduate Student','Male','Children\'s Music',23,' ',' '),(42,'Dishant','Shah','College of Arts, Media and Design','Graduate Student','Male','Jazz',25,' ',' '),(64,'sai','mahanth','College of Computer and Information Science','Graduate Student','Male','Blues',33,' ',' '),(74,'Dishant','Shah','College of Computer and Information Science','Graduate Student','Male','Blues',24,' ',' '),(79,'Trevyn','Langsford','College of Computer and Information Science','Undergraduate Student','Male','Electronic',22,' ',' '),(80,'Adam','Beairsto','College of Engineering','Undergraduate Student','Male','Hip - Hop / Rap',23,' ',' '),(85,'will','guo','College of Computer and Information Science','Undergraduate Student','Male','Jazz',20,' ',' ');
/*!40000 ALTER TABLE `Aruser` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Musician`
--

LOCK TABLES `Musician` WRITE;
/*!40000 ALTER TABLE `Musician` DISABLE KEYS */;
INSERT INTO `Musician` VALUES (2,' ',NULL,NULL,NULL,NULL),(5,'mahanth',NULL,24,'4BB7CA2D4E3F40BDA52C829E0F09C693',3),(10,'shivani',NULL,25,'74B5E46B80FA44ACB8CF47DFFE90D254',3),(12,'kumar',NULL,26,'55364DBFF15F4CCB8D0DD312A0C6DD30',3),(35,'naomi',NULL,25,'D0AD2E860899487EA13728F695E7EFD6',3),(36,'naomi',NULL,25,'8CA77DBD76DC446CBF52A8223D48E465',3),(37,'naomi',NULL,25,'AA54F60479B14DCC9B3FF881D9C3BB71',3),(38,'naomi',NULL,25,'54BC3DDECE524106A40433885B4D9A93',3),(39,'naomi25',NULL,25,'777179017CF24EE68FA6D50381A92869',3),(40,'naomi26',NULL,0,'F2CE28B710B94942BCEBA222DB0440E4',3),(44,'dishant833',NULL,24,'04257FBBB1B44C2385759B200CFFFE0D',3),(65,'mahanth02',NULL,0,'7B153227C52A4FF4B4D5C88B33C4CA88',64),(70,'dishant077',NULL,0,'ED8ACD93A37C438786D9A394268DBF04',3),(72,'dishant566',NULL,0,'AC8B5BB60AED47CDB895C95CD5550144',3),(73,'shivanigowri',NULL,0,'B361B237474B46279AEABF09E11DFB02',3),(75,'shivani123',NULL,0,'6322301265144AD1BB19768566309769',64),(76,'johndoe',NULL,0,'9500EBF8B70A4938B4D07EF067B92480',3),(78,'isignisign',NULL,0,'failure',1),(82,'chenbor',NULL,0,'F805130930DB4D4C8FB31B5291D72019',1),(83,'rickoneal',NULL,0,'CD60DA2932844A4A83CC221E7480CC1F',1),(86,'eclendaniel',NULL,0,'failure',1),(87,'Trevyn Langsford Musician',NULL,0,'failure',79),(88,'4l3x',NULL,0,'failure',1),(89,'narayanan.a',NULL,0,'failure',1),(90,'testMusician@gmail.com',NULL,0,'null',1),(92,'krintos',NULL,0,'failure',85);
/*!40000 ALTER TABLE `Musician` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=latin1;
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
) ENGINE=InnoDB AUTO_INCREMENT=488 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Token`
--

LOCK TABLES `Token` WRITE;
/*!40000 ALTER TABLE `Token` DISABLE KEYS */;
INSERT INTO `Token` VALUES (1,'MLLTKN1','akhil0@ccs.neu.edu',1,'2016-10-27',1,'musician'),(11,'MLLTKN11','mahanths2@gmail.com',3,'2016-11-11',0,'musician'),(25,'MLLTKN25','shinde.d@husky.neu.edu',3,'2016-11-23',0,'user'),(26,'MLLTKN26','kotak.v@husky.neu.edu',3,'2016-11-23',0,'user'),(30,'MLLTKN30','sanjivkotak@gmail.com',3,'2016-11-23',0,'musician'),(32,'MLLTKN32','niketakotak@gmail.com',3,'2016-11-23',0,'musician'),(33,'MLLTKN33','rahulsharmait@gmail.com',3,'2016-11-23',0,'musician'),(35,'MLLTKN35','roopkumar@me.com',3,'2016-11-23',0,'musician'),(36,'MLLTKN36','ankur@gmail.com',3,'2016-11-24',0,'musician'),(38,'MLLTKN38','qwe@gmail.com',3,'2016-11-24',0,'musician'),(40,'MLLTKN40','shivanigowri@gmail.com',3,'2016-11-26',1,'musician'),(41,'MLLTKN41','joshi.nao@husky.neu.edu',3,'2016-11-28',1,'musician'),(42,'MLLTKN42','qwer@gmail.com',3,'2016-11-28',0,'musician'),(76,'MLLTKN76','jakepoulios@gmail.com',3,'2016-11-29',0,'musician'),(77,'MLLTKN77','griffin.tay@husky.neu.edu',3,'2016-11-29',0,'musician'),(78,'MLLTKN78','lind.je@husky.neu.edu',3,'2016-11-29',0,'musician'),(79,'MLLTKN79','kang.ju@husky.neu.edu',3,'2016-11-29',0,'musician'),(84,'MLLTKN84','sheth.dhv@husky.neu.edu',3,'2016-12-02',0,'musician'),(85,'MLLTKN85','m.weintraub@northeastern.edu',3,'2016-12-02',0,'user'),(87,'MLLTKN87','naomi2610@gmail.com',3,'2016-12-03',1,'musician'),(88,'MLLTKN88','medhavi1607@gmail.com',3,'2016-12-03',0,'musician'),(89,'MLLTKN89','northeastern@husky.neu.edu',3,'2016-12-04',0,'musician'),(90,'MLLTKN90','unity@gmail.com',3,'2016-12-07',0,'musician'),(91,'MLLTKN91','awq@gmail.com',3,'2016-12-07',0,'user'),(92,'MLLTKN92','vishalkt07@gmail.com',3,'2016-12-07',1,'user'),(97,'MLLTKN97','shoutingcanvases@gmail.com',3,'2016-12-08',1,'user'),(98,'MLLTKN98','vishalkotak@gmail.com',3,'2016-12-08',0,'user'),(99,'MLLTKN99','vishalsan@gmail.com',3,'2016-12-08',0,'musician'),(100,'MLLTKN100','musician@gmail.com',3,'2016-12-08',0,'user'),(111,'MLLTKN111','mahanth@zimbra.ccs.neu.edu',64,'2016-12-08',1,'musician'),(116,'MLLTKN116','tyu@gmail.com',64,'2016-12-08',0,'musician'),(117,'MLLTKN117','bnm@gmail.com',64,'2016-12-08',0,'musician'),(119,'MLLTKN119','rmayank@gmail.com',3,'2016-12-08',0,'musician'),(124,'MLLTKN124','ashish_09@gmail.com',3,'2016-12-08',0,'musician'),(125,'MLLTKN125','gowrishankar.s@husky.neu.edu',3,'2016-12-08',1,'musician'),(130,'MLLTKN130','shah.dis@husky.neu.edu',64,'2016-12-08',1,'user'),(131,'MLLTKN131','kumar.as@husky.neu.edu',74,'2016-12-08',0,'musician'),(132,'MLLTKN132','gshivani@ccs.neu.edu',64,'2016-12-08',1,'musician'),(133,'MLLTKN133','dshah@gmail.com',3,'2016-12-08',0,'musician'),(134,'MLLTKN134','raghuramyuvi@gmail.com',3,'2016-12-14',1,'musician'),(135,'MLLTKN135','guo.wi@husky.neu.edu',1,'2017-02-03',1,'musician'),(138,'MLLTKN138','langsford.t@husky.neu.edu',1,'2017-02-03',1,'user'),(139,'MLLTKN139','chen.bor@husky.neu.edu',1,'2017-02-03',1,'musician'),(140,'MLLTKN140','ajbeairsto@gmail.com',1,'2017-02-03',1,'user'),(141,'MLLTKN141','rickonealmusic@gmail.com',1,'2017-02-06',1,'musician'),(143,'MLLTKN143','clendaniel.e@husky.neu.edu',1,'2017-02-07',1,'musician'),(144,'MLLTKN144','d.herlihy@northeastern.edu',1,'2017-02-07',0,'musician'),(145,'MLLTKN145','oneal.f@husky.neu.edu',1,'2017-02-07',0,'musician'),(146,'MLLTKN146','275030039bh@gmail.com',1,'2017-02-07',1,'user'),(147,'MLLTKN147','tlangsford@gmail.com',79,'2017-02-13',1,'musician'),(148,'MLLTKN148','4l3x@ccs.neu.edu',1,'2017-02-17',1,'musician'),(149,'MLLTKN149','narayanan.a@husky.neu.edu',1,'2017-02-18',1,'musician'),(150,'MLLTKN150','testMusician@gmail.com',1,'2017-02-18',1,'musician'),(151,'MLLTKN151','testAdmin@gmail.com',1,'2017-02-18',1,'user'),(152,'MLLTKN152','william.wenzhou.guo@gmail.com',85,'2017-02-19',1,'musician');
/*!40000 ALTER TABLE `Token` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'akhil0','25d55ad283aa400af464c76d713c07ad','akhil0@ccs.neu.edu','user'),(2,'akhil00','25d55ad283aa400af464c76d713c07ad','akhil0@ccs.neu.edu',NULL),(3,'aruser','dbb2ad553b54536d308f7ade07cefbe5','mahanths2@gmail,com','ARUser'),(4,'aruser1','dbb2ad553b54536d308f7ade07cefbe5','mahanths2@gmail,com',NULL),(5,'mahanth','dbb2ad553b54536d308f7ade07cefbe5','mahanths2@gmail.com','musician'),(6,'medhavi','dbb2ad553b54536d308f7ade07cefbe5','mahansaria.m@husly.neu.edu','musician'),(7,'dishant8','7adfca2f2aba4cd301a02b9f33ca9037','dishant.nmims@gmail.com','admin'),(10,'shivani','6c3373379d877442b49bb17e065f2658','shivanigowri@gmail.com','musician'),(11,'naomi','25d55ad283aa400af464c76d713c07ad','joshi.nao@husky.neu.edu','musician'),(12,'kumar','25d55ad283aa400af464c76d713c07ad','kumar.as@husky.neu.edu','musician'),(32,'sai','dbb2ad553b54536d308f7ade07cefbe5','mahanth@zimbra.ccs.neu.edu','musician'),(35,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(36,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(37,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(38,'naomi','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(39,'naomi25','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(40,'naomi26','25d55ad283aa400af464c76d713c07ad','naomi2610@gmail.com','musician'),(41,'kotakvishal','25d55ad283aa400af464c76d713c07ad','vishalkt07@gmail.com','ARUser'),(42,'dishantshah','25d55ad283aa400af464c76d713c07ad','dishant.nmims@gmail.com','user'),(43,'dishant822','25d55ad283aa400af464c76d713c07ad','shah.dis@husky.neu.edu','admin'),(44,'dishant833','25d55ad283aa400af464c76d713c07ad','shah.dis@husky.neu.edu','musician'),(45,'ashish123','25d55ad283aa400af464c76d713c07ad','shoutingcanvases@gmail.com','admin'),(46,'Ashish_as','25d55ad283aa400af464c76d713c07ad','ashish9@ccs.neu.edu','admin'),(54,'sam_sam','25d55ad283aa400af464c76d713c07ad','asm@sam.com','admin'),(64,'mahanth01','dbb2ad553b54536d308f7ade07cefbe5','mahanth@zimbra.ccs.neu.edu','aruser'),(65,'mahanth02','dbb2ad553b54536d308f7ade07cefbe5','mahanth@zimbra.ccs.neu.edu','musician'),(70,'dishant077','25d55ad283aa400af464c76d713c07ad','dishant.nmims@gmail.com','musician'),(72,'dishant566','25d55ad283aa400af464c76d713c07ad','dishant.nmims@gmail.com','musician'),(73,'shivanigowri','6c3373379d877442b49bb17e065f2658','gowrishankar.s@husky.neu.edu','musician'),(74,'dishant1992','25d55ad283aa400af464c76d713c07ad','shah.dis@husky.neu.edu','aruser'),(75,'shivani123','16a729caaab66c55e6800ab111f32ed4','gshivani@ccs.neu.edu','musician'),(76,'johndoe','25d55ad283aa400af464c76d713c07ad','johndoe@gmail.com','musician'),(78,'isignisign','25f9e794323b453885f5181f1b624d0b','guo.wi@husky.neu.edu','musician'),(79,'Trevyn Langsford','9d04b961364c35bc39e33bf73a80a2ff','langsford.t@husky.neu.edu','aruser'),(80,'Adam Beairsto','833ffa7296275e8c2db6b52851159a82','beairsto.a@husky.neu.edu','aruser'),(82,'chenbor','e1d9c067e4d318fbd8fad0730d839f14','chen.bor@husky.neu.edu','musician'),(83,'rickoneal','922cee0da5480e09dd93d2678d269fa7','rickonealmusic@gmail.com','musician'),(85,'isignisi','25f9e794323b453885f5181f1b624d0b','275030039bh@gmail.com','aruser'),(86,'eclendaniel','d7cd1c4d7d746ed353fca49f28e9be3d','clendaniel.e@husky.neu.edu','musician'),(87,'Trevyn Langsford Musician','3581fe7432c0f14af009e86d372340c2','tlangsford@gmail.com','musician'),(88,'4l3x','25d55ad283aa400af464c76d713c07ad','4l3x@ccs.neu.edu','musician'),(89,'narayanan.a','25d55ad283aa400af464c76d713c07ad','narayanan.a@husky.neu.edu','musician'),(90,'testMusician1','25d55ad283aa400af464c76d713c07ad','testMusician@gmail.com','musician'),(91,'testAdmin','25d55ad283aa400af464c76d713c07ad','testAdmin@gmail.com','admin'),(92,'krintos','25f9e794323b453885f5181f1b624d0b','william.wenzhou.guo@gmail.com','musician');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

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


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-21 14:02:12
