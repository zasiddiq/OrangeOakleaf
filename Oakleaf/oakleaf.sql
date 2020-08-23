/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50649
Source Host           : localhost:3306
Source Database       : oakleaf

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2020-08-07 12:21:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hotspot
-- ----------------------------
DROP TABLE IF EXISTS `hotspot`;
CREATE TABLE `hotspot` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `hotspotName` varchar(255) DEFAULT NULL,
  `hotspotType` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `clickResult` varchar(255) DEFAULT NULL,
  `clickTime` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS unlockBook;
CREATE TABLE unlockBook (
	id bigint(50) not null auto_increment,
    username varchar(255) not null,
    bookid tinyint not null,
    primary key (id) );

DROP TABLE IF EXISTS readBook;
CREATE TABLE readBook (
	id bigint(50) not null auto_increment,
    username varchar(255) not null,
    bookid tinyint not null,
    opentime datetime DEFAULT NULL,
    closetime datetime DEFAULT NULL,
    primary key (id) );

    
    
    