-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: localhost    Database: demo_java_api
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `up_device_admin_ref`
--

DROP TABLE IF EXISTS `up_device_admin_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_device_admin_ref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_sn` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备sn',
  `user_id` int NOT NULL COMMENT '用户id',
  `device_admin_ref_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备管理员关系名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `device_sn_index` (`device_sn`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备管理员关系信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_device_info`
--

DROP TABLE IF EXISTS `up_device_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_device_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_sn` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备sn',
  `device_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备名称',
  `location_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域id',
  `device_type_id` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备类型id',
  `remote_operation` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `device_sn` (`device_sn`),
  KEY `device_sn_index` (`device_sn`),
  KEY `device_name_index` (`device_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_device_key_info`
--

DROP TABLE IF EXISTS `up_device_key_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_device_key_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_sn` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备sn',
  `phone` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '钥匙持有人手机号',
  `owner` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '钥匙持有人',
  `start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `device_id_index` (`device_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up区域信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_device_log_info`
--

DROP TABLE IF EXISTS `up_device_log_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_device_log_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_sn` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备sn',
  `device_log_description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备记录描述',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `device_sn_index` (`device_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备记录信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_device_type_info`
--

DROP TABLE IF EXISTS `up_device_type_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_device_type_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `device_type_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备类型名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `device_type_name_index` (`device_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备类型信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_group`
--

DROP TABLE IF EXISTS `up_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户组名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `group_name_index` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户组信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_group_role_ref`
--

DROP TABLE IF EXISTS `up_group_role_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_group_role_ref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL COMMENT '用户组id',
  `role_id` int NOT NULL COMMENT '角色id',
  `group_role_ref_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户组角色关系名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `role_id_index` (`role_id`),
  KEY `group_id_index` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户组用户关系信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_location_info`
--

DROP TABLE IF EXISTS `up_location_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_location_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域名称',
  `venue_id` int DEFAULT NULL COMMENT '场馆id',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `location_name_index` (`location_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up区域信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_permission`
--

DROP TABLE IF EXISTS `up_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
  `permission_type_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限类型名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `permission_name_index` (`permission_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up权限信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_role`
--

DROP TABLE IF EXISTS `up_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `role_name_index` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_role_permission_ref`
--

DROP TABLE IF EXISTS `up_role_permission_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_role_permission_ref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_id` int NOT NULL COMMENT '权限id',
  `role_id` int NOT NULL COMMENT '角色id',
  `role_permission_ref_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限关系名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `permission_id_index` (`permission_id`),
  KEY `role_id_index` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up角色权限关系信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_user`
--

DROP TABLE IF EXISTS `up_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `phone` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `venue_id` int NOT NULL COMMENT '场馆id',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `open_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `user_name_index` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_user_group_ref`
--

DROP TABLE IF EXISTS `up_user_group_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_user_group_ref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `group_id` int NOT NULL COMMENT '用户组id',
  `user_group_ref_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户组关系名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `user_id_index` (`user_id`),
  KEY `group_id_index` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户组用户关系信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_user_role_ref`
--

DROP TABLE IF EXISTS `up_user_role_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_user_role_ref` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  `user_role_ref_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户角色关系名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `user_id_index` (`user_id`),
  KEY `role_id_index` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户角色关系信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `up_venue_info`
--

DROP TABLE IF EXISTS `up_venue_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `up_venue_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `venue_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '场馆名称',
  `is_in_use` tinyint NOT NULL COMMENT '是否有效',
  `creator` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `venue_name_index` (`venue_name`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up场馆信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-20 21:35:35


--
-- Dumping data for table `up_device_admin_ref`
--

LOCK TABLES `up_device_admin_ref` WRITE;
/*!40000 ALTER TABLE `up_device_admin_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_device_admin_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_device_info`
--

LOCK TABLES `up_device_info` WRITE;
/*!40000 ALTER TABLE `up_device_info` DISABLE KEYS */;
INSERT INTO `up_device_info` VALUES (1,'2205410383','象山303门禁机','1','1',NULL,1,'平台管理员','2023-01-20 22:16:19','平台管理员','2023-01-20 22:16:19',NULL),(2,'2204419775','象山304门禁机','2','1',NULL,1,'平台管理员','2023-01-20 22:16:19','平台管理员','2023-01-20 22:16:19',NULL),(3,'2205410451','象山308门禁机','3','1',NULL,1,'平台管理员','2023-01-20 22:16:19','平台管理员','2023-01-20 22:16:19',NULL),(4,'2205410036','象山玻璃门门禁机','4','1',NULL,1,'平台管理员','2023-01-20 22:16:19','平台管理员','2023-01-20 22:16:19',NULL);
/*!40000 ALTER TABLE `up_device_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_device_key_info`
--

LOCK TABLES `up_device_key_info` WRITE;
/*!40000 ALTER TABLE `up_device_key_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_device_key_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_device_log_info`
--

LOCK TABLES `up_device_log_info` WRITE;
/*!40000 ALTER TABLE `up_device_log_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_device_log_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_device_type_info`
--

LOCK TABLES `up_device_type_info` WRITE;
/*!40000 ALTER TABLE `up_device_type_info` DISABLE KEYS */;
INSERT INTO `up_device_type_info` VALUES (1,'门禁机',1,'平台管理员','2023-01-20 22:16:52','平台管理员','2023-01-20 22:16:52',NULL);
/*!40000 ALTER TABLE `up_device_type_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_group`
--

LOCK TABLES `up_group` WRITE;
/*!40000 ALTER TABLE `up_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_group_role_ref`
--

LOCK TABLES `up_group_role_ref` WRITE;
/*!40000 ALTER TABLE `up_group_role_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_group_role_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_location_info`
--

LOCK TABLES `up_location_info` WRITE;
/*!40000 ALTER TABLE `up_location_info` DISABLE KEYS */;
INSERT INTO `up_location_info` VALUES (1,'象山303教室',1,1,'平台管理员','2023-01-20 22:04:05','平台管理员','2023-01-20 22:04:05',NULL),(2,'象山304教室',1,1,'平台管理员','2023-01-20 22:06:57','平台管理员','2023-01-20 22:06:57',NULL),(3,'象山308教室',1,1,'平台管理员','2023-01-20 22:06:57','平台管理员','2023-01-20 22:06:57',NULL),(4,'象山玻璃门教室',1,1,'平台管理员','2023-01-20 22:06:57','平台管理员','2023-01-20 22:06:57',NULL);
/*!40000 ALTER TABLE `up_location_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_permission`
--

LOCK TABLES `up_permission` WRITE;
/*!40000 ALTER TABLE `up_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_role`
--

LOCK TABLES `up_role` WRITE;
/*!40000 ALTER TABLE `up_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_role_permission_ref`
--

LOCK TABLES `up_role_permission_ref` WRITE;
/*!40000 ALTER TABLE `up_role_permission_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_role_permission_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_user`
--

LOCK TABLES `up_user` WRITE;
/*!40000 ALTER TABLE `up_user` DISABLE KEYS */;
INSERT INTO `up_user` VALUES (1,'安峰','15822573101',1,1,'平台管理员','2023-01-20 22:21:44','平台管理员','2023-01-20 22:21:44',NULL,NULL,NULL),(2,'赵洪涛','15811584042',1,1,'平台管理员','2023-01-20 22:21:44','平台管理员','2023-01-20 22:21:44',NULL,NULL,NULL),(3,'里查德','17898078878',1,1,'平台管理员','2023-01-20 22:21:44','平台管理员','2023-01-20 22:21:44',NULL,NULL,NULL),(4,'汤婷婷','15268396139',1,1,'平台管理员','2023-01-20 22:21:44','平台管理员','2023-01-20 22:21:44',NULL,NULL,NULL),(5,'郭固婷','13578998363',1,1,'平台管理员','2023-01-20 22:21:44','平台管理员','2023-01-20 22:21:44',NULL,NULL,NULL);
/*!40000 ALTER TABLE `up_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_user_group_ref`
--

LOCK TABLES `up_user_group_ref` WRITE;
/*!40000 ALTER TABLE `up_user_group_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_user_group_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_user_role_ref`
--

LOCK TABLES `up_user_role_ref` WRITE;
/*!40000 ALTER TABLE `up_user_role_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `up_user_role_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `up_venue_info`
--

LOCK TABLES `up_venue_info` WRITE;
/*!40000 ALTER TABLE `up_venue_info` DISABLE KEYS */;
INSERT INTO `up_venue_info` VALUES (1,'象山文化馆',1,'平台管理员','2023-01-20 22:05:04','平台管理员','2023-01-20 22:05:04',NULL),(2,'北仑文化馆',1,'平台管理员','2023-01-20 22:07:25','平台管理员','2023-01-20 22:07:25',NULL);
/*!40000 ALTER TABLE `up_venue_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-20 22:25:54
LOCK TABLES `up_device_admin_ref` WRITE;
/*!40000 ALTER TABLE `up_device_admin_ref` DISABLE KEYS */;
INSERT INTO `up_device_admin_ref` VALUES (1,'2204419775',1,'2204419775管理员1',1,'管理员','2022-12-25 11:47:13','管理员','2022-12-25 11:47:13',NULL),(2,'2204419775',2,'2204419775管理员2',1,'管理员','2022-12-25 11:47:13','管理员','2022-12-25 11:47:13',NULL),(3,'2204419775',3,'2204419775管理员3',1,'管理员','2023-01-02 21:06:15','管理员','2023-01-02 21:06:15',NULL),(4,'2204419775',4,'2204419775管理员4',1,'管理员','2023-01-05 20:21:43','管理员','2023-01-05 20:21:43',NULL),(5,'2204419775',5,'2204419775管理员5',1,'管理员','2023-01-09 21:58:29','管理员','2023-01-09 21:58:29',NULL),(6,'2205410383',1,'2205410383管理员1',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(7,'2205410383',2,'2205410383管理员2',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(8,'2205410383',3,'2205410383管理员3',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(9,'2205410383',4,'2205410383管理员4',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(10,'2205410383',5,'2205410383管理员5',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(11,'2205410451',1,'2205410451管理员1',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(12,'2205410451',2,'2205410451管理员2',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(13,'2205410451',3,'2205410451管理员3',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(14,'2205410451',4,'2205410451管理员4',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(15,'2205410451',5,'2205410451管理员5',1,'管理员','2023-01-16 18:26:38','管理员','2023-01-16 18:26:38',NULL),(16,'2205410036',1,'2205410036管理员1',1,'管理员','2023-01-16 18:28:32','管理员','2023-01-16 18:28:32',NULL),(17,'2205410036',2,'2205410036管理员2',1,'管理员','2023-01-16 18:28:32','管理员','2023-01-16 18:28:32',NULL),(18,'2205410036',3,'2205410036管理员3',1,'管理员','2023-01-16 18:28:32','管理员','2023-01-16 18:28:32',NULL),(19,'2205410036',4,'2205410036管理员4',1,'管理员','2023-01-16 18:28:32','管理员','2023-01-16 18:28:32',NULL),(20,'2205410036',5,'2205410036管理员5',1,'管理员','2023-01-16 18:28:32','管理员','2023-01-16 18:28:32',NULL);
/*!40000 ALTER TABLE `up_device_admin_ref` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

LOCK TABLES `up_role` WRITE;
/*!40000 ALTER TABLE `up_role` DISABLE KEYS */;
INSERT INTO `up_role` VALUES (1,'sys_admin',1,'管理员','2022-12-24 16:37:38','管理员','2022-12-24 16:37:38',NULL),(2,'platform_admin',1,'管理员','2022-12-24 16:37:38','管理员','2022-12-24 16:37:38',NULL),(3,'visitor',1,'管理员','2022-12-24 16:37:38','管理员','2022-12-24 16:37:38',NULL),(4,'venue_staff',1,'管理员','2022-12-24 16:37:38','管理员','2022-12-24 16:37:38',NULL),(5,'venue_leader',1,'管理员','2022-12-24 16:37:38','管理员','2022-12-24 16:37:38',NULL);
/*!40000 ALTER TABLE `up_role` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `up_user_role_ref` WRITE;
/*!40000 ALTER TABLE `up_user_role_ref` DISABLE KEYS */;
INSERT INTO `up_user_role_ref` VALUES (1,1,1,'安峰的sysadmin关系',1,'管理员','2023-01-04 22:19:41','管理员','2023-01-04 22:19:41',NULL),(2,2,1,'赵洪涛的sysadmin关系',1,'管理员','2023-01-04 22:19:41','管理员','2023-01-04 22:19:41',NULL),(3,3,1,'里查德的sysadmin关系',1,'管理员','2023-01-15 22:39:48','里查德','2023-01-15 22:39:48',NULL),(4,4,1,'汤婷婷的sysadmin关系',1,'管理员','2023-01-16 19:00:10','管理员','2023-01-16 19:00:10',NULL),(5,5,1,'郭固婷的sysadmin关系',1,'管理员','2023-01-16 19:00:10','管理员','2023-01-16 19:00:10',NULL);
/*!40000 ALTER TABLE `up_user_role_ref` ENABLE KEYS */;
UNLOCK TABLES;



/*
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('潘优慧', '13566647353', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('黄小哇', '13652080125', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('8', '1', '黄小哇的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('7', '1', '谢之涛的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('安峰', '18322411847', '2', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('9', '1', '安峰的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('里查德', '13920754903', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('10', '1', '里查德的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('张城', '15057431240', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('11', '1', '张城的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('郭固婷', '18002148363', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('12', '1', '郭固婷的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('胡老师', '15958252918', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('13', '1', '胡老师的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('张师傅', '15858401328', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('14', '1', '张师傅的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('赵洁', '18892626172', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('15', '1', '赵洁的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('冯蕾', '13906696922', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('16', '1', '冯蕾的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('汪蓉', '13456163100', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('17', '1', '汪蓉的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('王丽丽', '13065633186', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('18', '1', '王丽丽的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('金燕萍', '15869393375', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('19', '1', '金燕萍的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('唐文虎', '13586851949', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('20', '1', '唐文虎的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('孔工', '18301125620', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('21', '1', '孔工的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('付群杰', '18612198755', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('22', '1', '付群杰的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('何薇', '18157442201', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('23', '1', '何薇的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('卢惠雨', '13757461880', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('24', '1', '卢惠雨的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('童吉阳', '15824210599', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('25', '1', '童吉阳的sysadmin关系', '1', '管理员', '管理员');
INSERT INTO `up_prod`.`up_user` (`user_name`, `phone`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('鄞州管理员A', '15088860868', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_user_role_ref` (`user_id`, `role_id`, `user_role_ref_name`, `is_in_use`, `creator`, `modifier`) VALUES ('26', '1', '鄞州管理员A的sysadmin关系', '1', '管理员', '管理员');

2024.7.1 300教室 2313501226 326教室 2312501231
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('300教室', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('326教室', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2312501226', '象山300门禁机', '32', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2312501231', '象山326门禁机', '33', '1', '1', '平台管理员', '平台管理员');

INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('215教室', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('大玻璃门教室', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('小玻璃门教室', '2', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2208413346', '北仑215门禁机', '7', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2208412960', '北仑大玻璃门门禁机', '8', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2204419779', '北仑小玻璃门门禁机', '9', '1', '1', '平台管理员', '平台管理员');

INSERT INTO `up_prod`.`up_venue_info` (`venue_name`, `is_in_use`, `creator`, `modifier`) VALUES ('彭山文化馆', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_type_info` (`device_type_name`, `is_in_use`, `creator`, `modifier`) VALUES ('pad', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山203教室', 3, '1', '平台管理员', '平台管理员', '{\"area\":\"15\",\"volume\":\"10\",\"desc\":\"彭山203教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山204教室', 3, '1', '平台管理员', '平台管理员', '{\"area\":\"25\",\"volume\":\"100\",\"desc\":\"彭山204教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山205教室', 3, '1', '平台管理员', '平台管理员', '{\"area\":\"65\",\"volume\":\"80\",\"desc\":\"彭山205教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山206教室', 3, '1', '平台管理员', '平台管理员', '{\"area\":\"45\",\"volume\":\"60\",\"desc\":\"彭山206教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山207教室', 3, '1', '平台管理员', '平台管理员', '{\"area\":\"85\",\"volume\":\"70\",\"desc\":\"彭山207教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up_prod`.`up_device_type_info` (`device_type_name`, `is_in_use`, `creator`, `modifier`) VALUES ('pad', '1', '平台管理员', '平台管理员');
ALTER TABLE `up_prod`.`up_device_info` CHANGE COLUMN `remark` `remark` VARCHAR(2000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL COMMENT '设备详情' ;
ALTER TABLE `up_prod`.`up_location_info` CHANGE COLUMN `remark` `remark` VARCHAR(2000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL COMMENT '教室详情' ;
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006773191', '彭山203电子班牌', '10', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006790051', '彭山204电子班牌', '11', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/up-location-info/get-location-detail\",\"versionCode\":\"102\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006763750', '彭山205电子班牌', '12', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006906882', '彭山206电子班牌', '13', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006804267', '彭山207电子班牌', '14', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');

INSERT INTO `up`.`up_venue_info` (`venue_name`, `is_in_use`, `creator`, `modifier`) VALUES ('彭山文化馆', '1', '平台管理员', '平台管理员');
INSERT INTO `up`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山202教室', '3', '1', '平台管理员', '平台管理员', '{\"area\":\"15\",\"volume\":\"10\",\"desc\":\"彭山202教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up`.`up_device_type_info` (`device_type_name`, `is_in_use`, `creator`, `modifier`) VALUES ('pad', '1', '平台管理员', '平台管理员');
ALTER TABLE `up`.`up_device_info` CHANGE COLUMN `remark` `remark` VARCHAR(2000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL COMMENT '设备详情' ;
ALTER TABLE `up`.`up_location_info` CHANGE COLUMN `remark` `remark` VARCHAR(2000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL COMMENT '教室详情' ;
--查看location根据需求更新 正式PAD设备SN 2ML2248010486126
INSERT INTO `up`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('test123456', '彭山202电子班牌', '10008', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/test/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
{"type":"默认","bgImg":"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png","logo":"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png","selfDefine":"","dataSourceType":"selfDefine","dataSource":"https://upcloudtech.cn/test/up-location-info/get-location-detail","versionCode":"101","bannerImages":"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg","otherUrl":"https://pengshan-szwhg.chaoxing.com/third/room","updateUrl":"https://upcloudtech.cn/resource/img/banpai.apk"
INSERT INTO `up`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山203教室', '10001', '1', '平台管理员', '平台管理员', '{\"area\":\"15\",\"volume\":\"10\",\"desc\":\"彭山203教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山204教室', '10001', '1', '平台管理员', '平台管理员', '{\"area\":\"25\",\"volume\":\"100\",\"desc\":\"彭山204教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山205教室', '10001', '1', '平台管理员', '平台管理员', '{\"area\":\"65\",\"volume\":\"80\",\"desc\":\"彭山205教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山206教室', '10001', '1', '平台管理员', '平台管理员', '{\"area\":\"45\",\"volume\":\"60\",\"desc\":\"彭山206教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('彭山207教室', '10001', '1', '平台管理员', '平台管理员', '{\"area\":\"85\",\"volume\":\"70\",\"desc\":\"彭山207教室简介\",\"image\":\"https://pengshan-szwhg.chaoxing.com/upload/admin/20230529/d56f51780633625f36bee59976c3a804.jpg\"}');
INSERT INTO `up`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006773191', '彭山203电子班牌', '10010', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/test/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006790051', '彭山204电子班牌', '10011', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/test/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006763750', '彭山205电子班牌', '10012', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/test/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006906882', '彭山206电子班牌', '10013', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/test/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');
INSERT INTO `up`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`, `remark`) VALUES ('3ME2315006804267', '彭山207电子班牌', '10014', '2', '1', '平台管理员', '平台管理员', '{\"type\":\"默认\",\"bgImg\":\"https://upcloudtech.cn/resource/img/ff3082d9-f59a-421f-9828-76219cdf14df.png\",\"logo\":\"https://upcloudtech.cn/resource/img/d80f1fc1-64c4-48f5-a8f8-de93a30c8c4d.png\",\"selfDefine\":\"\",\"dataSourceType\":\"selfDefine\",\"dataSource\":\"https://upcloudtech.cn/test/up-location-info/get-location-detail\",\"versionCode\":\"101\",\"bannerImages\":\"https://upcloudtech.cn/resource/img/f1ccbfd2-89f8-4cfb-af90-8717da5a5352.jpg,https://upcloudtech.cn/resource/img/cc5fef13-589a-4e42-97b6-7a7a4b497878.jpg,https://upcloudtech.cn/resource/img/cfe25b42-1b99-417d-97e4-7b8fbf08d523.jpg,https://upcloudtech.cn/resource/img/de3b8c4b-1eb0-4622-a171-7a948fbd78f7.jpg\",\"otherUrl\":\"https://pengshan-szwhg.chaoxing.com/third/room\",\"updateUrl\":\"https://upcloudtech.cn/resource/img/banpai.apk\"}');

-- 20231130 鄞州文化馆 yinzhou
INSERT INTO `up_prod`.`up_venue_info` (`venue_name`, `is_in_use`, `creator`, `modifier`) VALUES ('鄞州文化馆', '1', '平台管理员', '平台管理员');

INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('舞蹈排练西', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('二楼培训教室一', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('培训教室二', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('培训教室四', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('二楼办公室门口', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('舞蹈排练东', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('器乐排练厅', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('共享中央课堂直播厅', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('共享小剧场', '4', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_location_info` (`location_name`, `venue_id`, `is_in_use`, `creator`, `modifier`) VALUES ('合唱排练厅', '4', '1', '平台管理员', '平台管理员');

INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500294', '舞蹈排练西门禁机', '17', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500321', '二楼培训教室一门禁机', '18', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500306', '培训教室二门禁机', '19', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500303', '培训教室四门禁机', '20', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500300', '二楼办公室门口门禁机', '21', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500307', '舞蹈排练东门禁机', '22', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500305', '器乐排练厅门禁机', '23', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2309509736', '共享中央课堂直播厅门禁机', '24', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500301', '共享小剧场门禁机', '25', '1', '1', '平台管理员', '平台管理员');
INSERT INTO `up_prod`.`up_device_info` (`device_sn`, `device_name`, `location_id`, `device_type_id`, `is_in_use`, `creator`, `modifier`) VALUES ('2310500304', '合唱排练厅门禁机', '26', '1', '1', '平台管理员', '平台管理员');
*/

-- 加用户 加管理员
