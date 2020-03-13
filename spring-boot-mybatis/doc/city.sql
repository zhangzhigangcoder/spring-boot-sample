/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.68
Source Server Version : 50725
Source Host           : 192.168.2.68:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-09-18 17:38:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` varchar(32) NOT NULL,
  `city_name` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO `t_city` VALUES ('3dd3bd505447933222ce726d913235b0', '上海', '2019-09-18 17:23:29');
INSERT INTO `t_city` VALUES ('75b349a41898d2af7261b5a156b66c92', '北京', '2019-09-18 17:23:29');
INSERT INTO `t_city` VALUES ('84d1bfadfbf91c2619444c192499625a', '广州', '2019-09-18 17:23:29');
INSERT INTO `t_city` VALUES ('d1f31a6dd2499cedff4f7f467e6b3fee', '苏州', '2019-09-18 17:23:29');
INSERT INTO `t_city` VALUES ('ec976806bbf17d0cc379420131540ae5', '深圳', '2019-09-18 17:23:29');
