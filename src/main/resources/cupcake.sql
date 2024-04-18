CREATE DATABASE  IF NOT EXISTS `cupcake` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cupcake`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cupcake
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
-- Table structure for table `cupcake_bottom`
--

DROP TABLE IF EXISTS `cupcake_bottom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcake_bottom` (
  `cupcake_bottom_id` int NOT NULL AUTO_INCREMENT,
  `price` int NOT NULL,
  `flavor` varchar(45) NOT NULL,
  PRIMARY KEY (`cupcake_bottom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcake_bottom`
--

LOCK TABLES `cupcake_bottom` WRITE;
/*!40000 ALTER TABLE `cupcake_bottom` DISABLE KEYS */;
INSERT INTO `cupcake_bottom` VALUES (1,5,'Chocolate'),(2,5,'Vanilla'),(3,5,'Nutmeg'),(4,6,'Pistacio'),(5,7,'Almond');
/*!40000 ALTER TABLE `cupcake_bottom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcake_order`
--

DROP TABLE IF EXISTS `cupcake_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcake_order` (
  `cupcake_order_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `price` int NOT NULL,
  `cupcake_top_id` int NOT NULL,
  `cupcake_bottom_id` int NOT NULL,
  PRIMARY KEY (`cupcake_order_id`),
  KEY `fk_cupcake_order_orders1_idx` (`order_id`),
  KEY `fk_cupcake_order_cupcake_top1_idx` (`cupcake_top_id`),
  KEY `fk_cupcake_order_cupcake_bottom1_idx` (`cupcake_bottom_id`),
  CONSTRAINT `fk_cupcake_order_cupcake_bottom1` FOREIGN KEY (`cupcake_bottom_id`) REFERENCES `cupcake_bottom` (`cupcake_bottom_id`),
  CONSTRAINT `fk_cupcake_order_cupcake_top1` FOREIGN KEY (`cupcake_top_id`) REFERENCES `cupcake_top` (`cupcake_top_id`),
  CONSTRAINT `fk_cupcake_order_orders1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcake_order`
--

LOCK TABLES `cupcake_order` WRITE;
/*!40000 ALTER TABLE `cupcake_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `cupcake_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcake_top`
--

DROP TABLE IF EXISTS `cupcake_top`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcake_top` (
  `cupcake_top_id` int NOT NULL AUTO_INCREMENT,
  `price` int NOT NULL,
  `flavor` varchar(45) NOT NULL,
  PRIMARY KEY (`cupcake_top_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcake_top`
--

LOCK TABLES `cupcake_top` WRITE;
/*!40000 ALTER TABLE `cupcake_top` DISABLE KEYS */;
INSERT INTO `cupcake_top` VALUES (1,5,'Chocolate'),(2,5,'Blueberry'),(3,5,'Rasberry'),(4,6,'Crispy'),(5,6,'Strawberry'),(6,7,'Rum/Raisin'),(7,8,'Orange'),(8,8,'Lemon'),(9,9,'Blue cheese');
/*!40000 ALTER TABLE `cupcake_top` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `total_price` int NOT NULL,
  `order_creation_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(45) NOT NULL,
  `is_order_active` tinyint DEFAULT '0',
  `order_amount` int NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_user1_idx` (`username`),
  CONSTRAINT `fk_orders_user1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `saldo` int NOT NULL DEFAULT '0',
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','1234','admin',0,''),('amigo','amigo','user',0,''),('guest','1234','user',0,'ddf'),('user','1234','user',0,'');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-24 13:21:38
