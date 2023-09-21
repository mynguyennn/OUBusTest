-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: oubus
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `taiKhoan` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `matKhau` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vi_0900_as_cs DEFAULT NULL,
  `maQuyen` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','123',1),(2,'staff','123',2),(3,'staff1','123',2),(4,'staff2','123',2),(5,'staff3','123',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuyendi`
--

DROP TABLE IF EXISTS `chuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuyendi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `giaVe` int DEFAULT NULL,
  `ngayKhoiHanh` date DEFAULT NULL,
  `gioKhoiHanh` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diemKhoiHanh` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `diemKetThuc` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `soGheTrong` int DEFAULT '20',
  `soGheDat` int DEFAULT '0',
  `trangThai` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vi_0900_ai_ci DEFAULT 'Chua khoi hanh',
  `maXe` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chuyendi_xekhach_idx` (`maXe`),
  CONSTRAINT `fk_chuyendi_xekhach` FOREIGN KEY (`maXe`) REFERENCES `xekhach` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuyendi`
--

LOCK TABLES `chuyendi` WRITE;
/*!40000 ALTER TABLE `chuyendi` DISABLE KEYS */;
INSERT INTO `chuyendi` VALUES (13,123123,'2023-04-19','19:00:00','AA','BBB',13,7,'Đã khởi hành',1),(16,120000,'2023-03-30','22:40:00','A','B',18,2,'Đã khởi hành',1),(22,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(23,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(24,130000,'2023-04-03','01:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(25,145000,'2023-04-03','23:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',2),(27,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(28,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(29,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(30,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(32,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(33,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(34,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(35,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(36,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(37,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(38,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(39,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(40,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(41,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(42,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(43,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(44,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(45,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(46,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',21,-1,'Đã khởi hành',1),(47,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(48,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(49,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(50,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(51,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(54,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',22,-2,'Đã khởi hành',1),(55,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(56,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(57,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(58,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(59,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(60,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(61,100000,'2023-04-11','17:00:00','Hue','Sai Gon',20,0,'Đã khởi hành',1),(62,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(63,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(64,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(65,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(66,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(67,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(68,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(69,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(70,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(71,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(72,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(73,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(74,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(75,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(76,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(77,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(78,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(79,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(80,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(81,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(82,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(83,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(84,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(85,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(86,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(87,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(88,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(89,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(90,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(91,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(92,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(93,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(94,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(95,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(96,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(97,130000,'2023-04-03','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(98,145000,'2023-04-03','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(99,123000,'2023-04-03','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(100,130000,'2023-04-04','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(101,145000,'2023-04-04','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(102,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(103,130000,'2023-04-04','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(104,145000,'2023-04-04','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(105,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(106,130000,'2023-04-04','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(107,145000,'2023-04-04','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(108,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(109,130000,'2023-04-04','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(110,145000,'2023-04-04','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(111,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(112,130000,'2023-04-04','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(113,145000,'2023-04-04','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(114,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(115,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(116,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(117,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(118,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(119,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(120,123000,'2023-04-04','12:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(122,123000,'2023-04-05','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(123,123000,'2023-04-05','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(124,130000,'2023-04-05','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(125,145000,'2023-04-05','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(126,123000,'2023-04-05','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(127,130000,'2023-04-05','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(128,145000,'2023-04-05','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(129,145000,'2023-04-05','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(133,290000,'2023-04-06','17:00:00','TP HCM','Hue',19,1,'Đã khởi hành',2),(134,130000,'2023-04-08','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(135,145000,'2023-04-08','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(136,123000,'2023-04-08','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(138,12345678,'2023-04-09','06:00:00','Ha Giang','Ha Tinh',20,0,'Đã khởi hành',1),(139,145000,'2023-04-09','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(140,23456,'2023-04-11','15:00:00','Hau Giang','TPHCM',20,0,'Đã khởi hành',2),(141,23456,'2023-04-11','15:30:00','Hau Giang','TPHCM',20,0,'Đã khởi hành',1),(142,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(143,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(144,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(145,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(146,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(147,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(148,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(149,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(150,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(151,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(152,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(153,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(154,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(155,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(156,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(157,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(158,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(159,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(160,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(161,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(162,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(163,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(164,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(165,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(166,130000,'2023-04-11','17:00:00','Ben Tre','Tien Giang',20,0,'Đã khởi hành',2),(167,145000,'2023-04-11','12:30:00','Ha Tinh','Hue',20,0,'Đã khởi hành',1),(168,123000,'2023-04-11','17:00:00','An Giang','Tien Giang',20,0,'Đã khởi hành',1),(169,123,'2023-04-13','06:06:00','Tay Ninh','Hue',20,0,'Đã khởi hành',2),(170,14555,'2023-04-13','20:02:00','Gia Lai','Nha Trang',20,0,'Đã khởi hành',1),(171,100000,'2023-04-15','18:00:00','Tiền Giang ','TPHCM',20,0,'Đã khởi hành',1),(172,100000,'2023-04-16','05:32:00','Da Lat','Tien Giang',20,0,'Đã khởi hành',1),(173,12345,'2023-04-16','01:03:00','Hue','TPHCM',20,0,'Đã khởi hành',1),(174,100000,'2023-04-16','01:01:00','TPHCM','Hue',20,0,'Đã khởi hành',1),(175,123456,'2023-04-17','12:01:00','th','tr',20,0,'Đã khởi hành',2),(176,123355,'2023-04-17','12:00:00','DN','HCM',20,0,'Đã khởi hành',1),(177,123456,'2023-04-17','12:00:00','GL','HCM',20,0,'Đã khởi hành',1),(178,123456,'2023-04-17','12:00:00','AC','TH',20,0,'Đã khởi hành',2),(179,125000,'2023-04-18','17:00:00','GL','DN',17,3,'Đã khởi hành',2),(180,100000,'2023-04-20','22:00:00','TP','HT',17,3,'Chua khoi hanh',2),(182,150000,'2023-04-20','12:20:00','TPHCM','Hue',20,0,'Chua khoi hanh',1);
/*!40000 ALTER TABLE `chuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doanhthuchuyendi`
--

DROP TABLE IF EXISTS `doanhthuchuyendi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doanhthuchuyendi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doanhthu` int DEFAULT NULL,
  `soVeDat` int DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doanhthuchuyendi`
--

LOCK TABLES `doanhthuchuyendi` WRITE;
/*!40000 ALTER TABLE `doanhthuchuyendi` DISABLE KEYS */;
INSERT INTO `doanhthuchuyendi` VALUES (1,120000,1,'2020-02-02'),(2,200000,1,'2023-03-27');
/*!40000 ALTER TABLE `doanhthuchuyendi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tenNhanVien` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `maLoaiNhanVien` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `soDienThoai` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cMND` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `queQuan` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `maAccount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `maAccount_UNIQUE` (`maAccount`),
  CONSTRAINT `fk_account_nhanvien` FOREIGN KEY (`maAccount`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Lê Văn Lâm','staff','2000-01-01','0335555555','123456789123','TPHCM',2),(2,'Thái Tấn Phát','staff','1999-02-02','0720397434','123456789123','An Giang',3),(7,'Võ Bùi Minh Hoàng','Tổng giám đốc','2023-04-16','0399987202','056202010094','056202010094',5);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenKhachHang` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngayDat` date DEFAULT NULL,
  `sdt` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `viTriGhe` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangThai` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `maChuyenDi` int DEFAULT NULL,
  `maNhanVien` int DEFAULT NULL,
  `maDoanhThu` int DEFAULT NULL,
  `diemDon` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vexe_chuyendi_idx` (`maChuyenDi`),
  KEY `fk_doanhthu_vexe_idx` (`maDoanhThu`),
  KEY `fk_vexe_account_idx` (`maNhanVien`),
  CONSTRAINT `fk_doanhthu_vexe` FOREIGN KEY (`maDoanhThu`) REFERENCES `doanhthuchuyendi` (`id`),
  CONSTRAINT `fk_vexe_account` FOREIGN KEY (`maNhanVien`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_vexe_chuyendi` FOREIGN KEY (`maChuyenDi`) REFERENCES `chuyendi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
INSERT INTO `vexe` VALUES ('12f0c751-ac21-4f90-bc29-31ebf6373248','Hoang','2023-03-30','1111111111','B02','Đã xuất',16,2,1,NULL),('3220279b-3c3d-4f52-9192-8f2cebf89995','V','2023-04-18','0987564321','B01','Đã xuất',179,2,1,'xe'),('6180bad4-883d-4582-a5e6-8fab3fb748f9','Phat','2023-03-30','1111111111','B01','Đã xuất',16,2,1,NULL),('6f7d2900-4128-4fd3-8ea3-a376dd03e64c','B','2023-04-18','0987654321','A04','Đã đặt',13,2,1,'ze'),('8551c1ef-ca61-4039-9e8c-68622b25a92e','b','2023-04-18','0987654311','B02','Đã xuất',179,2,1,'xe'),('9a0d66cc-3477-4460-b51c-6c6c8410f3c7','thuyen','2023-04-18','0962539434','B03','Đã xuất',13,2,1,'Sóc zTrăng'),('9caf3e46-0c7b-4bea-8fe2-2c6c98a89de2','B','2023-04-18','0987654321','A03','Đã xuất',13,2,1,'xe'),('ac086e7d-695e-40e1-a82d-94905758b348','Phuong','2023-04-19','0987654321','A02','Đã đặt',180,2,1,'xe'),('dasdjadjahlkafjlkajfkweu','vi','2023-04-06','8473274246','B01','Đã xuất',48,2,1,NULL),('dfba82d9-1f90-4820-8e9e-b558a4a9807d','ABC','2023-04-18','0987654321','B01','Đã đặt',13,2,1,'xe'),('dfgdffghfbghfhfbn43546','yen','2023-04-06','8473274246','A01','Đã đặt',45,2,1,NULL),('e02d7aa7-6b22-4fbf-91ce-a11b6b969e60','My','2023-04-19','0987654321','A03','Đã đặt',180,2,1,'q1'),('e97ed339-3c9a-444c-aff8-263fedc0fecf','Thanh','2023-04-06','0987654321','A01','Đã đặt',133,2,1,'xe'),('eb9fb5a3-695a-45b9-a7de-41739a940d3b','ABX','2023-04-18','0987654321','A02','Đã xuất',179,2,1,'xe'),('f0f7c863-a789-4a6b-a908-7c372bca454c','Vi ','2023-04-19','0987654432','A06','Đã đặt',180,2,1,'cho lon');
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xekhach`
--

DROP TABLE IF EXISTS `xekhach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xekhach` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bienSoXe` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soGhe` int DEFAULT NULL,
  `maNhanVien` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `maNhanVien` FOREIGN KEY (`id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xekhach`
--

LOCK TABLES `xekhach` WRITE;
/*!40000 ALTER TABLE `xekhach` DISABLE KEYS */;
INSERT INTO `xekhach` VALUES (1,'55-A23',20,1),(2,'59-B12',20,2);
/*!40000 ALTER TABLE `xekhach` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-19 19:33:52
