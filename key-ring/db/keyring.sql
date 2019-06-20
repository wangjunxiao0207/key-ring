/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : keyring

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 19/06/2019 23:52:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for key
-- ----------------------------
DROP TABLE IF EXISTS `key`;
CREATE TABLE `key`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码',
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '账号标签',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码加密的盐值',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of key
-- ----------------------------
INSERT INTO `key` VALUES (4, 'wjx13579947882dfsd', 'dsfsadfsa', 'sdfsfas', 'sdfsd', 1, 'wjx', 'wjx', '2019-06-18 17:32:43', '2019-06-18 17:39:43');
INSERT INTO `key` VALUES (5, 'wjx13579947882', '123', '微信', '123', 7, 'wjx', 'wjx', '2019-06-18 17:32:44', '2019-06-18 17:32:44');
INSERT INTO `key` VALUES (6, '哈哈', '电风扇电风扇', '哈哈', '123', 7, 'wjx', 'wjx', '2019-06-19 11:58:04', '2019-06-19 11:58:04');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '操作类型，0：添加，1：删除，2：修改，3：查找, 4：复原',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '请求方法',
  `old_value` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '旧值',
  `new_value` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '新值',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 0, '/sys/user/add.json', NULL, '{\"id\":10,\"name\":\"dfsddfsddfsd\",\"telephone\":null,\"mail\":null,\"deptId\":null,\"status\":1,\"remark\":\"dsfs\",\"operator\":\"wjx\",\"createTime\":null,\"updateTime\":null}', 'wjx', '0:0:0:0:0:0:0:1', '2019-06-19 13:29:27', '2019-06-19 13:29:27');
INSERT INTO `sys_log` VALUES (2, 1, '/sys/user/delete.json', '8,9,10', NULL, 'wjx', '0:0:0:0:0:0:0:1', '2019-06-19 13:30:18', '2019-06-19 13:30:18');
INSERT INTO `sys_log` VALUES (3, 3, '/sys/user/get.json', NULL, NULL, 'wjx', '0:0:0:0:0:0:0:1', '2019-06-19 13:30:37', '2019-06-19 13:30:37');
INSERT INTO `sys_log` VALUES (4, 3, '/sys/user/list.json', NULL, '{\"id\":null,\"name\":null,\"telephone\":null,\"mail\":null,\"deptId\":null,\"status\":0,\"remark\":null,\"operator\":null,\"createTime\":null,\"updateTime\":null}', 'wjx', '0:0:0:0:0:0:0:1', '2019-06-19 13:32:08', '2019-06-19 13:32:08');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名称',
  `telephone` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号',
  `mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户所在部门的id',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态，1：正常，0：冻结状态，2：删除',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '操作者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '', 'dfsdafsadfsdfsad', '', 0, 1, NULL, 'test', '2019-06-18 09:42:09', '2019-06-18 10:52:02');
INSERT INTO `sys_user` VALUES (6, 'dfsdaf', '', '', '198C96EE2F7D196C5265CE9C5F184963', 0, 0, 'sdfsdfs', 'test', '2019-06-18 10:15:11', '2019-06-18 10:15:11');
INSERT INTO `sys_user` VALUES (7, 'wjx', '13579947882', '13579947882@163.com', '8A635C6E45314634E6AD80F71E66F302', 0, 0, '', 'test', '2019-06-18 11:42:03', '2019-06-18 11:42:03');

SET FOREIGN_KEY_CHECKS = 1;
