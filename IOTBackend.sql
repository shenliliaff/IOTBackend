-- choose database
use demo_java_api;
--
-- Basic info and RBAC tables
-- Table structure for table `up_user`
--
DROP TABLE IF EXISTS `up_user`;
CREATE TABLE `up_user` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`user_name` varchar(128) NOT NULL COMMENT '用户名称',
`venue_id` int NOT NULL COMMENT '场馆id',
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index user_name_index(`user_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户信息表';
--
-- Table structure for table `up_role`
--
DROP TABLE IF EXISTS `up_role`;
CREATE TABLE `up_role` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`role_name` varchar(128) NOT NULL COMMENT '角色名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index role_name_index(`role_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up角色信息表';
--
-- Table structure for table `up_permission`
--
DROP TABLE IF EXISTS `up_permission`;
CREATE TABLE `up_permission` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`permission_name` varchar(128) NOT NULL COMMENT '权限名称',   
`permission_type_name` varchar(64) NOT NULL COMMENT '权限类型名称',
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index permission_name_index(`permission_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up权限信息表';
--
-- Table structure for table `up_group`
--
DROP TABLE IF EXISTS `up_group`;
CREATE TABLE `up_group` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`group_name` varchar(128) NOT NULL COMMENT '用户名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index group_name_index(`group_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户组信息表';
--
-- Table structure for table `up_user_group_ref`
--
DROP TABLE IF EXISTS `up_user_group_ref`;
CREATE TABLE `up_user_group_ref` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`user_id` int NOT NULL unique COMMENT '用户id',   
`group_id` int NOT NULL unique COMMENT '用户组id', 
`user_group_ref_name` varchar(128) NOT NULL COMMENT '用户组关系名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index user_id_index(`user_id`),
index group_id_index(`group_id`)  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户组用户关系信息表';
--
-- Table structure for table `up_user_role_ref`
--
DROP TABLE IF EXISTS `up_user_role_ref`;
CREATE TABLE `up_user_role_ref` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`user_id` int NOT NULL COMMENT '用户id',
`role_id` int NOT NULL COMMENT '角色id',
`user_role_ref_name` varchar(128) NOT NULL COMMENT '用户角色关系名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index user_id_index(`user_id`),
index role_id_index(`role_id`)  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户角色关系信息表';
--
-- Table structure for table `up_role_permission_ref`
--
DROP TABLE IF EXISTS `up_role_permission_ref`;
CREATE TABLE `up_role_permission_ref` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`permission_id` int NOT NULL COMMENT '权限id',
`role_id` int NOT NULL COMMENT '角色id',
`role_permission_ref_name` varchar(128) NOT NULL COMMENT '角色权限关系名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index permission_id_index(`permission_id`),
index role_id_index(`role_id`)  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up角色权限关系信息表';
--
-- Table structure for table `up_group_role_ref`
--
DROP TABLE IF EXISTS `up_group_role_ref`;
CREATE TABLE `up_group_role_ref` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`group_id` int NOT NULL  COMMENT '用户组id',
`role_id` int NOT NULL COMMENT '角色id',
`group_role_ref_name` varchar(128) NOT NULL COMMENT '用户组关系名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index role_id_index(`role_id`) ,
index group_id_index(`group_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up用户组用户关系信息表';

-- 业务数据表
--
-- Table structure for table `up_device_info`
-- 远程操作状态，门开关(0/1)，摄像头转动，摄像头开关(0/1/2/3)，灯亮灭(0/1)
--
DROP TABLE IF EXISTS `up_device_info`;
CREATE TABLE `up_device_info` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`device_sn` varchar(128) NOT NULL unique COMMENT '设备sn',   
`device_name` varchar(128) COMMENT '设备名称',   
`location_id` varchar(128) NOT NULL COMMENT '区域id',
`device_type_id` varchar(128) NOT NULL COMMENT '设备类型id',
`remote_operation` varchar(64) NULL COMMENT '远程操作',
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index device_sn_index(`device_sn`) ,
index device_name_index(`device_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备信息表';
--
-- Table structure for table `up_device_admin_ref`
--
DROP TABLE IF EXISTS `up_device_admin_ref`;
CREATE TABLE `up_device_admin_ref` (
`id` int primary key unique NOT NULL AUTO_INCREMENT,
`device_sn`varchar(128) NOT NULL  COMMENT '设备sn',
`user_id` int NOT NULL COMMENT '角色id',
`device_admin_ref_name` varchar(128) NOT NULL COMMENT '设备管理员关系名称',
`is_in_use` tinyint NOT NULL COMMENT '是否有效',
`creator` varchar(128) NOT NULL COMMENT '创建者',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`modifier` varchar(128) NOT NULL COMMENT '用户名称',
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
`remark` varchar(255) COMMENT '描述',
index device_sn_index(`device_sn`) ,
index user_id_index(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备管理员关系信息表';
--
-- Table structure for table `up_device_log_info`
--
DROP TABLE IF EXISTS `up_device_log_info`;
CREATE TABLE `up_device_log_info` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`device_sn` varchar(128) NOT NULL COMMENT '设备sn',
`device_log_description` nvarchar(1000) NOT NULL COMMENT '设备记录描述',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index device_sn_index(`device_sn`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备记录信息表';
--
-- Table structure for table `up_location_info`
--
DROP TABLE IF EXISTS `up_location_info`;
CREATE TABLE `up_location_info` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`location_name` varchar(128) NULL COMMENT '区域名称',   
`venue_id` int NULL COMMENT '场馆id',
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index location_name_index(`location_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up区域信息表';
--
-- Table structure for table `up_venue_info`
--
DROP TABLE IF EXISTS `up_venue_info`;
CREATE TABLE `up_venue_info` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`venue_name` varchar(128) NULL COMMENT '场馆名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index venue_name_index(`venue_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up场馆信息表';
--
-- Table structure for table `up_device_key_info`
--
DROP TABLE IF EXISTS `up_device_key_info`;
CREATE TABLE `up_device_key_info` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`device_sn` varchar(128) NOT NULL COMMENT '设备sn',
`phone` varchar(128) COMMENT '钥匙持有人手机号',
`owner` varchar(128) NOT NULL COMMENT '钥匙持有人',
`start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
`end_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index device_id_index(`device_sn`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up区域信息表';
--
-- Table structure for table `up_device_type_info`
--
DROP TABLE IF EXISTS `up_device_type_info`;
CREATE TABLE `up_device_type_info` (   
`id` int primary key unique NOT NULL AUTO_INCREMENT,   
`device_type_name` varchar(128) NOT NULL COMMENT '设备类型名称',   
`is_in_use` tinyint NOT NULL COMMENT '是否有效',   
`creator` varchar(128) NOT NULL COMMENT '创建者',   
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',   
`modifier` varchar(128) NOT NULL COMMENT '用户名称',   
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',   
`remark` varchar(255) COMMENT '描述',   
index device_type_name_index(`device_type_name`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='up设备类型信息表';




