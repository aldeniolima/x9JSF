-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: x9jsf
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `idAdministrador` bigint(20) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idAdministrador`),
  CONSTRAINT `FK_administrador_idAdministrador` FOREIGN KEY (`idAdministrador`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'secretaria');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `idAluno` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_nascimento` date DEFAULT NULL,
  `deficiencia` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `idResponsavel` bigint(20) DEFAULT NULL,
  `idTurma` int(11) DEFAULT NULL,
  `idRelatorioParental` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idAluno`),
  KEY `FK_aluno_idResponsavel` (`idResponsavel`),
  KEY `FK_aluno_idRelatorioParental` (`idRelatorioParental`),
  KEY `FK_aluno_idTurma` (`idTurma`),
  CONSTRAINT `FK_aluno_idRelatorioParental` FOREIGN KEY (`idRelatorioParental`) REFERENCES `relatorioParental` (`idRelatorioParental`),
  CONSTRAINT `FK_aluno_idResponsavel` FOREIGN KEY (`idResponsavel`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `FK_aluno_idTurma` FOREIGN KEY (`idTurma`) REFERENCES `turma` (`idTurma`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'2000-11-10','Não','20291019','Jose',3,1,1),(2,'1999-11-10','Sim','Y1234567','Joao',3,2,2);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `idEndereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(12) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `numeroEndereco` int(11) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEndereco`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'50.680-000','Recife',54,'teste','PE'),(2,'50.680-000','recife',2,'teste','PE'),(3,'50.456-902','Recife',100,'Rua a','PE'),(4,'50.717-220','Recife',1000,'Rua a','PE');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `idgrupo` bigint(20) NOT NULL,
  `nomeGrupo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idgrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `idProfessor` bigint(20) NOT NULL,
  PRIMARY KEY (`idProfessor`),
  CONSTRAINT `FK_professor_idProfessor` FOREIGN KEY (`idProfessor`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (2);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relatorioParental`
--

DROP TABLE IF EXISTS `relatorioParental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relatorioParental` (
  `idRelatorioParental` bigint(20) NOT NULL AUTO_INCREMENT,
  `criatividade` double DEFAULT NULL,
  `lideranca` double DEFAULT NULL,
  `motivacao` double DEFAULT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `participacaoEmSala` double DEFAULT NULL,
  `trabalhoEmEquipe` double DEFAULT NULL,
  PRIMARY KEY (`idRelatorioParental`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatorioParental`
--

LOCK TABLES `relatorioParental` WRITE;
/*!40000 ALTER TABLE `relatorioParental` DISABLE KEYS */;
INSERT INTO `relatorioParental` VALUES (1,10,9,8,'Sem OBS.',7,6),(2,6,7,8,'OBS registrada.',9,10);
/*!40000 ALTER TABLE `relatorioParental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsavel`
--

DROP TABLE IF EXISTS `responsavel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsavel` (
  `idResponsavel` bigint(20) NOT NULL,
  PRIMARY KEY (`idResponsavel`),
  CONSTRAINT `FK_responsavel_idResponsavel` FOREIGN KEY (`idResponsavel`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsavel`
--

LOCK TABLES `responsavel` WRITE;
/*!40000 ALTER TABLE `responsavel` DISABLE KEYS */;
INSERT INTO `responsavel` VALUES (3);
/*!40000 ALTER TABLE `responsavel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma` (
  `idTurma` int(11) NOT NULL AUTO_INCREMENT,
  `numeroSala` int(11) DEFAULT NULL,
  `qtdAluno` int(11) DEFAULT NULL,
  `serie` varchar(255) DEFAULT NULL,
  `turno` varchar(255) DEFAULT NULL,
  `idProfessor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idTurma`),
  KEY `FK_turma_idProfessor` (`idProfessor`),
  CONSTRAINT `FK_turma_idProfessor` FOREIGN KEY (`idProfessor`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (1,20,21,'Quinta','Manha',2),(2,10,30,'5','Manhã',2);
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `dtype` varchar(1) DEFAULT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(16) DEFAULT NULL,
  `idEndereco` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `FK_usuario_idEndereco_idx` (`idEndereco`),
  CONSTRAINT `FK_usuario_idEndereco` FOREIGN KEY (`idEndereco`) REFERENCES `endereco` (`idEndereco`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'A','096.328.124-09','1997-03-17','aldeniopl@hotmail.com','administrador','Aldenio','.Ae12345','(81)99873-1273',1),(2,'P','104.707.064-22','1996-02-14','adeildo@hotmail.com','professor','Adeildo','.Ae12345','(81)99873-1272',2),(3,'R','965.462.320-06','1970-11-10','maria@gmail.com','maria','Maria','tT1234.','(81)23232-3232',3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_grupo`
--

DROP TABLE IF EXISTS `usuario_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_grupo` (
  `idUsuario` bigint(20) NOT NULL,
  `idGrupo` bigint(20) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_usuario_grupo_2_idx` (`idGrupo`),
  CONSTRAINT `fk_usuario_grupo_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_grupo_2` FOREIGN KEY (`idGrupo`) REFERENCES `grupo` (`idgrupo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_grupo`
--

LOCK TABLES `usuario_grupo` WRITE;
/*!40000 ALTER TABLE `usuario_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_grupo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-11 11:10:02
