/*
Navicat MySQL Data Transfer

Source Server         : localtest
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-04-19 08:54:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cfg_task`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_task`;
CREATE TABLE `cfg_task` (
  `cfg_task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `task_name` varchar(64) DEFAULT NULL COMMENT 'Task Name',
  `cfg_task_type_code` varchar(64) DEFAULT NULL COMMENT 'Process Id',
  `trigger_type` varchar(2) DEFAULT NULL COMMENT 'C:cron,S:simple',
  `trigger_expr` varchar(64) DEFAULT NULL COMMENT 'Cron Expression or simple trigger(count interval)。SimpleExpression',
  `pripority` int(11) DEFAULT NULL COMMENT '1-9',
  `create_user` varchar(32) DEFAULT NULL COMMENT 'Who create the trigger',
  `task_state` varchar(2) DEFAULT NULL COMMENT 'N:normal，S:suspend',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Create Date',
  `remarks` varchar(128) DEFAULT NULL,
  `start_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'task start time',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'task end time',
  `job_expr` varchar(128) DEFAULT NULL COMMENT 'sepcifiy the job type implements',
  `job_type` varchar(32) DEFAULT NULL COMMENT 'T:doTask,M:method invoke',
  `task_params` varchar(256) DEFAULT NULL COMMENT 'parameter split by &,like name=yuan&age=14',
  `task_group` varchar(32) DEFAULT NULL COMMENT 'Task group',
  PRIMARY KEY (`cfg_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Manage the task,We use the taskId as primary,because of exis';

-- ----------------------------
-- Records of cfg_task
-- ----------------------------
INSERT INTO `cfg_task` VALUES ('2', 'testTask', null, 'C', '0/30 * * * * ?', '0', 'admin', 'S', '2018-04-16 15:56:59', 'testTask', '2018-03-09 09:12:42', '2018-03-09 09:12:42', 'com.zhouqi.task.TestTask', 'T', null, 'order-V-Analytical');
INSERT INTO `cfg_task` VALUES ('3', 'testTaskTypeG!', null, 'C', '0 0/5 * * * ? *', null, 'zhouqi', 'S', '2018-04-17 17:29:20', 'test', '2018-04-17 10:10:10', '2018-04-17 10:10:10', 'com.zhouqi.task.TestGTask', 'C', null, 'order-V-Analytical');
INSERT INTO `cfg_task` VALUES ('4', 'testTaskTypeT!', null, 'C', '0 0/5 * * * ? *', null, 'zhouqi', 'S', '2018-04-18 11:52:29', 'test', '2018-04-17 10:10:10', '2018-04-17 10:10:10', 'com.zhouqi.task.TestTTask', 'T', null, 'order-V-Analytical');

-- ----------------------------
-- Table structure for `cfg_task_detail`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_task_detail`;
CREATE TABLE `cfg_task_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(20) DEFAULT NULL COMMENT 'cfg_task表id',
  `task_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `trigger_exp` varchar(128) DEFAULT NULL COMMENT '执行的类',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `remark` varchar(256) DEFAULT NULL COMMENT '描述',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
  `modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of cfg_task_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `cfg_task_log`
-- ----------------------------
DROP TABLE IF EXISTS `cfg_task_log`;
CREATE TABLE `cfg_task_log` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cfg_task_id` bigint(20) DEFAULT NULL COMMENT 'CFG_TASK_ID',
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'when start execute ',
  `finish_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'when complete task',
  `state` varchar(2) DEFAULT NULL COMMENT 'E:Exception,N:normal',
  `content` varchar(1024) DEFAULT NULL COMMENT 'The return content',
  `cfg_task_detail_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务日志表';

-- ----------------------------
-- Records of cfg_task_log
-- ----------------------------
INSERT INTO `cfg_task_log` VALUES ('1', '3', '2018-04-17 10:24:00', '2018-04-17 10:24:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('2', '3', '2018-04-17 10:24:30', '2018-04-17 10:24:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('3', '3', '2018-04-17 10:25:00', '2018-04-17 10:25:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('4', '3', '2018-04-17 10:25:30', '2018-04-17 10:25:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('5', '3', '2018-04-17 10:26:00', '2018-04-17 10:26:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('6', '3', '2018-04-17 10:26:30', '2018-04-17 10:26:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('7', '3', '2018-04-17 10:27:00', '2018-04-17 10:27:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('8', '3', '2018-04-17 10:27:30', '2018-04-17 10:27:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('9', '3', '2018-04-17 10:28:00', '2018-04-17 10:28:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('10', '3', '2018-04-17 10:28:30', '2018-04-17 10:28:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('11', '3', '2018-04-17 10:29:00', '2018-04-17 10:29:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('12', '3', '2018-04-17 10:29:30', '2018-04-17 10:29:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('13', '3', '2018-04-17 10:31:23', '2018-04-17 10:31:23', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('14', '3', '2018-04-17 10:31:30', '2018-04-17 10:31:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('15', '3', '2018-04-17 10:32:00', '2018-04-17 10:32:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('16', '3', '2018-04-17 10:32:30', '2018-04-17 10:32:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('17', '3', '2018-04-17 10:33:00', '2018-04-17 10:33:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('18', '3', '2018-04-17 10:33:30', '2018-04-17 10:33:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('19', '3', '2018-04-17 10:34:00', '2018-04-17 10:34:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('20', '3', '2018-04-17 10:34:30', '2018-04-17 10:34:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('21', '3', '2018-04-17 10:35:00', '2018-04-17 10:35:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('22', '3', '2018-04-17 10:42:00', '2018-04-17 10:42:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('23', '3', '2018-04-17 10:42:30', '2018-04-17 10:42:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('24', '3', '2018-04-17 10:43:00', '2018-04-17 10:43:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('25', '3', '2018-04-17 10:43:30', '2018-04-17 10:43:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('26', '3', '2018-04-17 10:44:00', '2018-04-17 10:44:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('27', '3', '2018-04-17 10:44:30', '2018-04-17 10:44:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('28', '3', '2018-04-17 10:45:00', '2018-04-17 10:45:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('29', '3', '2018-04-17 10:45:30', '2018-04-17 10:45:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('30', '3', '2018-04-17 10:46:00', '2018-04-17 10:46:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('31', '3', '2018-04-17 10:46:30', '2018-04-17 10:46:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('32', '3', '2018-04-17 10:47:00', '2018-04-17 10:47:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('33', '3', '2018-04-17 10:47:30', '2018-04-17 10:47:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('34', '3', '2018-04-17 10:48:00', '2018-04-17 10:48:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('35', '3', '2018-04-17 10:48:30', '2018-04-17 10:48:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('36', '3', '2018-04-17 10:49:00', '2018-04-17 10:49:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('37', '3', '2018-04-17 10:49:30', '2018-04-17 10:49:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('38', '3', '2018-04-17 10:50:00', '2018-04-17 10:50:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('39', '3', '2018-04-17 10:50:30', '2018-04-17 10:50:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('40', '3', '2018-04-17 10:51:00', '2018-04-17 10:51:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('41', '3', '2018-04-17 10:51:30', '2018-04-17 10:51:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('42', '3', '2018-04-17 10:52:00', '2018-04-17 10:52:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('43', '3', '2018-04-17 10:52:30', '2018-04-17 10:52:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('44', '3', '2018-04-17 10:53:00', '2018-04-17 10:53:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('45', '3', '2018-04-17 10:53:30', '2018-04-17 10:53:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('46', '3', '2018-04-17 10:54:00', '2018-04-17 10:54:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('47', '3', '2018-04-17 10:54:30', '2018-04-17 10:54:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('48', '3', '2018-04-17 10:55:00', '2018-04-17 10:55:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('49', '3', '2018-04-17 10:55:30', '2018-04-17 10:55:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('50', '3', '2018-04-17 10:56:00', '2018-04-17 10:56:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('51', '3', '2018-04-17 10:56:30', '2018-04-17 10:56:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('52', '3', '2018-04-17 10:57:00', '2018-04-17 10:57:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('53', '3', '2018-04-17 10:57:30', '2018-04-17 10:57:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('54', '3', '2018-04-17 10:58:00', '2018-04-17 10:58:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('55', '3', '2018-04-17 10:58:30', '2018-04-17 10:58:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('56', '3', '2018-04-17 10:59:00', '2018-04-17 10:59:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('57', '3', '2018-04-17 10:59:30', '2018-04-17 10:59:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('58', '3', '2018-04-17 11:00:00', '2018-04-17 11:00:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('59', '3', '2018-04-17 11:00:30', '2018-04-17 11:00:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('60', '3', '2018-04-17 11:01:00', '2018-04-17 11:01:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('61', '3', '2018-04-17 11:01:30', '2018-04-17 11:01:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('62', '3', '2018-04-17 11:02:00', '2018-04-17 11:02:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('63', '3', '2018-04-17 11:02:30', '2018-04-17 11:02:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('64', '3', '2018-04-17 11:03:00', '2018-04-17 11:03:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('65', '3', '2018-04-17 11:03:30', '2018-04-17 11:03:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('66', '3', '2018-04-17 11:04:00', '2018-04-17 11:04:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('67', '3', '2018-04-17 11:04:30', '2018-04-17 11:04:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('68', '3', '2018-04-17 11:05:00', '2018-04-17 11:05:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('69', '3', '2018-04-17 11:05:30', '2018-04-17 11:05:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('70', '3', '2018-04-17 11:06:00', '2018-04-17 11:06:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('71', '3', '2018-04-17 11:06:30', '2018-04-17 11:06:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('72', '3', '2018-04-17 11:07:00', '2018-04-17 11:07:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('73', '3', '2018-04-17 11:07:30', '2018-04-17 11:07:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('74', '3', '2018-04-17 11:08:00', '2018-04-17 11:08:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('75', '3', '2018-04-17 11:08:30', '2018-04-17 11:08:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('76', '3', '2018-04-17 11:09:00', '2018-04-17 11:09:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('77', '3', '2018-04-17 11:09:30', '2018-04-17 11:09:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('78', '3', '2018-04-17 11:10:00', '2018-04-17 11:10:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('79', '3', '2018-04-17 11:10:30', '2018-04-17 11:10:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('80', '3', '2018-04-17 11:11:00', '2018-04-17 11:11:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('81', '3', '2018-04-17 11:11:30', '2018-04-17 11:11:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('82', '3', '2018-04-17 11:12:00', '2018-04-17 11:12:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('83', '3', '2018-04-17 11:12:30', '2018-04-17 11:12:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('84', '3', '2018-04-17 11:13:00', '2018-04-17 11:13:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('85', '3', '2018-04-17 11:13:30', '2018-04-17 11:13:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('86', '3', '2018-04-17 11:14:00', '2018-04-17 11:14:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('87', '3', '2018-04-17 11:14:30', '2018-04-17 11:14:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('88', '3', '2018-04-17 11:15:00', '2018-04-17 11:15:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('89', '3', '2018-04-17 11:15:30', '2018-04-17 11:15:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('90', '3', '2018-04-17 11:16:00', '2018-04-17 11:16:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('91', '3', '2018-04-17 11:16:30', '2018-04-17 11:16:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('92', '3', '2018-04-17 11:17:00', '2018-04-17 11:17:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('93', '3', '2018-04-17 11:17:30', '2018-04-17 11:17:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('94', '3', '2018-04-17 11:18:00', '2018-04-17 11:18:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('95', '3', '2018-04-17 11:18:30', '2018-04-17 11:18:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('96', '3', '2018-04-17 11:19:00', '2018-04-17 11:19:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('97', '3', '2018-04-17 11:19:30', '2018-04-17 11:19:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('98', '3', '2018-04-17 11:20:00', '2018-04-17 11:20:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('99', '3', '2018-04-17 11:20:30', '2018-04-17 11:20:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('100', '3', '2018-04-17 11:21:00', '2018-04-17 11:21:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('101', '3', '2018-04-17 11:21:30', '2018-04-17 11:21:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('102', '3', '2018-04-17 11:22:00', '2018-04-17 11:22:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('103', '3', '2018-04-17 11:22:30', '2018-04-17 11:22:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('104', '3', '2018-04-17 11:23:00', '2018-04-17 11:23:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('105', '3', '2018-04-17 11:23:30', '2018-04-17 11:23:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('106', '3', '2018-04-17 11:24:00', '2018-04-17 11:24:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('107', '3', '2018-04-17 11:24:30', '2018-04-17 11:24:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('108', '3', '2018-04-17 11:25:00', '2018-04-17 11:25:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('109', '3', '2018-04-17 11:25:30', '2018-04-17 11:25:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('110', '3', '2018-04-17 11:26:00', '2018-04-17 11:26:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('111', '3', '2018-04-17 11:26:30', '2018-04-17 11:26:30', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('112', '3', '2018-04-17 11:45:00', '2018-04-17 11:45:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('113', '3', '2018-04-17 11:50:16', '2018-04-17 11:50:36', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('114', '3', '2018-04-17 11:55:08', '2018-04-17 11:55:08', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('115', '3', '2018-04-17 14:47:11', '2018-04-17 14:47:11', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('116', '3', '2018-04-17 14:50:03', '2018-04-17 14:50:03', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('117', '3', '2018-04-17 14:55:05', '2018-04-17 14:55:05', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('118', '3', '2018-04-17 15:05:06', '2018-04-17 15:05:07', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('119', '3', '2018-04-17 15:10:05', '2018-04-17 15:10:06', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('120', '3', '2018-04-17 15:15:03', '2018-04-17 15:15:04', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('121', '3', '2018-04-17 15:30:02', '2018-04-17 15:30:03', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('122', '3', '2018-04-17 15:35:02', '2018-04-17 15:35:03', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('123', '3', '2018-04-17 15:40:03', '2018-04-17 15:40:04', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('124', '4', '2018-04-17 17:40:05', '2018-04-17 17:40:05', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('125', '4', '2018-04-18 10:35:00', '2018-04-18 10:35:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('126', '4', '2018-04-18 10:40:04', '2018-04-18 10:40:05', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('127', '4', '2018-04-18 10:45:00', '2018-04-18 10:45:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('128', '4', '2018-04-18 11:35:17', '2018-04-18 11:35:17', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('129', '4', '2018-04-18 11:40:00', '2018-04-18 11:40:00', 'R', null, null);
INSERT INTO `cfg_task_log` VALUES ('130', '4', '2018-04-18 11:52:19', '2018-04-18 11:52:19', 'R', null, null);

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('tms-job-scheduler-name-dev', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('tms-job-scheduler-name-dev', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('tms-job-scheduler-name-dev', 'wh-code-pc01841524023539307', '1524023569434', '15000');

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='job设置表';

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
