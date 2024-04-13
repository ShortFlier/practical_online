/*
 Navicat Premium Data Transfer

 Source Server         : thesqlbase
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : practice_online

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 13/04/2024 21:55:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `account` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '账号',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fill_in_the_blank
-- ----------------------------
DROP TABLE IF EXISTS `fill_in_the_blank`;
CREATE TABLE `fill_in_the_blank`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '每个答案以“#answer#”结尾',
  `analyse` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `audit_state` int NOT NULL,
  `audit_time` datetime(0) NULL DEFAULT NULL,
  `subject_id` bigint NULL DEFAULT NULL,
  `difficulty` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for judgment
-- ----------------------------
DROP TABLE IF EXISTS `judgment`;
CREATE TABLE `judgment`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `answer` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '0错，1对',
  `analyse` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `audit_state` int NOT NULL,
  `audit_time` datetime(0) NULL DEFAULT NULL,
  `subject_id` bigint NULL DEFAULT NULL,
  `difficulty` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for multiple_choices
-- ----------------------------
DROP TABLE IF EXISTS `multiple_choices`;
CREATE TABLE `multiple_choices`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `option_a` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_b` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_c` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_d` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_e` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_f` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `answer` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `analyse` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `audit_state` int NOT NULL,
  `audit_time` datetime(0) NULL DEFAULT NULL,
  `subject_id` bigint NULL DEFAULT NULL,
  `difficulty` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `duration` int NOT NULL COMMENT '考试时间\r\n单位：分钟\r\n考试时间\r\n单位：分钟\r\n考试时间\r\n单位：分钟\r\n',
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `subject_id` bigint NOT NULL,
  `total_marks` int NOT NULL,
  `difficulty` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '难度，1-5',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for paper_detail
-- ----------------------------
DROP TABLE IF EXISTS `paper_detail`;
CREATE TABLE `paper_detail`  (
  `paper_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `radio_marks` int NOT NULL COMMENT '单选总分',
  `radio_ids` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT '单选id列表，使用\"#id#\"分隔',
  `mul_marks` int NOT NULL COMMENT '多选分数',
  `mul_ids` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT '多选ids',
  `judg_marks` int NOT NULL COMMENT '判断分数',
  `judg_ids` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT '判断ids',
  `fitb_marks` int NOT NULL COMMENT '填空分数',
  `fitb_ids` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT '填空ids',
  `voc_marks` int NOT NULL COMMENT '应用题分数',
  `voc_ids` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT '应用题ids',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`paper_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for practice
-- ----------------------------
DROP TABLE IF EXISTS `practice`;
CREATE TABLE `practice`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `topic_id` bigint NOT NULL,
  `submit_id` bigint NOT NULL,
  `submit_answer` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `grade` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for radioes
-- ----------------------------
DROP TABLE IF EXISTS `radioes`;
CREATE TABLE `radioes`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `option_a` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_b` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_c` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `option_d` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `answer` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '小写a,b,c,d',
  `analyse` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `audit_state` int NOT NULL DEFAULT 0,
  `audit_time` datetime(0) NULL DEFAULT NULL,
  `subject_id` bigint NULL DEFAULT NULL,
  `difficulty` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '数字1-5表示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `stu_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`stu_id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '创建人',
  `audit_state` int NOT NULL DEFAULT 0 COMMENT '-1不通过，\r\n0待审核，\r\n1通过\r\n',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `th_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`th_id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for topic_excel
-- ----------------------------
DROP TABLE IF EXISTS `topic_excel`;
CREATE TABLE `topic_excel`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `error_msg` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL COMMENT '出错信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vocabulary_qst
-- ----------------------------
DROP TABLE IF EXISTS `vocabulary_qst`;
CREATE TABLE `vocabulary_qst`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `answer` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '每个答案以“#answer#”结尾',
  `analyse` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `launcher` varchar(25) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `audit_state` int NOT NULL,
  `audit_time` datetime(0) NULL DEFAULT NULL,
  `subject_id` bigint NULL DEFAULT NULL,
  `difficulty` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
