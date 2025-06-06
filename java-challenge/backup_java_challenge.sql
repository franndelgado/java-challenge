-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: java_challenge
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accreditation`
--

DROP TABLE IF EXISTS `accreditation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accreditation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` bigint DEFAULT NULL,
  `point_of_sale_id` int NOT NULL,
  `point_of_sale_name` varchar(255) DEFAULT NULL,
  `reception_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accreditation`
--

LOCK TABLES `accreditation` WRITE;
/*!40000 ALTER TABLE `accreditation` DISABLE KEYS */;
INSERT INTO `accreditation` VALUES (1,504,2,'GBA_1','2025-04-29'),(2,300,3,'GBA_2','2025-05-05');
/*!40000 ALTER TABLE `accreditation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accreditations`
--

DROP TABLE IF EXISTS `accreditations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accreditations` (
  `id` int NOT NULL,
  `amount` bigint DEFAULT NULL,
  `point_of_sale_name` varchar(255) DEFAULT NULL,
  `reception_date` date DEFAULT NULL,
  `point_of_sale_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accreditations`
--

LOCK TABLES `accreditations` WRITE;
/*!40000 ALTER TABLE `accreditations` DISABLE KEYS */;
INSERT INTO `accreditations` VALUES (1,1000,'Santa Fe','2025-01-22',0),(7,89,'Salta','2025-01-22',0);
/*!40000 ALTER TABLE `accreditations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_of_sale`
--

DROP TABLE IF EXISTS `point_of_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_of_sale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_of_sale`
--

LOCK TABLES `point_of_sale` WRITE;
/*!40000 ALTER TABLE `point_of_sale` DISABLE KEYS */;
INSERT INTO `point_of_sale` VALUES (1,'CABA'),(2,'GBA_1'),(3,'GBA_2'),(4,'Santa Fe'),(5,'Cordoba'),(6,'Misiones'),(7,'Salta'),(8,'Chubut'),(9,'Santa Cruz'),(10,'Catamarca'),(11,'Formosa'),(13,'Mendoza'),(14,'CABA'),(15,'GBA_1'),(16,'GBA_2'),(17,'Santa Fe'),(18,'C├│rdoba'),(19,'Misiones'),(20,'Salta'),(21,'Chubut'),(22,'Santa Cruz'),(23,'Catamarca'),(24,'CABA'),(25,'GBA_1'),(26,'GBA_2'),(27,'Santa Fe'),(28,'C├│rdoba'),(29,'Misiones'),(30,'Salta'),(31,'Chubut'),(32,'Santa Cruz'),(33,'Catamarca'),(34,'CABA'),(35,'GBA_1'),(36,'GBA_2'),(37,'Santa Fe'),(38,'C├│rdoba'),(39,'Misiones'),(40,'Salta'),(41,'Chubut'),(42,'Santa Cruz'),(43,'Catamarca'),(44,'CABA'),(45,'GBA_1'),(46,'GBA_2'),(47,'Santa Fe'),(48,'C├│rdoba'),(49,'Misiones'),(50,'Salta'),(51,'Chubut'),(52,'Santa Cruz'),(53,'Catamarca'),(54,'CABA'),(55,'GBA_1'),(56,'GBA_2'),(57,'Santa Fe'),(58,'C├│rdoba'),(59,'Misiones'),(60,'Salta'),(61,'Chubut'),(62,'Santa Cruz'),(63,'Catamarca'),(64,'CABA'),(65,'GBA_1'),(66,'GBA_2'),(67,'Santa Fe'),(68,'C├│rdoba'),(69,'Misiones'),(70,'Salta'),(71,'Chubut'),(72,'Santa Cruz'),(73,'Catamarca'),(74,'CABA'),(75,'GBA_1'),(76,'GBA_2'),(77,'Santa Fe'),(78,'C├│rdoba'),(79,'Misiones'),(80,'Salta'),(81,'Chubut'),(82,'Santa Cruz'),(83,'Catamarca'),(84,'CABA'),(85,'GBA_1'),(86,'GBA_2'),(87,'Santa Fe'),(88,'C├│rdoba'),(89,'Misiones'),(90,'Salta'),(91,'Chubut'),(92,'Santa Cruz'),(93,'Catamarca'),(94,'CABA'),(95,'GBA_1'),(96,'GBA_2'),(97,'Santa Fe'),(98,'C├│rdoba'),(99,'Misiones'),(100,'Salta'),(101,'Chubut'),(102,'Santa Cruz'),(103,'Catamarca'),(104,'CABA'),(105,'GBA_1'),(106,'GBA_2'),(107,'Santa Fe'),(108,'C├│rdoba'),(109,'Misiones'),(110,'Salta'),(111,'Chubut'),(112,'Santa Cruz'),(113,'Catamarca'),(114,'CABA'),(115,'GBA_1'),(116,'GBA_2'),(117,'Santa Fe'),(118,'C├â┬│rdoba'),(119,'Misiones'),(120,'Salta'),(121,'Chubut'),(122,'Santa Cruz'),(123,'Catamarca'),(124,'CABA'),(125,'GBA_1'),(126,'GBA_2'),(127,'Santa Fe'),(128,'C├â┬│rdoba'),(129,'Misiones'),(130,'Salta'),(131,'Chubut'),(132,'Santa Cruz'),(133,'Catamarca'),(134,'CABA'),(135,'GBA_1'),(136,'GBA_2'),(137,'Santa Fe'),(138,'C├â┬│rdoba'),(139,'Misiones'),(140,'Salta'),(141,'Chubut'),(142,'Santa Cruz'),(143,'Catamarca'),(144,'CABA'),(145,'GBA_1'),(146,'GBA_2'),(147,'Santa Fe'),(148,'C├â┬│rdoba'),(149,'Misiones'),(150,'Salta'),(151,'Chubut'),(152,'Santa Cruz'),(153,'Catamarca'),(154,'CABA'),(155,'GBA_1'),(156,'GBA_2'),(157,'Santa Fe'),(158,'C├â┬│rdoba'),(159,'Misiones'),(160,'Salta'),(161,'Chubut'),(162,'Santa Cruz'),(163,'Catamarca'),(164,'Mendoza'),(165,'Chaco'),(166,'CABA'),(167,'GBA_1'),(168,'GBA_2'),(169,'Santa Fe'),(170,'C├│rdoba'),(171,'Misiones'),(172,'Salta'),(173,'Chubut'),(174,'Santa Cruz'),(175,'Catamarca'),(176,'CABA'),(177,'GBA_1'),(178,'GBA_2'),(179,'Santa Fe'),(180,'C├│rdoba'),(181,'Misiones'),(182,'Salta'),(183,'Chubut'),(184,'Santa Cruz'),(185,'Catamarca'),(186,'CABA'),(187,'GBA_1'),(188,'GBA_2'),(189,'Santa Fe'),(190,'C├│rdoba'),(191,'Misiones'),(192,'Salta'),(193,'Chubut'),(194,'Santa Cruz'),(195,'Catamarca'),(196,'CABA'),(197,'GBA_1'),(198,'GBA_2'),(199,'Santa Fe'),(200,'C├â┬│rdoba'),(201,'Misiones'),(202,'Salta'),(203,'Chubut'),(204,'Santa Cruz'),(205,'Catamarca');
/*!40000 ALTER TABLE `point_of_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_of_sale_cost`
--

DROP TABLE IF EXISTS `point_of_sale_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_of_sale_cost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cost` int DEFAULT NULL,
  `ida` int DEFAULT NULL,
  `idb` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_of_sale_cost`
--

LOCK TABLES `point_of_sale_cost` WRITE;
/*!40000 ALTER TABLE `point_of_sale_cost` DISABLE KEYS */;
INSERT INTO `point_of_sale_cost` VALUES (1,2,1,2),(2,3,1,3),(3,5,2,3),(4,10,2,4),(5,11,1,4),(6,5,4,5),(7,14,2,5),(8,32,6,7),(9,11,8,9),(10,5,10,7),(11,10,3,8),(12,30,5,8),(13,5,10,5),(14,6,4,6),(15,5,99,100),(20,2,1,2),(21,3,1,3),(22,5,2,3),(23,10,2,4),(24,11,1,4),(25,5,4,5),(26,14,2,5),(27,32,6,7),(28,11,8,9),(29,5,10,7),(30,10,3,8),(31,30,5,8),(32,5,10,5),(33,6,4,6),(34,2,1,2),(35,3,1,3),(36,5,2,3),(37,10,2,4),(38,11,1,4),(39,5,4,5),(40,14,2,5),(41,32,6,7),(42,11,8,9),(43,5,10,7),(44,10,3,8),(45,30,5,8),(46,5,10,5),(47,6,4,6),(48,2,1,2),(49,3,1,3),(50,5,2,3),(51,10,2,4),(52,11,1,4),(53,5,4,5),(54,14,2,5),(55,32,6,7),(56,11,8,9),(57,5,10,7),(58,10,3,8),(59,30,5,8),(60,5,10,5),(61,6,4,6),(62,2,1,2),(63,3,1,3),(64,5,2,3),(65,10,2,4),(66,11,1,4),(67,5,4,5),(68,14,2,5),(69,32,6,7),(70,11,8,9),(71,5,10,7),(72,10,3,8),(73,30,5,8),(74,5,10,5),(75,6,4,6),(76,2,1,2),(77,3,1,3),(78,5,2,3),(79,10,2,4),(80,11,1,4),(81,5,4,5),(82,14,2,5),(83,32,6,7),(84,11,8,9),(85,5,10,7),(86,10,3,8),(87,30,5,8),(88,5,10,5),(89,6,4,6),(90,2,1,2),(91,3,1,3),(92,5,2,3),(93,10,2,4),(94,11,1,4),(95,5,4,5),(96,14,2,5),(97,32,6,7),(98,11,8,9),(99,5,10,7),(100,10,3,8),(101,30,5,8),(102,5,10,5),(103,6,4,6),(104,2,1,2),(105,3,1,3),(106,5,2,3),(107,10,2,4),(108,11,1,4),(109,5,4,5),(110,14,2,5),(111,32,6,7),(112,11,8,9),(113,5,10,7),(114,10,3,8),(115,30,5,8),(116,5,10,5),(117,6,4,6),(118,2,1,2),(119,3,1,3),(120,5,2,3),(121,10,2,4),(122,11,1,4),(123,5,4,5),(124,14,2,5),(125,32,6,7),(126,11,8,9),(127,5,10,7),(128,10,3,8),(129,30,5,8),(130,5,10,5),(131,6,4,6),(132,2,1,2),(133,3,1,3),(134,5,2,3),(135,10,2,4),(136,11,1,4),(137,5,4,5),(138,14,2,5),(139,32,6,7),(140,11,8,9),(141,5,10,7),(142,10,3,8),(143,30,5,8),(144,5,10,5),(145,6,4,6),(146,2,1,2),(147,3,1,3),(148,5,2,3),(149,10,2,4),(150,11,1,4),(151,5,4,5),(152,14,2,5),(153,32,6,7),(154,11,8,9),(155,5,10,7),(156,10,3,8),(157,30,5,8),(158,5,10,5),(159,6,4,6),(160,2,1,2),(161,3,1,3),(162,5,2,3),(163,10,2,4),(164,11,1,4),(165,5,4,5),(166,14,2,5),(167,32,6,7),(168,11,8,9),(169,5,10,7),(170,10,3,8),(171,30,5,8),(172,5,10,5),(173,6,4,6),(174,2,1,2),(175,3,1,3),(176,5,2,3),(177,10,2,4),(178,11,1,4),(179,5,4,5),(180,14,2,5),(181,32,6,7),(182,11,8,9),(183,5,10,7),(184,10,3,8),(185,30,5,8),(186,5,10,5),(187,6,4,6),(188,2,1,2),(189,3,1,3),(190,5,2,3),(191,10,2,4),(192,11,1,4),(193,5,4,5),(194,14,2,5),(195,32,6,7),(196,11,8,9),(197,5,10,7),(198,10,3,8),(199,30,5,8),(200,5,10,5),(201,6,4,6),(202,2,1,2),(203,3,1,3),(204,5,2,3),(205,10,2,4),(206,11,1,4),(207,5,4,5),(208,14,2,5),(209,32,6,7),(210,11,8,9),(211,5,10,7),(212,10,3,8),(213,30,5,8),(214,5,10,5),(215,6,4,6),(216,2,1,2),(217,3,1,3),(218,5,2,3),(219,10,2,4),(220,11,1,4),(221,5,4,5),(222,14,2,5),(223,32,6,7),(224,11,8,9),(225,5,10,7),(226,10,3,8),(227,30,5,8),(228,5,10,5),(229,6,4,6),(230,2,1,2),(231,3,1,3),(232,5,2,3),(233,10,2,4),(234,11,1,4),(235,5,4,5),(236,14,2,5),(237,32,6,7),(238,11,8,9),(239,5,10,7),(240,10,3,8),(241,30,5,8),(242,5,10,5),(243,6,4,6),(244,2,1,2),(245,3,1,3),(246,5,2,3),(247,10,2,4),(248,11,1,4),(249,5,4,5),(250,14,2,5),(251,32,6,7),(252,11,8,9),(253,5,10,7),(254,10,3,8),(255,30,5,8),(256,5,10,5),(257,6,4,6),(258,2,1,2),(259,3,1,3),(260,5,2,3),(261,10,2,4),(262,11,1,4),(263,5,4,5),(264,14,2,5),(265,32,6,7),(266,11,8,9),(267,5,10,7),(268,10,3,8),(269,30,5,8),(270,5,10,5),(271,6,4,6),(272,2,1,2),(273,3,1,3),(274,5,2,3),(275,10,2,4),(276,11,1,4),(277,5,4,5),(278,14,2,5),(279,32,6,7),(280,11,8,9),(281,5,10,7),(282,10,3,8),(283,30,5,8),(284,5,10,5),(285,6,4,6);
/*!40000 ALTER TABLE `point_of_sale_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_of_sale_cost_seq`
--

DROP TABLE IF EXISTS `point_of_sale_cost_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_of_sale_cost_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_of_sale_cost_seq`
--

LOCK TABLES `point_of_sale_cost_seq` WRITE;
/*!40000 ALTER TABLE `point_of_sale_cost_seq` DISABLE KEYS */;
INSERT INTO `point_of_sale_cost_seq` VALUES (101);
/*!40000 ALTER TABLE `point_of_sale_cost_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(18) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'julieta','$2a$10$dSZCMWysQqwxJaSkW9yKz.QtVe2UN0uTeRB9daBoO6vOW1JPRS8IK',_binary ''),(2,'admin','$2a$10$HS7HPRLXef2QqDkXZrDQ4ea8RB4W7F4.MrPb/kjaPMYXhQRbOj6nq',_binary ''),(3,'tomasF','$2a$10$Ljt2nS9KA3phQuNqVkCbBuaNpIer2C9Lx4Way6wLx4W3aMKTzRduW',_binary ''),(8,'admin2','$2a$10$ebZqm66Kzzlu4Z13Rl21fu9Yp7Ot263IsTYTRII.IlqUeW.x2ZZgi',_binary ''),(9,'admin3','$2a$10$ubCXeBIHqH7hA6sNGcYZfeLpbFEp.NDDwAt3j29h/FC9IoT.V4aCm',_binary ''),(16,'admin4','$2a$10$hY3wDzFCM/d6DAtpg3gOZuID0hP/IFXvYls62Iy5wSmuAY9DnoaAS',_binary ''),(17,'Alfredo','$2a$10$U7nbjErhOaq00VZIdDNmPO5FjhefcMPtrREeHeTMAozPQaaaiH64m',_binary ''),(18,'Roberto','$2a$10$m7akL9nAwnUU3CuuW5q6..jiYTzVHp6qJEMOKAaBcBxJO4sTUPR9i',_binary ''),(19,'admin1','$2a$10$dDik7RhLzd71bYWhVGruley0LfowoFCOT1M1VI/VqEp9T6o3TUXAq',_binary ''),(20,'admin5','$2a$10$iqIgC/5mGwbEPcTyCH6EveFGZd4ds.nJxnlst7y8X7Gc5ihTPfeDW',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `UKq3r1u8cne2rw2hkr899xuh7vj` (`user_id`,`role_id`),
  KEY `FK_roles_idx` (`role_id`),
  CONSTRAINT `FK_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,1),(8,1),(18,1),(20,1),(1,2),(2,2),(3,2),(8,2),(9,2),(16,2),(17,2),(18,2),(19,2),(20,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14  9:59:30
