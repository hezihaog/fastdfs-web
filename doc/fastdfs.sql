/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : fastdfs

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 02/09/2020 19:00:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for creditor_info
-- ----------------------------
DROP TABLE IF EXISTS `creditor_info`;
CREATE TABLE `creditor_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `real_name` varchar(35) DEFAULT NULL COMMENT '债权借款人姓名',
  `id_card` varchar(18) DEFAULT NULL COMMENT '债权借款人身份证',
  `address` varchar(150) DEFAULT NULL COMMENT '债权借款人地址',
  `sex` int(1) DEFAULT NULL COMMENT '1男2女',
  `phone` varchar(11) DEFAULT NULL COMMENT '债权借款人电话',
  `money` decimal(10,2) DEFAULT NULL COMMENT '债权借款人借款金额',
  `group_name` varchar(10) DEFAULT NULL COMMENT '债权合同所在组',
  `remote_file_path` varchar(150) DEFAULT NULL COMMENT '债权合同所在路径',
  `old_file_name` varchar(255) DEFAULT NULL COMMENT '文件上传前的名字，下载时命名回文件名字',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小，用于记录下载进度',
  `file_type` varchar(255) DEFAULT NULL COMMENT '文件类型，用于显示文件类型相同的图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of creditor_info
-- ----------------------------
BEGIN;
INSERT INTO `creditor_info` VALUES (1, '张三', '1234567890', '北京', 1, '13700000000', 12000.00, 'group1', 'M00/00/00/wKjTg19PeH-AGw7yAAArzqtox3g049.sql', 'hchat.sql', 11214, 'application/octet-stream');
INSERT INTO `creditor_info` VALUES (2, '李四', '0987654321', '上海', 2, '13898765432', 20000.00, '', '', '', 0, '');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
