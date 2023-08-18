-- MySQL dump 10.13  Distrib 8.0.34, for Linux (x86_64)
--
-- Host: localhost    Database: scrooge
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.20.04.1

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `img_address` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6l9vkfd5ixw8o8kph5rj1k7gu` (`member_id`),
  CONSTRAINT `FK6l9vkfd5ixw8o8kph5rj1k7gu` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (5,'쿠키 더 시키려다가\r\n리뷰이벤트로 쿠키 하나 더 받았습니다 ㅎ','2023-08-17 11:39:36','https://storage.googleapis.com/scroogestorage/daa5e4d0-4527-4085-ab8c-e5253d11739d',4),(6,'공차 통신사 할인 받아서\r\n10% 할인된 가격으로 먹었습니다.\r\n다른 분들도 참고해주세요 ','2023-08-17 11:44:44','https://storage.googleapis.com/scroogestorage/9e054101-57c0-4d42-b625-cca233fe3f1f',13),(7,'돈 아끼려고 바이알 구걸해서 마셨습니다...','2023-08-17 12:28:41','https://storage.googleapis.com/scroogestorage/30e5c573-5609-428d-b9d8-9c5cbe59c6d7',2),(8,'싸피 기프티콘으로 커피 사 먹었다!\n그런데.. 카페라떼 먹어서 추가 결제함ㅋ','2023-08-17 12:48:44','https://storage.googleapis.com/scroogestorage/093285ef-b5e2-4a7c-b0f8-3388c487e8e3',5),(9,'싸피에서 바나나 먹어서 과일 살 돈 아꼈어요^^','2023-08-17 13:45:09','https://storage.googleapis.com/scroogestorage/db3c30b1-5b38-4a7d-aa76-9657e89bdb2f',1),(11,'식비 아끼려고 싸피 들어왔어요~ 삼성전기 밥 JMT !!! ','2023-08-17 14:56:52','https://storage.googleapis.com/scroogestorage/ce8b6401-b9bc-4e31-8982-a6afefcde1c5',16),(12,'배달비가 너무 비싸서\r\n그냥 포장해왔어요 ㅎㅎ \r\n더워서 좀 힘들었지만 나름 뿌듯 ^^','2023-08-17 15:02:27','https://storage.googleapis.com/scroogestorage/cc826d65-75f2-410c-b61c-89821f392705',13),(13,'오늘은 자전거를 타고 싸피에 갔다 왔어요...\r\n날이 덥지 않아서 참 다행이었답니다?','2023-08-17 15:21:03','https://storage.googleapis.com/scroogestorage/d6340284-5767-479c-a834-0305667f634f',13),(14,'텀블러 쓰면 할인 해주는 카페가 많다는 것 알고 계신지 ~~~\r\n스크루지 분들도 텀블러 쓰세요 ㅎㅎ','2023-08-17 15:50:31','https://storage.googleapis.com/scroogestorage/826f574c-f987-4207-9d07-704359921633',14),(15,'세트 먹고싶었지만 단품으로 참았습니다 ㅜㅜ','2023-08-18 00:26:25','https://storage.googleapis.com/scroogestorage/7d8efe93-9c86-449c-9664-d932c69b080a',2);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_bad`
--

DROP TABLE IF EXISTS `article_bad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_bad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtm6gu2dyna6ltf817e95c66dv` (`article_id`),
  KEY `FKkd155wlqvf3xavp80bm3s1mq6` (`member_id`),
  CONSTRAINT `FKkd155wlqvf3xavp80bm3s1mq6` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKtm6gu2dyna6ltf817e95c66dv` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_bad`
--

LOCK TABLES `article_bad` WRITE;
/*!40000 ALTER TABLE `article_bad` DISABLE KEYS */;
INSERT INTO `article_bad` VALUES (2,8,6),(3,8,2),(4,7,5),(8,8,15),(9,8,13),(12,9,5),(13,6,5);
/*!40000 ALTER TABLE `article_bad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_comment`
--

DROP TABLE IF EXISTS `article_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `article_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKghmocqkgqs5tkmucf5putw64t` (`article_id`),
  KEY `FKk9vyararhec6o9v70qg1pjmo3` (`member_id`),
  CONSTRAINT `FKghmocqkgqs5tkmucf5putw64t` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FKk9vyararhec6o9v70qg1pjmo3` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comment`
--

LOCK TABLES `article_comment` WRITE;
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
INSERT INTO `article_comment` VALUES (3,'우왓! 혹시 가게 정보 공유 가능한가요?',5,6),(4,'집에 가져가서 우유 타마셨어야죠',8,2),(5,'아무리 그래도 구걸은 좀...',7,5),(6,'nono님 예콤달콤입니다 ㅎㅎ',5,13),(7,'그러게요 .. 구걸은 좀 ... 스크루지도 자존심이 있습니다!',7,13),(8,'샷만 달라고 해서 우유를 타먹는게 좋은 방법이긴 하네요 ',8,13),(9,'그래도 공차는 비싸요',6,5),(10,'최고입니다...',11,13),(11,'기프티콘을 썼는데,, 반응이 안 좋네요 ㅠ',8,5);
/*!40000 ALTER TABLE `article_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_good`
--

DROP TABLE IF EXISTS `article_good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_good` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtc2q2cxde1qm5i1nlqastxh66` (`article_id`),
  KEY `FK1qvrq8r06dcrc5icbmf2gjrp4` (`member_id`),
  CONSTRAINT `FK1qvrq8r06dcrc5icbmf2gjrp4` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKtc2q2cxde1qm5i1nlqastxh66` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_good`
--

LOCK TABLES `article_good` WRITE;
/*!40000 ALTER TABLE `article_good` DISABLE KEYS */;
INSERT INTO `article_good` VALUES (7,8,5),(14,7,6),(15,6,6),(17,5,6),(18,5,13),(20,8,15),(21,9,1),(22,11,13),(23,6,13),(24,14,5),(25,13,5),(26,12,5),(27,11,5),(28,5,5);
/*!40000 ALTER TABLE `article_good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avatar`
--

DROP TABLE IF EXISTS `avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avatar` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avatar`
--

LOCK TABLES `avatar` WRITE;
/*!40000 ALTER TABLE `avatar` DISABLE KEYS */;
INSERT INTO `avatar` VALUES (1,'avatar1'),(2,'avatar2'),(3,'avatar3'),(4,'avatar4'),(5,'avatar5'),(6,'avatar6'),(7,'avatar7'),(8,'avatar8'),(9,'avatar9'),(10,'avatar10'),(11,'avatar11'),(12,'avatar12'),(13,'avatar13'),(14,'avatar14'),(15,'avatar15'),(16,'avatar16'),(17,'avatar17'),(18,'avatar18'),(19,'avatar19'),(20,'avatar20'),(21,'avatar21'),(22,'avatar22'),(23,'avatar23'),(24,'avatar24'),(25,'avatar25'),(26,'avatar26'),(27,'avatar27'),(28,'avatar28'),(29,'avatar29'),(30,'avatar30'),(31,'avatar31'),(32,'avatar32'),(33,'avatar33'),(34,'avatar34'),(35,'avatar35'),(36,'avatar36'),(37,'avatar37'),(38,'avatar38'),(39,'avatar39'),(40,'avatar40'),(41,'avatar41'),(42,'avatar42'),(43,'avatar43'),(44,'avatar44'),(45,'avatar45'),(46,'avatar46'),(47,'avatar47'),(48,'avatar48'),(49,'avatar49'),(50,'avatar50'),(51,'avatar51'),(52,'avatar52'),(53,'avatar53'),(54,'avatar54'),(55,'avatar55'),(56,'avatar56'),(57,'avatar57'),(58,'avatar58'),(59,'avatar59'),(60,'avatar60'),(61,'avatar61'),(62,'avatar62'),(63,'avatar63'),(64,'avatar64'),(65,'avatar65'),(66,'avatar66'),(67,'avatar67'),(68,'avatar68'),(69,'avatar69'),(70,'avatar70'),(71,'avatar71'),(72,'avatar72'),(73,'avatar73'),(74,'avatar74'),(75,'avatar75'),(76,'avatar76'),(77,'avatar77'),(78,'avatar78'),(79,'avatar79'),(80,'avatar80'),(81,'avatar81'),(82,'avatar82'),(83,'avatar83'),(84,'avatar84'),(85,'avatar85'),(86,'avatar86'),(87,'avatar87'),(88,'avatar88'),(89,'avatar89'),(90,'avatar90'),(91,'avatar91'),(92,'avatar92'),(93,'avatar93'),(94,'avatar94'),(95,'avatar95'),(96,'avatar96'),(97,'avatar97'),(98,'avatar98'),(99,'avatar99'),(100,'avatar100'),(101,'avatar101');
/*!40000 ALTER TABLE `avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `badge_description` varchar(255) NOT NULL,
  `badge_name` varchar(20) NOT NULL,
  `img_address` varchar(255) NOT NULL,
  `max_count` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
INSERT INTO `badge` VALUES (1,'첫번째 일일 정산 완료','출첵1','assets/badge/badge1.png',1),(2,'일일 정산 7일 완료','출첵2','assets/badge/badge2.png',7),(3,'일일 정산 한달 완료','출첵3','assets/badge/badge3.png',30),(4,'첫번째 챌린지 우승','챌린지 우승1','assets/badge/badge4.png',1),(5,'첫번째모든 퀘스트 완료','퀘스트 성공1','assets/badge/badge5.png',1),(6,'첫번째 게시글 평가','게시글 리뷰1','assets/badge/badge6.png',1),(7,'첫번째 뱃지 획득을 기념하는 뱃지','첫 뱃지','assets/badge/badge7.png',1),(8,'더비 뱃지1','더미1','assets/badge/badge8.png',100000000),(9,'회원가입시 증정하는 기념 뱃지','회원가입 기념','assets/badge/badge9.png',1000000000);
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `auth_method` text NOT NULL,
  `category` varchar(64) NOT NULL,
  `description` text NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `lose_team_no` int DEFAULT NULL,
  `max_participants` int DEFAULT '20',
  `min_participants` int NOT NULL,
  `period` varchar(64) NOT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` int DEFAULT '1',
  `title` varchar(256) NOT NULL,
  `total_auth_count` int DEFAULT NULL,
  `win_team_no` int DEFAULT NULL,
  `challenge_master_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl4fm56ioonl0ehtn3w4xfkowt` (`challenge_master_id`),
  CONSTRAINT `FKl4fm56ioonl0ehtn3w4xfkowt` FOREIGN KEY (`challenge_master_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
INSERT INTO `challenge` VALUES (39,'고양이 사진을 올려서 인증해요','기타','고양이 사진을 공유해서 스트레스 완화로 병원비를 아껴요!',NULL,NULL,20,4,'1주',NULL,1,'고양이 보고 힐링하기',7,NULL,1),(40,'텀블러 사진 들고 인증 부탁드려요!!','식비','텀블러쓰고 할인받고\n환경도 챙겨볼 분들 구합니다 ~~ ','2023-08-24 23:59:59.000000',1,20,4,'1주','2023-08-17 21:00:16.418279',3,'텀블러 들고 다니고 할인 받을 분!!',7,0,13),(41,'자전거 탄 사진을 올려주세용 ㅎㅎ','교통','택시에 돈을 너무 많이 쓰게되서요 ..\n대중교통 타는 것 보단\n건강 챙길겸!!!\n자전거를 타고 출근하실 분~~~ !!!','2023-08-24 23:59:59.000000',NULL,20,4,'1주','2023-08-17 22:01:43.648646',2,'자전거 타고 출근해요',7,NULL,13),(43,'포장해오는 사진을 인증해주세요.','식비','배달비 요즘 진짜 어마어마하잖아요\n포장해오면 최소 2천원 최대 7천원까진 아낄 수 있는 요즘 ㅎㅎ\n함께 아껴봅시다',NULL,NULL,20,4,'1주',NULL,1,'배달 대신 포장해요!',7,NULL,13),(44,'파 키우는 사진 인증해주세요','식비','요즘 직접 야채 키워서 먹는게 유행이람서요 ㅎㅎ 절약도 되고,,,\n저도 스크루지 분들과 함께 유행타보겠습니다 ~~ ','2023-08-24 23:59:59.000000',0,20,4,'1주','2023-08-17 21:55:09.926935',3,'파 키우기',7,1,13);
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_auth`
--

DROP TABLE IF EXISTS `challenge_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge_auth` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `img_address` varchar(255) DEFAULT NULL,
  `is_success` bit(1) DEFAULT NULL,
  `challenge_participant_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp9pod7oog1fwju4bn6nbi5bui` (`challenge_participant_id`),
  CONSTRAINT `FKp9pod7oog1fwju4bn6nbi5bui` FOREIGN KEY (`challenge_participant_id`) REFERENCES `challenge_participant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_auth`
--

LOCK TABLES `challenge_auth` WRITE;
/*!40000 ALTER TABLE `challenge_auth` DISABLE KEYS */;
INSERT INTO `challenge_auth` VALUES (1,'2023-08-17 21:10:15.426763','https://storage.googleapis.com/scroogestorage/c71616de-f27d-4d56-89e1-815a6399055e',_binary '\0',42),(2,'2023-08-17 21:10:26.171239','https://storage.googleapis.com/scroogestorage/71dd3186-88fc-4d27-a374-da28b9a517f9',_binary '\0',42),(3,'2023-08-17 21:15:12.047671','https://storage.googleapis.com/scroogestorage/157d9466-4aa1-4af0-aafe-42903fc9ef98',_binary '\0',42),(4,'2023-08-17 21:20:27.236459','https://storage.googleapis.com/scroogestorage/8a0aab54-1b18-4f3d-a478-57f2d98e7879',_binary '',42),(5,NULL,NULL,_binary '',43),(6,NULL,NULL,_binary '',43),(7,NULL,NULL,_binary '',43),(8,'2023-08-17 22:18:33.856502','https://storage.googleapis.com/scroogestorage/386a1197-d1d2-4615-a9fd-077243af60d2',_binary '\0',48),(9,'2023-08-17 22:18:37.588840','https://storage.googleapis.com/scroogestorage/1f504077-a0a9-4df6-8336-d7fdd0b684f5',_binary '\0',48),(10,'2023-08-17 22:21:34.310361','https://storage.googleapis.com/scroogestorage/195fca15-cc52-4d4f-9712-6b59b4fb47b7',_binary '\0',48),(11,'2023-08-17 22:25:23.246421','https://storage.googleapis.com/scroogestorage/5f66f39e-3596-4e53-ab21-a0a22c18ff3f',_binary '\0',48),(12,'2023-08-17 22:29:27.044618','https://storage.googleapis.com/scroogestorage/34f90fda-6ed9-4162-b136-5a14587a6763',_binary '',45),(13,'2023-08-17 22:31:26.026553','https://storage.googleapis.com/scroogestorage/1f8795cc-41c9-4d27-915e-eafe477e9b9b',_binary '\0',56),(14,'2023-08-17 22:31:36.672184','https://storage.googleapis.com/scroogestorage/f67d7c58-055c-40e4-9d26-c97b8b74333f',_binary '\0',48),(15,'2023-08-17 22:32:07.759030','https://storage.googleapis.com/scroogestorage/e47a34c9-7f9b-448b-9046-8da11b31f44a',_binary '\0',48),(16,'2023-08-17 22:32:10.090980','https://storage.googleapis.com/scroogestorage/b2a73008-d5e5-454a-b8ee-c18708c4512e',_binary '',56),(17,'2023-08-17 22:32:49.794990','https://storage.googleapis.com/scroogestorage/3fb85643-ec1d-49fe-9ca9-333eebcb3631',_binary '\0',48),(18,'2023-08-17 22:38:55.263321','https://storage.googleapis.com/scroogestorage/2a34ba94-31a3-4bf6-bfda-9bea246a9d64',_binary '\0',48),(19,'2023-08-17 22:39:10.567073','https://storage.googleapis.com/scroogestorage/f25f61f8-92a3-4e9a-8a20-868d80feede6',_binary '\0',48),(20,'2023-08-17 22:39:35.545784','https://storage.googleapis.com/scroogestorage/2484bbf5-a885-4ee9-b89c-4e8b843fd1bc',_binary '\0',48),(21,'2023-08-17 22:39:48.019236','https://storage.googleapis.com/scroogestorage/b92f12ed-f123-4893-aa71-5f1d4fb0e410',_binary '\0',48),(22,'2023-08-17 22:40:02.950520','https://storage.googleapis.com/scroogestorage/e6a396a2-dc27-4745-8bb4-cd84b4dc15be',_binary '\0',48),(23,'2023-08-17 22:41:18.090871','https://storage.googleapis.com/scroogestorage/ba0e7f42-53bb-4351-a907-c77a0307e55b',_binary '\0',48),(24,'2023-08-17 22:56:16.012670','https://storage.googleapis.com/scroogestorage/3c1596ff-baf5-4197-aeec-0b2c2c8d00ef',_binary '\0',48);
/*!40000 ALTER TABLE `challenge_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_chatting_message`
--

DROP TABLE IF EXISTS `challenge_chatting_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge_chatting_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `challenge_chatting_room_id` bigint DEFAULT NULL,
  `message_author` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrg1j74b0q8062wqp8pxy40lli` (`challenge_chatting_room_id`),
  KEY `FKsc1fhi1ih07pn91uph7pw7qxb` (`message_author`),
  CONSTRAINT `FKrg1j74b0q8062wqp8pxy40lli` FOREIGN KEY (`challenge_chatting_room_id`) REFERENCES `challenge_chatting_room` (`id`),
  CONSTRAINT `FKsc1fhi1ih07pn91uph7pw7qxb` FOREIGN KEY (`message_author`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_chatting_message`
--

LOCK TABLES `challenge_chatting_message` WRITE;
/*!40000 ALTER TABLE `challenge_chatting_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenge_chatting_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_chatting_room`
--

DROP TABLE IF EXISTS `challenge_chatting_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge_chatting_room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `team` int DEFAULT NULL,
  `challenge_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKflmmuyw8rc3h56bh5vxffsao9` (`challenge_id`),
  CONSTRAINT `FKflmmuyw8rc3h56bh5vxffsao9` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_chatting_room`
--

LOCK TABLES `challenge_chatting_room` WRITE;
/*!40000 ALTER TABLE `challenge_chatting_room` DISABLE KEYS */;
INSERT INTO `challenge_chatting_room` VALUES (1,0,40),(2,1,40),(3,0,44),(4,1,44),(5,0,41),(6,1,41);
/*!40000 ALTER TABLE `challenge_chatting_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_chatting_room_participants`
--

DROP TABLE IF EXISTS `challenge_chatting_room_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge_chatting_room_participants` (
  `chatting_room_id` bigint NOT NULL,
  `participant_id` bigint DEFAULT NULL,
  KEY `FK4jbdpx6mkm6tws7t02x512d3x` (`chatting_room_id`),
  CONSTRAINT `FK4jbdpx6mkm6tws7t02x512d3x` FOREIGN KEY (`chatting_room_id`) REFERENCES `challenge_chatting_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_chatting_room_participants`
--

LOCK TABLES `challenge_chatting_room_participants` WRITE;
/*!40000 ALTER TABLE `challenge_chatting_room_participants` DISABLE KEYS */;
INSERT INTO `challenge_chatting_room_participants` VALUES (1,41),(1,43),(2,42),(2,44),(3,52),(3,54),(4,53),(4,55),(5,45),(5,48),(6,47),(6,56);
/*!40000 ALTER TABLE `challenge_chatting_room_participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_example_image`
--

DROP TABLE IF EXISTS `challenge_example_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge_example_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `img_address` varchar(256) DEFAULT NULL,
  `challenge_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4dso6d2q8nbuxp391qcb6x3dk` (`challenge_id`),
  CONSTRAINT `FK4dso6d2q8nbuxp391qcb6x3dk` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_example_image`
--

LOCK TABLES `challenge_example_image` WRITE;
/*!40000 ALTER TABLE `challenge_example_image` DISABLE KEYS */;
INSERT INTO `challenge_example_image` VALUES (23,'https://storage.googleapis.com/scroogestorage/182048b8-f785-472b-982b-c6ee76a848ce',39),(24,'https://storage.googleapis.com/scroogestorage/29b1b753-ab38-42af-8a03-d1d47ce4cf7c',39),(25,'https://storage.googleapis.com/scroogestorage/534be704-88ba-4e11-b7e9-49f3c36d85cd',39),(26,'https://storage.googleapis.com/scroogestorage/889e3389-ae82-4d33-ae7a-d393f7f587b0',40),(27,'https://storage.googleapis.com/scroogestorage/9b92d251-2105-48bd-955d-791b059efb3c',40),(28,'https://storage.googleapis.com/scroogestorage/0903f7a8-3dd5-42c6-b9c6-fa971ce6ec69',40),(29,'https://storage.googleapis.com/scroogestorage/3eb2af60-d40d-4ae2-9a38-d0cc1c7c47b3',40),(30,'https://storage.googleapis.com/scroogestorage/af73eb82-ef70-47cb-ac56-1c7f48e8b6a6',40),(31,'https://storage.googleapis.com/scroogestorage/5ff76a63-fbf8-40ea-88fb-6c24648e3fa3',41),(32,'https://storage.googleapis.com/scroogestorage/02a3381e-e3f5-456e-8882-c14f00efd297',41),(33,'https://storage.googleapis.com/scroogestorage/cc7a9074-9478-43da-bcd4-90fd02e5f58a',41),(34,'https://storage.googleapis.com/scroogestorage/750f9cbc-8148-4f60-90de-a4d1987eec29',41),(35,'https://storage.googleapis.com/scroogestorage/d5655276-1e08-4821-9514-788eb1fddea4',41),(37,'https://storage.googleapis.com/scroogestorage/2998530b-4949-4b82-a384-d35a28a0f704',43),(38,'https://storage.googleapis.com/scroogestorage/aeb6c7a1-9d87-4832-aa57-0d3fbd711765',43),(39,'https://storage.googleapis.com/scroogestorage/b3fd0227-e530-47f1-bc8a-ca65a6b5f34d',43),(40,'https://storage.googleapis.com/scroogestorage/ae6a8f57-f5c0-4aa9-a702-9c04a89fb57d',43),(41,'https://storage.googleapis.com/scroogestorage/edaf8f87-c215-4df5-84c2-8098f0a2f62d',43),(42,'https://storage.googleapis.com/scroogestorage/75a934c0-18d0-4c21-b298-ebafc704cf24',44),(43,'https://storage.googleapis.com/scroogestorage/fddb2b4d-1be1-4b42-99cc-08ba921f636c',44),(44,'https://storage.googleapis.com/scroogestorage/83c120fa-8821-4afe-a514-5a44d1b99e59',44),(45,'https://storage.googleapis.com/scroogestorage/cb3c4993-394a-4413-9b1b-3d2b657e6e7c',44),(46,'https://storage.googleapis.com/scroogestorage/c126a027-5a2d-4033-b880-fda9dfad7401',44);
/*!40000 ALTER TABLE `challenge_example_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_participant`
--

DROP TABLE IF EXISTS `challenge_participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge_participant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `team` int DEFAULT NULL,
  `challenge_id` bigint DEFAULT NULL,
  `challenge_chatting_room_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcmotvehs33fr3oregxapplphf` (`challenge_id`),
  KEY `FKqocnlkl63x6ntes3b13qmt5uw` (`challenge_chatting_room_id`),
  KEY `FKi70mosxh7q3fr2kv5yiqijtea` (`member_id`),
  CONSTRAINT `FKcmotvehs33fr3oregxapplphf` FOREIGN KEY (`challenge_id`) REFERENCES `challenge` (`id`),
  CONSTRAINT `FKi70mosxh7q3fr2kv5yiqijtea` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKqocnlkl63x6ntes3b13qmt5uw` FOREIGN KEY (`challenge_chatting_room_id`) REFERENCES `challenge_chatting_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_participant`
--

LOCK TABLES `challenge_participant` WRITE;
/*!40000 ALTER TABLE `challenge_participant` DISABLE KEYS */;
INSERT INTO `challenge_participant` VALUES (40,0,39,NULL,1),(41,0,40,NULL,13),(42,1,40,NULL,1),(43,0,40,NULL,5),(44,1,40,NULL,4),(45,0,41,NULL,13),(46,1,39,NULL,13),(47,1,41,NULL,5),(48,0,41,NULL,1),(50,0,43,NULL,13),(51,1,43,NULL,1),(52,0,44,NULL,13),(53,1,44,NULL,4),(54,0,44,NULL,14),(55,1,44,NULL,5),(56,1,41,NULL,14),(57,0,43,NULL,2),(60,0,39,NULL,17);
/*!40000 ALTER TABLE `challenge_participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gacha` int NOT NULL,
  `level` int NOT NULL,
  `required_exp` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,2,1,500),(2,2,2,500),(3,2,3,500),(4,2,4,500),(5,2,5,500),(6,2,6,600),(7,2,7,600),(8,2,8,600),(9,2,9,600),(10,2,10,600),(11,3,11,700),(12,3,12,700),(13,3,13,700),(14,3,14,700),(15,3,15,700),(16,3,16,800),(17,3,17,800),(18,3,18,800),(19,3,19,800),(20,3,20,800),(21,4,21,900),(22,4,22,900),(23,4,23,900),(24,4,24,900),(25,4,25,900),(26,4,26,1000),(27,4,27,1000),(28,4,28,1000),(29,4,29,1000),(30,4,30,1000);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `exp` int DEFAULT '0',
  `is_settlement_done` bit(1) DEFAULT NULL,
  `joined_at` datetime(6) DEFAULT NULL,
  `max_streak` int DEFAULT '0',
  `message` varchar(255) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `remain_gacha` int DEFAULT NULL,
  `streak` int DEFAULT '0',
  `weekly_consum` int DEFAULT '0',
  `weekly_goal` int DEFAULT '0',
  `level_id` bigint DEFAULT NULL,
  `main_avatar_id` bigint DEFAULT NULL,
  `main_badge_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKclsk8iy6qqnul2k5v2qtul5ii` (`level_id`),
  KEY `FK3ht2s4wmdgu6huuquq29w0pmd` (`main_avatar_id`),
  KEY `FKgfuv5n0t8c5vfabttnq4qayd9` (`main_badge_id`),
  CONSTRAINT `FK3ht2s4wmdgu6huuquq29w0pmd` FOREIGN KEY (`main_avatar_id`) REFERENCES `avatar` (`id`),
  CONSTRAINT `FKclsk8iy6qqnul2k5v2qtul5ii` FOREIGN KEY (`level_id`) REFERENCES `level` (`id`),
  CONSTRAINT `FKgfuv5n0t8c5vfabttnq4qayd9` FOREIGN KEY (`main_badge_id`) REFERENCES `badge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'leednj0113@naver.com',0,_binary '\0','2023-08-17 14:46:43.338248',0,'유명한','홍박사','$2a$10$RpiODjtNySVrbIDOX5556OcDm.MZ0UwcuLLg1OqU.gsr5kF/IGssO',NULL,0,0,0,0,1,59,9),(2,'woo@dong.choi',300,_binary '\0','2023-08-17 14:50:29.488709',1,'상태는 메시지~','관리자1','$2a$10$IppOqhyzCroOkH8twjsrKu8aiaJ5PaI71/0wntTIHu0at9yvcI4u.',NULL,0,1,1000,200000,1,82,9),(4,'conan@naver.com',200,_binary '\0','2023-08-17 14:54:25.492365',0,'상태메시지를 설정해주세요.','명란젓코난','$2a$10$E.JBcZIeTkZqK1ByctsurOfLqYXEB5Ep7Nip.vaud8eRkwOUvrly6',NULL,0,0,0,0,1,89,9),(5,'qwe123@naver.com',200,_binary '\0','2023-08-17 15:00:14.039321',0,'상태메시지를 설정해주세요.','윤두준잘생겼따','$2a$10$rZRVtF8AdDE3LeyReVVOmOy5Ik.VY5pwKX9msO5upYcBiaGbjTExC',NULL,1,0,16500,0,1,41,9),(6,'nono@no.no',300,_binary '\0','2023-08-17 15:14:47.210817',1,'상태메시지를 설정해주세요.','nono','$2a$10$jusn4QRi/vtaAjIXqdC3GO8ARMXjFfyPsKsMoSYS4TT8KJxnWe5Hi',NULL,2,1,19400,30000,1,66,9),(7,'cat10830@naver.com',0,_binary '\0','2023-08-17 15:21:45.393027',0,'상태메시지를 설정해주세요.','인코모치','$2a$10$6f1n/kWNYfz3QflLcJWTL.iXCTqiduQcgOH.um3R11.2XD4.S0R0G',NULL,2,0,21000,0,1,66,9),(8,'cpghkdi@hanmail.net',0,_binary '\0','2023-08-17 15:24:03.986961',0,'상태메시지를 설정해주세요.','연우이모 ','$2a$10$AZoIdkgoiapW0WkyWgP6lOzxITFGz8ndWAQnjrXhtAAl8BwV40QSG',NULL,2,0,0,0,1,66,9),(9,'cookie@ck.com',100,_binary '\0','2023-08-17 15:27:50.646458',1,'꼬르륵...배가 고파여...','초코쿠키','$2a$10$UO5vFJ2Pk3X2kGcXMEoQvuowXtErEq014b7KQS7PJi4FQLx5KOT96',NULL,2,1,7300,10000,1,66,9),(10,'test1@example.com',0,_binary '\0','2023-08-17 17:48:51.935935',0,'상태메시지를 설정해주세요.','test1','$2a$10$PDRNPejT132QDfGfGJuCneH8bya3E.ijZZnpi90cMgI8oPONwOWwa',NULL,2,0,0,0,1,66,9),(11,'screw@naver.com',0,_binary '\0','2023-08-17 20:41:33.383295',0,'상태메시지를 설정해주세요.','스크루바','$2a$10$7IKfYKIDTnrJHXx9mN2elOsBiHuF7nq5frLbI4mAYM8iRnTOpzPdm',NULL,2,0,0,0,1,66,9),(12,'screw@naver.com',0,_binary '\0','2023-08-17 20:41:33.383057',0,'상태메시지를 설정해주세요.','스크루바','$2a$10$ZRHWQEqI07QBk6hv5G9XceyuQVdNTRSB8ZWcc59gREmO2vSA7XjMS',NULL,2,0,0,0,1,66,9),(13,'happy@naver.com',100,_binary '\0','2023-08-17 20:42:58.780693',8,'해피 해피 해피 ~~~ 🎵','해피캣짱','$2a$10$l9dagxbEaL8jCMCMxTLxHOlG6mZ4xgB5fz9wL8hcm1rKtecRO86ci',NULL,0,6,105400,200000,1,6,9),(14,'gonan@naver.com',100,_binary '\0','2023-08-17 21:53:40.761969',1,'돈이 없어서 매일이 고난','내이름은고난','$2a$10$mefeD/IvZTc1PpHcGyc3s./bn3eg1c1JCOtI1kkzr/RgAlmy/D4PK',NULL,2,1,194500,0,1,66,9),(15,'9gmldud@naver.com',0,_binary '\0','2023-08-17 22:49:33.559729',0,'상태메시지를 설정해주세요.','히영히영','$2a$10$NdU/GsiAQbXsCZe1U9rbYu/qUY7UCG5EsN7Ai/q6XeBqwTOW44nZO',NULL,2,0,0,0,1,66,9),(16,'bob@bob.com',100,_binary '\0','2023-08-17 23:49:14.784399',1,'상태메시지를 설정해주세요.','사랑의밥대리','$2a$10$tx8sfcVHENGdr/rnic5MvO0AaBExEvxUsVhB1uA5O8SCzSQfg6GMy',NULL,0,1,28422,0,1,66,1),(17,'testtest@gmail.com',0,_binary '\0','2023-08-18 01:06:07.711811',0,'상태메시지를 설정해주세요.','김남무123','$2a$10$YZchc4dfwLUixX7LHSTR4uuRtnADD6PYr75YvpdhVtLUpBHHoiLtS',NULL,2,0,0,0,1,66,9);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_challenge_chatting_room`
--

DROP TABLE IF EXISTS `member_challenge_chatting_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_challenge_chatting_room` (
  `member_id` bigint NOT NULL,
  `chatting_room_id` bigint NOT NULL,
  KEY `FK6xva8t0pkgkb1sxb0igubshhm` (`chatting_room_id`),
  KEY `FKbrhr5k09w8v5pxo2hptsuy3of` (`member_id`),
  CONSTRAINT `FK6xva8t0pkgkb1sxb0igubshhm` FOREIGN KEY (`chatting_room_id`) REFERENCES `challenge_chatting_room` (`id`),
  CONSTRAINT `FKbrhr5k09w8v5pxo2hptsuy3of` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_challenge_chatting_room`
--

LOCK TABLES `member_challenge_chatting_room` WRITE;
/*!40000 ALTER TABLE `member_challenge_chatting_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_challenge_chatting_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_owning_avatar`
--

DROP TABLE IF EXISTS `member_owning_avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_owning_avatar` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `acquired_at` datetime(6) DEFAULT NULL,
  `avatar_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm41cudg6kde8fcajupomp8ec2` (`avatar_id`),
  KEY `FK1iyde0snm7pac3onwkwhkrox7` (`member_id`),
  CONSTRAINT `FK1iyde0snm7pac3onwkwhkrox7` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKm41cudg6kde8fcajupomp8ec2` FOREIGN KEY (`avatar_id`) REFERENCES `avatar` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_owning_avatar`
--

LOCK TABLES `member_owning_avatar` WRITE;
/*!40000 ALTER TABLE `member_owning_avatar` DISABLE KEYS */;
INSERT INTO `member_owning_avatar` VALUES (1,'2023-08-17 14:46:43.367730',66,1),(2,'2023-08-17 14:50:29.510722',66,2),(3,'2023-08-17 14:52:22.399118',9,2),(4,'2023-08-17 14:52:23.871199',10,2),(6,'2023-08-17 14:54:25.495687',66,4),(7,'2023-08-17 14:55:40.104360',75,4),(8,'2023-08-17 14:55:44.243038',89,4),(9,'2023-08-17 14:56:24.381315',57,2),(10,'2023-08-17 14:56:25.801759',3,2),(11,'2023-08-17 14:56:26.661677',25,2),(12,'2023-08-17 14:56:27.455560',82,2),(13,'2023-08-17 14:56:28.209481',34,2),(14,'2023-08-17 14:56:29.300111',21,2),(15,'2023-08-17 14:56:30.105494',24,2),(16,'2023-08-17 14:56:30.832456',77,2),(17,'2023-08-17 14:56:31.584404',86,2),(18,'2023-08-17 14:56:32.294660',81,2),(19,'2023-08-17 15:00:14.041888',66,5),(20,'2023-08-17 15:00:31.933961',41,5),(21,'2023-08-17 15:14:47.212994',66,6),(22,'2023-08-17 15:17:50.102885',59,1),(23,'2023-08-17 15:17:51.039196',16,1),(24,'2023-08-17 15:21:45.395300',66,7),(25,'2023-08-17 15:24:03.989018',66,8),(26,'2023-08-17 15:27:50.648662',66,9),(27,'2023-08-17 17:48:51.965766',66,10),(28,'2023-08-17 20:41:33.385658',66,12),(29,'2023-08-17 20:41:33.385651',66,11),(30,'2023-08-17 20:42:58.783068',66,13),(31,'2023-08-17 21:53:40.764058',66,14),(32,'2023-08-17 22:27:12.981695',43,13),(33,'2023-08-17 22:27:14.189480',6,13),(34,'2023-08-17 22:49:33.561658',66,15),(35,'2023-08-17 23:49:14.786121',66,16),(36,'2023-08-17 23:57:01.114318',47,16),(37,'2023-08-17 23:57:14.088336',1,16),(38,'2023-08-18 00:35:13.792754',74,13),(39,'2023-08-18 01:06:07.713925',66,17);
/*!40000 ALTER TABLE `member_owning_avatar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_owning_badge`
--

DROP TABLE IF EXISTS `member_owning_badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_owning_badge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `acquired_at` datetime(6) DEFAULT NULL,
  `badge_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKln03qa3yugojux7f8evlrhdy7` (`badge_id`),
  KEY `FKc4dxk0qfmxhy2iovxwc07pkbp` (`member_id`),
  CONSTRAINT `FKc4dxk0qfmxhy2iovxwc07pkbp` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKln03qa3yugojux7f8evlrhdy7` FOREIGN KEY (`badge_id`) REFERENCES `badge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_owning_badge`
--

LOCK TABLES `member_owning_badge` WRITE;
/*!40000 ALTER TABLE `member_owning_badge` DISABLE KEYS */;
INSERT INTO `member_owning_badge` VALUES (1,'2023-08-17 14:46:43.370809',9,1),(2,'2023-08-17 14:50:29.514537',9,2),(4,'2023-08-17 14:54:25.496705',9,4),(8,'2023-08-17 14:58:28.429535',5,2),(9,'2023-08-17 15:00:14.042789',9,5),(10,'2023-08-17 15:14:47.213810',9,6),(11,'2023-08-17 15:21:45.396113',9,7),(12,'2023-08-17 15:24:03.989755',9,8),(13,'2023-08-17 15:27:50.649491',9,9),(14,'2023-08-17 15:30:01.913967',1,9),(15,'2023-08-17 15:30:01.924095',2,9),(16,'2023-08-17 15:30:01.937101',3,9),(17,'2023-08-17 16:57:28.068900',1,2),(18,'2023-08-17 16:57:28.079906',2,2),(19,'2023-08-17 16:57:28.089593',3,2),(20,'2023-08-17 17:44:48.895891',5,4),(21,'2023-08-17 17:48:51.969002',9,10),(22,'2023-08-17 19:17:37.175177',5,5),(23,'2023-08-17 20:41:33.388792',9,12),(24,'2023-08-17 20:41:33.388990',9,11),(25,'2023-08-17 20:42:58.783974',9,13),(26,'2023-08-17 21:47:38.594340',1,6),(27,'2023-08-17 21:47:38.604807',2,6),(28,'2023-08-17 21:47:38.615931',3,6),(29,'2023-08-17 21:49:54.822330',5,6),(30,'2023-08-17 21:53:40.764787',9,14),(31,'2023-08-17 22:08:36.237561',1,13),(32,'2023-08-17 22:08:36.253780',2,13),(33,'2023-08-17 22:08:36.268620',3,13),(34,'2023-08-17 22:47:57.144705',1,14),(35,'2023-08-17 22:47:57.154914',2,14),(36,'2023-08-17 22:47:57.167689',3,14),(37,'2023-08-17 22:49:33.562400',9,15),(38,'2023-08-17 23:49:14.786785',9,16),(39,'2023-08-17 23:55:50.570446',1,16),(40,'2023-08-17 23:55:50.581365',2,16),(41,'2023-08-17 23:55:50.591148',3,16),(42,'2023-08-18 00:21:02.087641',5,13),(43,'2023-08-18 01:06:07.714695',9,17);
/*!40000 ALTER TABLE `member_owning_badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_selected_quest`
--

DROP TABLE IF EXISTS `member_selected_quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_selected_quest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `complete_count` int DEFAULT NULL,
  `is_done` bit(1) DEFAULT NULL,
  `is_selected` bit(1) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `quest_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmj2hec3n0ib92wsad4sw507vq` (`member_id`),
  KEY `FK7fdjvledyoj0fuc5v4nr80xt` (`quest_id`),
  CONSTRAINT `FK7fdjvledyoj0fuc5v4nr80xt` FOREIGN KEY (`quest_id`) REFERENCES `quest` (`id`),
  CONSTRAINT `FKmj2hec3n0ib92wsad4sw507vq` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_selected_quest`
--

LOCK TABLES `member_selected_quest` WRITE;
/*!40000 ALTER TABLE `member_selected_quest` DISABLE KEYS */;
INSERT INTO `member_selected_quest` VALUES (7,0,_binary '\0',_binary '',4,3),(8,2,_binary '',_binary '',4,4),(9,0,_binary '\0',_binary '\0',4,6),(10,0,_binary '\0',_binary '\0',4,1),(11,0,_binary '\0',_binary '\0',4,2),(12,0,_binary '\0',_binary '\0',4,5),(13,1,_binary '\0',_binary '',2,3),(14,0,_binary '\0',_binary '\0',2,1),(15,2,_binary '',_binary '',2,4),(16,0,_binary '\0',_binary '\0',2,2),(17,0,_binary '\0',_binary '\0',2,5),(18,2,_binary '\0',_binary '',2,6),(19,0,_binary '\0',_binary '\0',5,5),(20,0,_binary '\0',_binary '',5,1),(21,0,_binary '\0',_binary '\0',5,6),(22,3,_binary '\0',_binary '',5,3),(23,0,_binary '\0',_binary '\0',5,2),(24,2,_binary '',_binary '',5,4),(25,0,_binary '\0',_binary '\0',1,5),(26,0,_binary '\0',_binary '\0',1,3),(27,0,_binary '\0',_binary '\0',1,2),(28,0,_binary '\0',_binary '\0',1,6),(29,0,_binary '\0',_binary '\0',1,4),(30,0,_binary '\0',_binary '\0',1,1),(31,0,_binary '\0',_binary '',6,1),(32,0,_binary '\0',_binary '\0',6,3),(33,10,_binary '',_binary '',6,6),(34,0,_binary '\0',_binary '',6,5),(35,0,_binary '\0',_binary '\0',6,2),(36,0,_binary '\0',_binary '\0',6,4),(37,0,_binary '\0',_binary '',7,2),(38,0,_binary '\0',_binary '',7,3),(39,0,_binary '\0',_binary '\0',7,5),(40,0,_binary '\0',_binary '\0',7,4),(41,0,_binary '\0',_binary '\0',7,6),(42,0,_binary '\0',_binary '\0',7,1),(43,0,_binary '\0',_binary '',9,5),(44,0,_binary '\0',_binary '\0',9,3),(45,0,_binary '\0',_binary '\0',9,4),(46,0,_binary '\0',_binary '\0',9,2),(47,0,_binary '\0',_binary '\0',9,6),(48,0,_binary '\0',_binary '\0',9,1),(49,0,_binary '\0',_binary '\0',13,6),(50,0,_binary '\0',_binary '\0',13,2),(51,0,_binary '\0',_binary '\0',13,3),(52,0,_binary '\0',_binary '\0',13,5),(53,1,_binary '\0',_binary '',13,4),(54,0,_binary '\0',_binary '\0',13,1),(55,0,_binary '\0',_binary '\0',14,6),(56,0,_binary '\0',_binary '\0',14,4),(57,0,_binary '\0',_binary '\0',14,2),(58,0,_binary '\0',_binary '\0',14,5),(59,0,_binary '\0',_binary '\0',14,1),(60,0,_binary '\0',_binary '\0',14,3),(61,0,_binary '\0',_binary '\0',16,4),(62,0,_binary '\0',_binary '\0',16,1),(63,0,_binary '\0',_binary '\0',16,6),(64,0,_binary '\0',_binary '\0',16,2),(65,0,_binary '\0',_binary '\0',16,3),(66,0,_binary '\0',_binary '\0',16,5),(67,0,_binary '\0',_binary '\0',17,2),(68,0,_binary '\0',_binary '\0',17,5),(69,0,_binary '\0',_binary '\0',17,6),(70,0,_binary '\0',_binary '\0',17,1),(71,0,_binary '\0',_binary '\0',17,3),(72,0,_binary '\0',_binary '\0',17,4);
/*!40000 ALTER TABLE `member_selected_quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_history`
--

DROP TABLE IF EXISTS `payment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `card_name` varchar(255) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `is_settled` tinyint(1) DEFAULT '0',
  `paid_at` datetime(6) DEFAULT NULL,
  `used_at` varchar(255) NOT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK10d3sxnur6a5e9x82ow8mxuvc` (`member_id`),
  CONSTRAINT `FK10d3sxnur6a5e9x82ow8mxuvc` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_history`
--

LOCK TABLES `payment_history` WRITE;
/*!40000 ALTER TABLE `payment_history` DISABLE KEYS */;
INSERT INTO `payment_history` VALUES (1,6100,'농협','식비',1,'2023-08-17 16:03:00.000000','이디야 수박주스',9),(2,1200,'농협','식비',1,'2023-08-17 16:03:00.000000','CU 삼각김밥',9),(3,1000,'카드없음',NULL,0,'2023-08-15 16:57:00.000000','커피',2),(4,9300,'신한카드 ','식비',1,'2023-08-17 18:45:02.670625','주식회사 롯데리아 신호점',6),(5,7500,' ',NULL,0,'2023-08-17 19:07:38.744164','맘스터치 신명지점',7),(6,13500,' ',NULL,0,'2023-08-17 19:42:08.915108','주식회사 아성다이소',7),(7,13000,'카드없음',NULL,0,'2023-08-14 20:54:00.000000','마라탕',5),(8,3500,'카드없음',NULL,0,'2023-08-17 20:59:00.000000','컴포즈 커피',5),(9,5800,'삼성카드','문화생활',1,'2023-08-17 21:46:00.000000','SSAFY',6),(10,4300,'삼성카드','식비',1,'2023-08-17 21:46:00.000000','이디야커피 신호점',6),(11,1100,'우리카드(BC)','식비',1,'2023-08-17 22:07:00.000000','이마트24 녹산',13),(12,1000,'우리카드(BC)','교통비',1,'2023-08-17 22:07:00.000000','교통카드 충전',13),(13,3400,'우리카드(BC)','식비',1,'2023-08-17 22:07:00.000000','컴포즈커피 녹산',13),(14,8500,'우리카드(BC)','식비',1,'2023-08-17 22:08:00.000000','(주)우아한형제',13),(15,10000,'우리카드(BC)','기타',1,'2023-08-16 22:09:00.000000','배달의 민족 쿠폰 (생일 선물)',13),(16,18000,'우리카드(BC)','문화생활',1,'2023-08-16 22:10:00.000000','부일페 티켓 2장',13),(17,5900,'우리카드(BC)','식비',1,'2023-08-15 22:10:00.000000','스타벅스 코리아',13),(18,10300,'우리카드(BC)','식비',1,'2023-08-15 22:10:00.000000','맥도날드',13),(19,5700,'우리카드(BC)','쇼핑',1,'2023-08-15 22:11:00.000000','올리브영 명지점',13),(20,4000,'우리카드(BC)','기타',1,'2023-08-15 22:11:00.000000','산타코인노래연습장',13),(21,1000,'우리카드(BC)','교통비',1,'2023-08-15 22:11:00.000000','교통카드 충전',13),(22,1000,'우리카드(BC)','식비',1,'2023-08-14 22:13:00.000000','캔커피',13),(23,1500,'우리카드(BC)','식비',1,'2023-08-13 22:14:00.000000','이디야',13),(24,15900,'우리카드(BC)','식비',1,'2023-08-13 22:14:00.000000','피자굽는나무꾼',13),(25,7590,'우리카드(BC)','정기구독',1,'2023-08-13 22:14:00.000000','FLO',13),(26,4800,'우리카드(BC)','정기구독',1,'2023-08-12 22:15:00.000000','NETFIX',13),(27,2500,'우리카드(BC)','기타',1,'2023-08-12 22:15:00.000000','이마트24 녹산',13),(28,600,'우리카드(BC)','식비',1,'2023-08-10 22:18:00.000000','비타파워',13),(29,1000,'우리카드(BC)','교통비',1,'2023-08-10 22:18:00.000000','교통카드 충전',13),(30,1000,'우리카드(BC)','식비',1,'2023-08-08 22:19:00.000000','얼음컵 2개',13),(31,1000,'우리카드(BC)','식비',1,'2023-08-08 22:19:00.000000','블루레몬에이드',13),(32,2500,'우리카드(BC)','식비',1,'2023-08-07 22:20:00.000000','이마트24 녹산',13),(33,11160,'우리카드(BC)','식비',1,'2023-08-07 22:20:00.000000','공차 부산명지점',13),(34,2700,'우리카드(BC)','쇼핑',1,'2023-08-07 22:20:00.000000','올리브영',13),(35,7900,'우리카드(BC)','정기구독',1,'2023-08-06 22:22:00.000000','TVING',13),(36,27700,'우리카드(BC)','식비',1,'2023-08-06 22:22:00.000000','배달의민족 맥도날드',13),(37,16000,'우리카드(BC)','쇼핑',1,'2023-08-06 22:22:00.000000','다이소',13),(38,7000,'우리카드(BC)','식비',1,'2023-08-06 22:22:00.000000','배달의민족 타코야끼',13),(39,11000,'우리카드(BC)','식비',1,'2023-08-05 22:23:00.000000','아이스크림 할인점',13),(40,2000,'우리카드(BC)','식비',1,'2023-08-05 22:23:00.000000','블루샥',13),(41,1000,'우리카드(BC)','교통비',1,'2023-08-04 22:24:00.000000','교통카드 충전',13),(42,28200,'우리카드(BC)','식비',1,'2023-08-04 22:24:00.000000','배달의 민족 닭강정',13),(43,500,'우리카드(BC)','식비',1,'2023-08-03 22:25:00.000000','얼음컵',13),(44,1000,'우리카드(BC)','교통비',1,'2023-08-03 22:25:00.000000','교통카드 충전',13),(45,8000,'우리카드(BC)','식비',1,'2023-08-02 22:25:00.000000','설빙 명지점',13),(46,2500,'우리카드(BC)','기타',1,'2023-08-02 22:25:00.000000','포토이즘박스',13),(47,500,'우리카드(BC)','식비',1,'2023-08-01 22:25:00.000000','얼음컵',13),(48,1000,'우리카드(BC)','식비',1,'2023-08-01 22:26:00.000000','블루레몬에이드',13),(49,200,'우리카드(BC)','식비',1,'2023-08-01 22:26:00.000000','컴포즈커피',13),(50,5000,'우리카드(BC)','식비',1,'2023-08-17 22:46:00.000000','불닭볶음면 한봉지',14),(51,165000,'우리카드(BC)','문화생활',1,'2023-08-17 22:47:00.000000','부산 락 페스티벌',14),(52,2500,'우리카드(BC)','식비',1,'2023-08-17 22:47:00.000000','컴포즈 커피',14),(53,22000,'우리카드(BC)','식비',1,'2023-07-29 22:52:00.000000','배달의 민족 ',14),(54,24000,'우리카드(BC)',NULL,0,'2023-07-29 22:53:00.000000','이디야',13),(55,9300,'우리카드(BC)','식비',1,'2023-08-17 23:48:00.000000','주식회사 롯데리아 신호점',13),(56,5000,'안ㅇㄹㅇㄹ','교통비',1,'2023-08-17 23:55:00.000000','안냥',16),(57,23422,'ㅇㄹㄴㅇ','기타',1,'2023-08-17 23:56:00.000000','ㄹㄴㅇㄹㄴㅇ',16),(58,2200,' ',NULL,0,'2023-08-18 08:38:48.316423','이마트24 녹산더시티점',13);
/*!40000 ALTER TABLE `payment_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quest`
--

DROP TABLE IF EXISTS `quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `max_count` int NOT NULL,
  `title` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest`
--

LOCK TABLES `quest` WRITE;
/*!40000 ALTER TABLE `quest` DISABLE KEYS */;
INSERT INTO `quest` VALUES (1,'한 주 동안 일일 정산 4회 이상 하기',4,'일일 정산 4번'),(2,'한 주 동안 2만원 이하로 소비 3일 이상 하기',3,'일일 소비금액 2만원 이하(3일)'),(3,'아무 게시들이나 댓글 5회 이상 작성하기',5,'댓글 5개 작성'),(4,'게시글 작성 2회 이상 하기',2,'게시글 작성 2회'),(5,'본인이 참여한 챌린지에 인증 5회 이상 하기',5,'챌린지 일일 참여 인증 5회'),(6,'게시글 좋아요/싫어요 10회 이상 하기',10,'게시글 평가 10회');
/*!40000 ALTER TABLE `quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload`
--

DROP TABLE IF EXISTS `upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `img_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload`
--

LOCK TABLES `upload` WRITE;
/*!40000 ALTER TABLE `upload` DISABLE KEYS */;
/*!40000 ALTER TABLE `upload` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18  9:42:03
