-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rrhh
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id_empresa` int NOT NULL AUTO_INCREMENT,
  `nombre_empresa` varchar(30) NOT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'Arnedo.SL'),(2,'Toyota'),(3,'Amazon'),(4,'General Electric'),(5,'FedEx'),(6,'Procter&Gamble');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcajes`
--

DROP TABLE IF EXISTS `marcajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcajes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `tipo_marcaje` enum('E','S') NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `marcajes_usuarios_id_user_fk` (`id_usuario`),
  CONSTRAINT `marcajes_usuarios_id_user_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcajes`
--

LOCK TABLES `marcajes` WRITE;
/*!40000 ALTER TABLE `marcajes` DISABLE KEYS */;
/*!40000 ALTER TABLE `marcajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos`
--

DROP TABLE IF EXISTS `proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos` (
  `id_proyecto` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `id_empresa` int NOT NULL,
  PRIMARY KEY (`id_proyecto`),
  KEY `proyectos_empresa_id_empresa_fk` (`id_empresa`),
  CONSTRAINT `proyectos_empresa_id_empresa_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos`
--

LOCK TABLES `proyectos` WRITE;
/*!40000 ALTER TABLE `proyectos` DISABLE KEYS */;
INSERT INTO `proyectos` VALUES (1,'ArnLogistic',1),(2,'ArnPayment',1),(3,'WebToyotaService',2),(4,'ElectricLogistic',4),(5,'AmazonLogistic',3),(6,'AwsCloud',3),(7,'AwsPayments',3),(8,'ElectricLogistics',4),(9,'GambleDatabases',6),(10,'FedexUtils',5);
/*!40000 ALTER TABLE `proyectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `tipo_usuario` enum('A','U') NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','admin','99999999Z','Admin','admin','2023-02-22 10:15:57','2023-02-22 10:25:20','A'),(2,'usuario','usuario','88888888T','usuario','demo','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(3,'usuario1','usuario','95940962G','Raul','Calzada','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(4,'usuario2','usuario','98223734A','Marco','Aurelio','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(5,'usuario3','usuario','98223734A','Juan Carlos','de Borbón','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(6,'usuario4','usuario','42997956P','Felipe','Rodriguez','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(7,'usuario5','usuario','74776547M','Jose Carlos','Martinez','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(8,'usuario6','usuario','41936734Y','Marcos','Marquez','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(9,'usuario7','usuario','58207971P','Ainara','Campos','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(10,'usuario8','usuario','00564950R','Fabiola','Durán','2023-02-22 10:25:20','2023-02-22 10:25:20','U'),(11,'usuario9','usuario','04400996S','María','García','2023-02-22 10:25:20',NULL,'U'),(12,'usuario10','usuario','60604935G','Daniel','Campos','2023-02-22 10:25:20',NULL,'U'),(13,'usuario11','usuario','63724964Z','Rodrigo','Martin','2023-02-22 10:25:20',NULL,'U'),(14,'usuario12','usuario','04823289M','Ruben','Castro','2023-02-22 10:25:20',NULL,'U'),(15,'usuario13','usuario','05454384A','Manuel','Fando','2023-02-22 10:25:20',NULL,'U'),(16,'usuario14','usuario','69896852J','Natalia','Obrero','2023-02-22 10:25:20',NULL,'U'),(17,'usuario15','usuario','48882052Z','Carlota','Marquez','2023-02-22 10:25:20',NULL,'U'),(18,'usuario16','usuario','70416970D','Roberto','Maldonado','2023-02-22 10:25:20',NULL,'U'),(19,'usuario17','usuario','04913818Y','Roberto','Carrillo','2023-02-22 10:25:20',NULL,'U'),(20,'usuario18','usuario','87353141S','Juan Manuel','Vazquez','2023-02-22 10:25:20',NULL,'U');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_proyectos`
--

DROP TABLE IF EXISTS `usuarios_proyectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_proyectos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_proyecto` int NOT NULL,
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuarios_proyectos_proyectos_id_proyecto_fk` (`id_proyecto`),
  KEY `usuarios_proyectos___fk` (`id_user`),
  CONSTRAINT `usuarios_proyectos___fk` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id_user`),
  CONSTRAINT `usuarios_proyectos_proyectos_id_proyecto_fk` FOREIGN KEY (`id_proyecto`) REFERENCES `proyectos` (`id_proyecto`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_proyectos`
--

LOCK TABLES `usuarios_proyectos` WRITE;
/*!40000 ALTER TABLE `usuarios_proyectos` DISABLE KEYS */;
INSERT INTO `usuarios_proyectos` VALUES (1,2,1,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(2,3,1,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(3,4,1,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(4,5,2,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(5,6,3,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(6,7,2,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(7,8,2,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(8,9,3,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(9,10,3,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(10,11,4,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(11,12,4,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(12,13,4,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(13,14,5,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(14,15,5,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(15,16,5,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(16,17,6,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(17,18,6,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(18,19,6,'2023-02-22 10:25:20','2024-02-22 10:25:20'),(19,20,3,'2023-02-22 10:25:20','2024-02-22 10:25:20');
/*!40000 ALTER TABLE `usuarios_proyectos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-12 13:39:45
