/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : oakleaf

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-07-19 16:04:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hotspot
-- ----------------------------
DROP TABLE IF EXISTS `hotspot`;
CREATE TABLE `hotspot` (
  `id` bigint(50) NOT NULL auto_increment,
  `hotspotName` varchar(255) default NULL,
  `hotspotType` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `clickTime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotspot
-- ----------------------------
INSERT INTO `hotspot` VALUES ('2', 'leaf on page 3', 'images', 'admin', '2020-07-19 15:44:34');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(50) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `userType` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '1');
INSERT INTO `user` VALUES ('2', 'Tom', '1', '2');
INSERT INTO `user` VALUES ('3', 'Amy', '1', '3');
INSERT INTO `user` VALUES ('4', 'hhboy', '123', '4');
