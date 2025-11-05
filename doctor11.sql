/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : doctor11

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 16/05/2025 09:23:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm
-- ----------------------------
DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `drug_id` int(10) NULL DEFAULT NULL COMMENT '预警药品',
  `message_info` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '预警信息',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报警' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of alarm
-- ----------------------------
INSERT INTO `alarm` VALUES (6, 6, '商品药品01库存仅剩余12个,请尽快补货!', 1, '2025-05-15 19:50:05', '2025-05-15 20:03:38');
INSERT INTO `alarm` VALUES (7, 7, '商品药品02库存仅剩余19个,请尽快补货!', 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `alarm` VALUES (8, 8, '商品阿莫西林库存仅剩余14个,请尽快补货!', 1, '2025-05-15 19:58:44', '2025-05-15 20:00:25');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '轮播图' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (6, '1', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//a27c94dd-d9f9-4c11-88ce-9d3efd7db23f_back.png', 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `banner` VALUES (7, '2', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//0aeb5fa5-e05a-4b4a-87d1-efe52c78dc71_u=3895672671,438252690&fm=253&fmt=auto&app=120&f=JPEG.webp', 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `banner` VALUES (12, '22', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-01//d99d806c-bfbc-48af-8e69-77bbb3595483_9985BE45E87ADFC657E40FEE26E74DBE.png', 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `banner` VALUES (13, '22', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-11//93093f7c-0b37-48af-83b0-a6aa01bfed83_c4878adf64194ceb9c1464dc55028cd8.png', 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `banner` VALUES (14, 'xx', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-15//654a1944-497c-4254-80f1-915d28c5906f_c9710d41147342d6b601e52bcea5baa5.png', 1, '2025-05-15 20:00:49', '2025-05-15 20:00:49');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '科室' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (6, '心血管内科', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `dept` VALUES (7, '脑神经科', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `dept` VALUES (8, '外科', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `dept` VALUES (9, '内科', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `dept` VALUES (10, '中医', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `dept` VALUES (14, '妇产科', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `dept` VALUES (16, '其他', '2025-05-15 19:54:50', '2025-05-15 19:54:50');

-- ----------------------------
-- Table structure for diagnose
-- ----------------------------
DROP TABLE IF EXISTS `diagnose`;
CREATE TABLE `diagnose`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `doctor_user_id` int(10) NULL DEFAULT NULL COMMENT '医生',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '病人',
  `order_id` int(10) NULL DEFAULT NULL COMMENT '挂号编号',
  `drug_id` int(10) NULL DEFAULT NULL COMMENT '药品',
  `count` int(10) NULL DEFAULT NULL COMMENT '数量',
  `result_info` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊断结果',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '诊断' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of diagnose
-- ----------------------------
INSERT INTO `diagnose` VALUES (11, 18, 19, 14, 6, 3, '`1111', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `diagnose` VALUES (12, 16, 21, 15, 6, 5, 'xxx', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `diagnose` VALUES (13, 24, 23, 16, 8, 5, 'xxx', '2025-05-15 19:58:43', '2025-05-15 19:58:43');

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `drug_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药品名',
  `count` int(10) NULL DEFAULT NULL COMMENT '数量',
  `desc` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
  `sort_id` int(10) NULL DEFAULT NULL COMMENT '分类',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '药品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (6, '药品01', 12, '根据《中华人民共和国药品管理法》第二条关于药品的定义：本法所称药品，是指用于预防、治疗、诊断人的疾病，有目的地调节人的生理机能并规定有适应症或者功能主治、用法和用量的物质，包括中药、化学药和生物制品等。', '根据《中华人民共和国药品管理法》第二条关于药品的定义：本法所称药品，是指用于预防、治疗、诊断人的疾病，有目的地调节人的生理机能并规定有适应症或者功能主治、用法和用量的物质，包括中药、化学药和生物制品等。根据《中华人民共和国药品管理法》第二条关于药品的定义：本法所称药品，是指用于预防、治疗、诊断人的疾病，有目的地调节人的生理机能并规定有适应症或者功能主治、用法和用量的物质，包括中药、化学药和生物制品等。根据《中华人民共和国药品管理法》第二条关于药品的定义：本法所称药品，是指用于预防、治疗、诊断人的疾病，有目的地调节人的生理机能并规定有适应症或者功能主治、用法和用量的物质，包括中药、化学药和生物制品等。', 15, 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-11//3d4f7cc4-0276-4fd8-8482-8dcc255ff3e7_724E7481389E69DDFD02CF19D3C7ADB1.png', 60, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `drug` VALUES (7, '药品02', 39, '从使用对象上说：它是以人为使用对象，预防、治疗、诊断人的疾病。有目的地调节人的生理机能，有规定的适用症、用法和用量要求；', '### 详细\n从使用对象上说：它是以人为使用对象，预防、治疗、诊断人的疾病。有目的地调节人的生理机能，有规定的适用症、用法和用量要求；从使用方法上说：除外观，患者无法辨认其内在质量，许多药品需要在医生的指导下使用，而不由患者选择决定。同时，药品的使用方法、数量、时间等多种因素在很大程度上决定其使用效果，误用不仅不能“治病”，还可能“致病”，甚至危及生命安全。因此，药品是一种特殊的商品。\n1．种类复杂性：具体品种，全世界大约有20000余种，我国中药制剂约5000多种，西药制剂约4000多种，由此可见，药品的种类复杂、品种繁多。\n2．药品的医用专属性：药品不是一种独立的商品，它与医学紧密结合，相辅相成。患者只有通过医生的检查诊断，并在医生与执业药师的指导下合理用药，才能达到防止疾病、保护健康的目的。\n3．药品质量的严格性：药品直接关系到人们的身体健康甚至生命存亡，因此，其质量不得有半点马虎。我们必须确保药品的安全、有效、均一、稳定。\n另外，药品的质量还有显著的特点：它不像其他商品一样，有质量等级之分：优等品、一等品、二等品、合格品等等，都可以销售，而药品只有符合规定与不符合规定之分，只有符合规定的产品才能允许销售，否则不得销售\n### 注意事项\n从使用对象上说：它是以人为使用对象，预防、治疗、诊断人的疾病。有目的地调节人的生理机能，有规定的适用症、用法和用量要求；从使用方法上说：除外观，患者无法辨认其内在质量，许多药品需要在医生的指导下使用，而不由患者选择决定。同时，药品的使用方法、数量、时间等多种因素在很大程度上决定其使用效果，误用不仅不能“治病”，还可能“致病”，甚至危及生命安全。因此，药品是一种特殊的商品。\n1．种类复杂性：具体品种，全世界大约有20000余种，我国中药制剂约5000多种，西药制剂约4000多种，由此可见，药品的种类复杂、品种繁多。\n2．药品的医用专属性：药品不是一种独立的商品，它与医学紧密结合，相辅相成。患者只有通过医生的检查诊断，并在医生与执业药师的指导下合理用药，才能达到防止疾病、保护健康的目的。\n3．药品质量的严格性：药品直接关系到人们的身体健康甚至生命存亡，因此，其质量不得有半点马虎。我们必须确保药品的安全、有效、均一、稳定。\n另外，药品的质量还有显著的特点：它不像其他商品一样，有质量等级之分：优等品、一等品、二等品、合格品等等，都可以销售，而药品只有符合规定与不符合规定之分，只有符合规定的产品才能允许销售，否则不得销售', 16, 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-11//8ba17c6a-8f96-4f52-8cf1-b06d805759b6_724E7481389E69DDFD02CF19D3C7ADB1.png', 10, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `drug` VALUES (8, '阿莫西林', 34, '阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小', '### 详细\n阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小\n阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小\n阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小', 16, 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-15//8d41b157-47a5-49be-89ec-6f4d897dad59_524088F7E24D73C185B2F5C81B274D41.png', 20, '2025-05-15 19:58:23', '2025-05-15 19:58:23');

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `doctor_user_id` int(10) NULL DEFAULT NULL COMMENT '医生',
  `score` double NULL DEFAULT NULL COMMENT '评分',
  `comment` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of evaluate
-- ----------------------------
INSERT INTO `evaluate` VALUES (10, 19, 18, 9.5, '1', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `evaluate` VALUES (11, 21, 16, 7.5, '1323', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `evaluate` VALUES (12, 23, 24, 9, '12323', '2025-05-15 19:58:55', '2025-05-15 19:58:55');

-- ----------------------------
-- Table structure for exchange
-- ----------------------------
DROP TABLE IF EXISTS `exchange`;
CREATE TABLE `exchange`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `drug_id` int(10) NULL DEFAULT NULL COMMENT '药品',
  `count` int(10) NULL DEFAULT NULL COMMENT '数量',
  `price` double NULL DEFAULT NULL COMMENT '单价',
  `total_price` double NULL DEFAULT NULL COMMENT '总价',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '药品订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exchange
-- ----------------------------
INSERT INTO `exchange` VALUES (6, '202505114582159955', 6, 3, 60, 180, 9, '19399439411', NULL, 2, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `exchange` VALUES (7, '202505111406762623', 7, 5, 10, 50, 21, '19293949391', '北京市xxx', 3, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `exchange` VALUES (8, '202505153747259188', 8, 6, 20, 120, 23, '18283848341', '737437@qq.com', 3, '2025-05-15 19:59:34', '2025-05-15 19:59:51');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `friend_id` int(10) NULL DEFAULT NULL COMMENT '好友id',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  `new_message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '最新消息',
  `status` int(1) NULL DEFAULT NULL COMMENT '1-申请中,2-已通过，3-已拒绝，4-已忽略',
  `apply_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证消息',
  `active_add_user_id` int(10) NULL DEFAULT NULL COMMENT '主动加好友的人',
  `accept_add_user_id` int(10) NULL DEFAULT NULL COMMENT '接受加好友的人',
  `friend_type` int(1) NULL DEFAULT NULL COMMENT '1-正常 2-黑名单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '好友' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (99, 17, 16, '2025-05-15 19:50:05', '2025-05-15 19:50:05', '[文件]', 2, NULL, 17, 16, 1);
INSERT INTO `friend` VALUES (100, 16, 17, '2025-05-15 19:50:05', '2025-05-15 19:50:05', '[文件]', 2, NULL, 17, 16, 1);
INSERT INTO `friend` VALUES (101, 19, 18, '2025-05-15 19:50:05', '2025-05-15 19:50:05', '[文件]', 2, NULL, 19, 18, 1);
INSERT INTO `friend` VALUES (102, 18, 19, '2025-05-15 19:50:05', '2025-05-15 19:50:05', '[文件]', 2, NULL, 19, 18, 1);
INSERT INTO `friend` VALUES (103, 21, 16, '2025-05-15 19:50:05', '2025-05-15 19:50:05', '1232', 2, NULL, 21, 16, 1);
INSERT INTO `friend` VALUES (104, 16, 21, '2025-05-15 19:50:05', '2025-05-15 19:50:05', '1232', 2, NULL, 21, 16, 1);
INSERT INTO `friend` VALUES (105, 23, 24, '2025-05-15 20:01:14', '2025-05-15 20:01:48', '[文件]', 2, NULL, 23, 24, 1);
INSERT INTO `friend` VALUES (106, 24, 23, '2025-05-15 20:01:14', '2025-05-15 20:01:48', '[文件]', 2, NULL, 23, 24, 1);

-- ----------------------------
-- Table structure for friend_message
-- ----------------------------
DROP TABLE IF EXISTS `friend_message`;
CREATE TABLE `friend_message`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `send_user_id` int(10) NULL DEFAULT NULL COMMENT '发送方用户Id',
  `receive_user_id` int(10) NULL DEFAULT NULL COMMENT '接收方用户id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '聊天内容',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `file_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_view` int(1) NULL DEFAULT NULL COMMENT '是否已读,1-未读,2-已读',
  `strategy_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 450 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend_message
-- ----------------------------
INSERT INTO `friend_message` VALUES (428, 17, 16, '111', 1, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (429, 16, 17, '1232', 1, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (430, 16, 17, 'http://127.0.0.1:9001/file/202504057531416757_724E7481389E69DDFD02CF19D3C7ADB1.png', 2, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (431, 16, 17, 'http://127.0.0.1:9001/file/202504057596896999_1743831759680.wav', 4, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (432, 17, 16, '202504057707718663_新建 DOCX 文档.docx', 3, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', 'http://127.0.0.1:9001/file/202504057707718663_新建 DOCX 文档.docx', 1, NULL);
INSERT INTO `friend_message` VALUES (433, 19, 18, '132', 1, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (434, 19, 18, 'http://127.0.0.1:9001/file/202505016471076033_500_ChsEmVwYskmARJypAAY1xxWYLEo649.jpg', 2, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (435, 18, 19, 'http://127.0.0.1:9001/file/202505016502757544_500_ChsEmVwYskmARJypAAY1xxWYLEo649.jpg', 2, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (436, 18, 19, 'http://127.0.0.1:9001/file/202505016575434823_1746097657535.wav', 4, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (437, 19, 18, '202505016640343471_新建 DOCX 文档.docx', 3, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', 'http://127.0.0.1:9001/file/202505016640343471_新建 DOCX 文档.docx', 2, NULL);
INSERT INTO `friend_message` VALUES (438, 16, 21, '1123', 1, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (439, 21, 16, '1232', 1, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (440, 21, 16, 'http://127.0.0.1:9001/file/202505119279569786_671b359008dcca1505400304.jpg', 2, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (441, 21, 16, 'http://127.0.0.1:9001/file/202505119334847028_1746903933474.wav', 4, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (442, 21, 16, '202505119367400046_1--VM安装乌版图.docx', 3, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', 'http://127.0.0.1:9001/file/202505119367400046_1--VM安装乌版图.docx', 1, NULL);
INSERT INTO `friend_message` VALUES (443, 16, 21, '1232', 1, 1, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (444, 24, 23, '1232', 1, 1, '2025-05-15 20:01:25', '2025-05-15 20:01:25', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (445, 23, 24, '1232', 1, 1, '2025-05-15 20:01:29', '2025-05-15 20:01:29', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (446, 24, 23, 'http://127.0.0.1:9001/file/202505154927697716_500_ChsEmVwYskmARJypAAY1xxWYLEo649.jpg', 2, 1, '2025-05-15 20:01:32', '2025-05-15 20:01:32', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (447, 23, 24, 'http://127.0.0.1:9001/file/202505154990176759_1747310498989.wav', 4, 1, '2025-05-15 20:01:39', '2025-05-15 20:01:39', NULL, 1, NULL);
INSERT INTO `friend_message` VALUES (448, 24, 23, '12323', 1, 1, '2025-05-15 20:01:42', '2025-05-15 20:01:42', NULL, 2, NULL);
INSERT INTO `friend_message` VALUES (449, 23, 24, '202505155080715517_1--VM安装乌版图.docx', 3, 1, '2025-05-15 20:01:48', '2025-05-15 20:01:48', 'http://127.0.0.1:9001/file/202505155080715517_1--VM安装乌版图.docx', 1, NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `desc` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (12, '医疗知识库', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教', NULL, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `notice` VALUES (13, '发烧了怎么办', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-01//1f551bdb-c495-4d51-894e-12c63c804079_AB1780ABA3A0B7C2F704340E2BFF571C.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `notice` VALUES (14, 'xxx', '从使用对象上说：它是以人为使用对象，预防、治疗、诊断人的疾病。有目的地调节人的生理机能，有规定的适用症、用法和用量要求；从使用方法上说：除外观，患者无法辨认其内在质量，许多药品需要在医生的指导下使用，而不由患者选择决定。同时，药品的使用方法、数量、时间等多种因素在很大程度上决定其使用效果，误用不仅不能“治病”，还可能“致病”，甚至危及生命安全。因此，药品是一种特殊的商品。\n1．种类复杂性：具体品种，全世界大约有20000余种，我国中药制剂约5000多种，西药制剂约4000多种，由此可见，药品的种类复杂、品种繁多。\n2．药品的医用专属性：药品不是一种独立的商品，它与医学紧密结合，相辅相成。患者只有通过医生的检查诊断，并在医生与执业药师的指导下合理用药，才能达到防止疾病、保护健康的目的。\n3．药品质量的严格性：药品直接关系到人们的身体健康甚至生命存亡，因此，其质量不得有半点马虎。我们必须确保药品的安全、有效、均一、稳定。\n另外，药品的质量还有显著的特点：它不像其他商品一样，有质量等级之分：优等品、一等品、二等品、合格品等等，都可以销售，而药品只有符合规定与不符合规定之分，只有符合规定的产品才能允许销售，否则不得销售', '从使用对象上说：它是以人为使用对象，预防、治疗、诊断人的疾病。有目的地调节人的生理机能，有规定的适用症、用法和用量要求；从使用方法上说：除外观，患者无法辨认其内在质量，许多药品需要在医生的指导下使用，而不由患者选择决定。同时，药品的使用方法、数量、时间等多种因素在很大程度上决定其使用效果，误用不仅不能“治病”，还可能“致病”，甚至危及生命安全。因此，药品是一种特殊的商品。\n1．种类复杂性：具体品种，全世界大约有20000余种，我国中药制剂约5000多种，西药制剂约4000多种，由此可见，药品的种类复杂、品种繁多。\n2．药品的医用专属性：药品不是一种独立的商品，它与医学紧密结合，相辅相成。患者只有通过医生的检查诊断，并在医生与执业药师的指导下合理用药，才能达到防止疾病、保护健康的目的。\n3．药品质量的严格性：药品直接关系到人们的身体健康甚至生命存亡，因此，其质量不得有半点马虎。我们必须确保药品的安全、有效、均一、稳定。\n另外，药品的质量还有显著的特点：它不像其他商品一样，有质量等级之分：优等品、一等品、二等品、合格品等等，都可以销售，而药品只有符合规定与不符合规定之分，只有符合规定的产品才能允许销售，否则不得销售从使用对象上说：它是以人为使用对象，预防、治疗、诊断人的疾病。有目的地调节人的生理机能，有规定的适用症、用法和用量要求；从使用方法上说：除外观，患者无法辨认其内在质量，许多药品需要在医生的指导下使用，而不由患者选择决定。同时，药品的使用方法、数量、时间等多种因素在很大程度上决定其使用效果，误用不仅不能“治病”，还可能“致病”，甚至危及生命安全。因此，药品是一种特殊的商品。\n1．种类复杂性：具体品种，全世界大约有20000余种，我国中药制剂约5000多种，西药制剂约4000多种，由此可见，药品的种类复杂、品种繁多。\n2．药品的医用专属性：药品不是一种独立的商品，它与医学紧密结合，相辅相成。患者只有通过医生的检查诊断，并在医生与执业药师的指导下合理用药，才能达到防止疾病、保护健康的目的。\n3．药品质量的严格性：药品直接关系到人们的身体健康甚至生命存亡，因此，其质量不得有半点马虎。我们必须确保药品的安全、有效、均一、稳定。\n另外，药品的质量还有显著的特点：它不像其他商品一样，有质量等级之分：优等品、一等品、二等品、合格品等等，都可以销售，而药品只有符合规定与不符合规定之分，只有符合规定的产品才能允许销售，否则不得销售', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-11//1efaa106-f67d-46bc-82a0-bfff73852fb8_671b359008dcca1505400304.jpg', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `notice` VALUES (15, 'xxxxx', '阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小', '阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小阿莫西林（Amoxicillin）是一种有机化合物，化学式为C16H19N3O5S，是一种抗生素药物，又称之为羟氨苄青霉素，属于青霉素家族的氨基青霉素类。其为白色或类白色的结晶型粉末，稍有特异的气味和苦味，是第二代青霉素的主要品种，系广谱半合成抗生素，能抑制细菌细胞壁的合成，具有高效的广谱抗菌作用，而且毒副作用很小', NULL, '2025-05-15 20:01:04', '2025-05-15 20:01:04');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '挂号人',
  `doctor_user_id` int(10) NULL DEFAULT NULL COMMENT '医生',
  `order_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `appoint_day` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '挂号日期',
  `week_day` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '周',
  `time` int(10) NULL DEFAULT NULL COMMENT '时间段',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `price` double NULL DEFAULT NULL COMMENT '支付金额',
  `dept_id` int(10) NULL DEFAULT NULL COMMENT '科室',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  `plan_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '挂号' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (14, 19, 18, '202505016069874631', '2025-05-01', '星期四', 1, 2, 60, 7, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL);
INSERT INTO `order` VALUES (15, 21, 16, '202505118761207293', '2025-05-12', '星期一', 1, 2, 10, 6, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL);
INSERT INTO `order` VALUES (16, 23, 24, '202505152364175848', '2025-05-16', '星期五', 1, 2, 40, 9, '2025-05-15 19:57:16', '2025-05-15 19:57:16', NULL);

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '医生',
  `plan_day` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期',
  `week_day` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '周',
  `type` int(10) NULL DEFAULT NULL COMMENT '类型',
  `time` int(10) NULL DEFAULT NULL COMMENT '时间段',
  `count` int(10) NULL DEFAULT NULL COMMENT '最大人数',
  `money` double NULL DEFAULT NULL COMMENT '挂号费用',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排班' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES (20, 24, '2025-05-16', '星期五', 1, 1, 10, 40, '2025-05-15 19:56:53', '2025-05-15 19:56:53');
INSERT INTO `plan` VALUES (21, 24, '2025-05-17', '星期六', 2, 3, 4, 20, '2025-05-15 19:57:05', '2025-05-15 19:57:05');

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采购编号',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `drug_id` int(10) NULL DEFAULT NULL COMMENT '采购药品',
  `count` int(10) NULL DEFAULT NULL COMMENT '数量',
  `status` int(10) NULL DEFAULT NULL COMMENT '状态',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '申请人',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (6, '202505111280733046', 'xx', 6, 10, 2, 20, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `purchase` VALUES (7, '202505111944577561', '采购xx', 7, 20, 2, 22, '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `purchase` VALUES (8, '202505154141789213', 'xxx', 8, 20, 2, 22, '2025-05-15 20:00:14', '2025-05-15 20:00:25');

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (13, '分类1', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `sort` VALUES (14, '分类2', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `sort` VALUES (15, '分类3', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `sort` VALUES (16, '处方药', '2025-05-15 19:50:05', '2025-05-15 19:50:05');
INSERT INTO `sort` VALUES (17, 'xxx', '2025-05-15 19:58:26', '2025-05-15 19:58:26');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `sex` int(10) NULL DEFAULT NULL COMMENT '性别',
  `dept_id` int(10) NULL DEFAULT NULL COMMENT '科室',
  `major_info` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业方向',
  `desc` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
  `score` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评级',
  `user_type` int(10) NULL DEFAULT NULL COMMENT '用户类型',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  `work_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` double(10, 1) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'admin', '12345', '超管', '19394939411', 1, 1, '1', NULL, NULL, NULL, 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//327d0994-b1d4-4585-8a87-a53499d8f225_u=889593590,2151398315&fm=253&fmt=auto&app=138&f=JPEG.webp', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 0.0, NULL);
INSERT INTO `user` VALUES (7, 'zhangsan', '12345', '张三', '19399439431', 1, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//7f3e7b15-cd84-49d7-807c-b4bb0aa1058a_6041C4EBE72E2CAC15D019F8E4511E10.jpg', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 61.0, NULL);
INSERT INTO `user` VALUES (8, 'xiaowang', '12345', '王伟明', '19939439431', 1, 8, '心血管内科', '中国医学科学院阜外医院心内科主任，擅长冠心病介入治疗、心律失常射频消融治疗。曾赴美国哈佛医学院进修，发表SCI论文20余篇。对高血压、心力衰竭等心血管疾病的诊治有丰富临床经验。', '中国医学科学院阜外医院心内科主任，擅长冠心病介入治疗、心律失常射频消融治疗。曾赴美国哈佛医学院进修，发表SCI论文20余篇。对高血压、心力衰竭等心血管疾病的诊治有丰富临床经验。', '5', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//e204040f-97ca-466d-83c8-66a0c8072d76_Snipaste_2025-01-17_23-32-17.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', '10年经验', 0.0, NULL);
INSERT INTO `user` VALUES (9, 'xiaoli', '12345', '夏利', '19399439411', 1, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//da3f0b7c-fc4f-49a9-800d-7a3de6c7b8a9_u=889593590,2151398315&fm=253&fmt=auto&app=138&f=JPEG.webp', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 10921.0, 'xxxx');
INSERT INTO `user` VALUES (10, 'wudi', '12345', '无敌', '18848348341', 1, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//71193a5e-df95-4e1b-8d91-72ce12ff3ec5_u=3336438882,2456079850&fm=253&fmt=auto&app=120&f=JPEG.webp', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 858.0, NULL);
INSERT INTO `user` VALUES (11, 'tangyan', '12345', '唐嫣', '19394934931', 1, 10, '中医养生', '中医有四大经典著作，《黄帝内经》《难经》《伤寒杂病论》和《神农本草经》。其中，《黄帝内经》成书最早，内容最多，成为中国医学宝库的经典。', '### 详细信息\n中医有四大经典著作，《黄帝内经》《难经》《伤寒杂病论》和《神农本草经》。其中，《黄帝内经》成书最早，内容最多，成为中国医学宝库的经典。\n### 留学经历\n中医有四大经典著作，《黄帝内经》《难经》《伤寒杂病论》和《神农本草经》。其中，《黄帝内经》成书最早，内容最多，成为中国医学宝库的经典。\n中医有四大经典著作，《黄帝内经》《难经》《伤寒杂病论》和《神农本草经》。其中，《黄帝内经》成书最早，内容最多，成为中国医学宝库的经典。\n中医有四大经典著作，《黄帝内经》《难经》《伤寒杂病论》和《神农本草经》。其中，《黄帝内经》成书最早，内容最多，成为中国医学宝库的经典。\n中医有四大经典著作，《黄帝内经》《难经》《伤寒杂病论》和《神农本草经》。其中，《黄帝内经》成书最早，内容最多，成为中国医学宝库的经典。', '4', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-04//f2c466ef-0b51-4995-8274-07136541b0bd_Snipaste_2024-12-30_20-58-27.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', '10年经验', 0.0, NULL);
INSERT INTO `user` VALUES (12, 'dilireba', '12345', '迪丽热吧', '19394939439', 2, 6, '心血管方向', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%', '### 医生信息\n中国自古不同于西方，宋代以前，对医生的称呼较为复杂，一般根据其专科进行称呼，如食医、疾医、金疮医等。宋代始北方人对医生的尊称为大夫。大夫本是官名。三代时，天子及诸侯皆设之。分为上大夫、中大夫、下大夫3级。秦汉以来，有御史大夫、谏大夫、太中大夫、光禄大夫等名。清代文官阶自正一品至五品，亦称大夫。旧时，太医院专称大夫。加之唐末五代以后官衔泛滥，以官名称呼逐渐形成社会风气，所以，北方人尊称医生为“大夫”。为了区别于官名，将称医生为“大夫”的“大”读成dài，\n### 留学经历\n南方人对医生的尊称为郎中。郎中本是官名，即帝王侍从官的通称。其职责原为护卫、陪从，随时建议，备顾问及差遣。战国始有，秦汉治置。后世遂以侍郎、郎中、员外郎为各部要职。郎中作为医生的称呼始自宋代。尊称医生为郎中是南方方言，由唐末五代后官衔泛滥所致。\n坐堂医是在中药店中为患者诊脉看病的中医大夫。坐堂医源于汉。相传汉代名医张仲景曾作过长沙太守，每月的初一和十五他坐堂行医，并分文不取。为了纪念张仲景崇高的医德和高超的医术，后来许多中药店都冠以某某堂，并把坐在药铺里诊病的医师称为“坐堂医”。\n直至近代，医生才成为为业医生者之通称。\n日本在明治以后称呼为：医师先生、医生、老师\n美国传统性地医生被称Physician。同时，以不同专业领域被分招呼为：内科医生（Physician）和外科医生（Surgeon），Doctor。', '5', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-05//dd5ead92-3e0a-4c0c-8855-7ccf3d8f0cd6_Snipaste_2024-12-30_20-59-17.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', '10年', 0.0, NULL);
INSERT INTO `user` VALUES (13, 'xiaofeng', '12345', '小风', '13943843843', 1, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-05//5d12f41f-d61d-4a79-8932-d8ead9c1bb95_Snipaste_2025-01-17_23-32-17.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 29.0, NULL);
INSERT INTO `user` VALUES (14, 'liuyifei', '12345', '刘亦菲', '19939493943', 2, 6, '心血管方向', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任、承担部分课题研究', '### 医生信息\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任、承担部分课题研究\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任、承担部分课题研究\n### 留学经历\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任、承担部分课题研究', '5', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-04-03//ed3b26be-20f1-4f05-8f12-479b83dda742_u=2190917617,1912588452&fm=253&fmt=auto&app=138&f=JPEG.webp', '2025-05-15 19:50:05', '2025-05-15 19:50:05', '10年', 0.0, NULL);
INSERT INTO `user` VALUES (15, 'dafei', '12345', '大飞', '18384838431', 1, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-04-03//6b57a6c3-35c1-456a-8487-9b8b29b76b3c_u=2530930737,3372561126&fm=253&fmt=auto&app=120&f=JPEG.webp', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 535.0, NULL);
INSERT INTO `user` VALUES (16, 'xiaohong', '12345', '小红', '18834838483', 1, 6, '心血管方向', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教', '### 医生信息\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教\n### 留学经历\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教', NULL, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-04-05//86403212-0d3a-4f26-8965-79b288f55d57_D0162CDA37BC1AB080C5D082988F4B48.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', '10年', 0.0, NULL);
INSERT INTO `user` VALUES (17, 'xiaoye', '12345', '宵夜', '18838484831', 2, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-04-05//88292d40-fa12-46ff-84f4-e74591d23f62_login.jpg', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 646.0, NULL);
INSERT INTO `user` VALUES (18, 'hutuo', '12345', '华佗', '19939943943', 1, 7, '眼科', '医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任', '### 医生介绍\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任\n### 留学经历\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任\n医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任医生(Doctor)，钻研学习医学科学技术，挽救生命以治病为业的人，一般指临床医师。2014年调查显示月入过万者仅占6.15%。 [1]按照卫生部、卫健委、医政部有关医疗卫生管理条例的法律法规，主持医患沟通，学术讨论，新技术推广、预后分析、公众教育、护理示教、康复培训、出院教育、执行卫生防疫、计生、大病早期识别干预等法律政治责任', NULL, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-01//95f5c786-5a32-4593-81fd-8f10a96743d8_D0162CDA37BC1AB080C5D082988F4B48.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', '10年', 0.0, NULL);
INSERT INTO `user` VALUES (19, 'xiaofei', '12345', '西奥菲', '19394939432', 2, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-01//04eb07c4-8ef3-4747-80f1-1272aa29e6ba_524088F7E24D73C185B2F5C81B274D41.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 606.0, NULL);
INSERT INTO `user` VALUES (20, 'xiaolin', '12345', '小林', '18348384384', 1, NULL, NULL, NULL, NULL, NULL, 4, NULL, '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 0.0, 'xxx');
INSERT INTO `user` VALUES (21, 'xiaohie', '12345', '小黑', '19293949391', 2, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-11//0e182fd5-a002-40e4-863a-cd5aaf6ae82c_ai.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 606.0, '北京市xxx');
INSERT INTO `user` VALUES (22, 'dahai', '12345', '大海', '18384838431', 2, NULL, NULL, NULL, NULL, NULL, 4, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-11//c5147c43-a39b-4b81-8bdb-9883bc6f4c3f_ai.png', '2025-05-15 19:50:05', '2025-05-15 19:50:05', NULL, 0.0, 'xxx');
INSERT INTO `user` VALUES (23, 'zhangxiao', '12345', '张晓', '18283848341', 1, NULL, NULL, NULL, NULL, NULL, 3, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-15//36d8ff7b-0f66-4b01-839e-389e73904c68_6041C4EBE72E2CAC15D019F8E4511E10.jpg', '2025-05-15 19:54:19', '2025-05-15 19:54:19', NULL, 506.0, '737437@qq.com');
INSERT INTO `user` VALUES (24, 'xiaotang', '123456', '小唐', '18384838411', 2, 9, '内科手术', '内科学的内容包含了疾病的定义、病因、致病机转、流行病学、自然史、症状、征候、实验诊断、影像检查、鉴别诊断、诊断、治疗、预后。内科学的方法是透过病史询问或面谈后，进行理学检查，根据病史与检查所见做实验诊断与影像检查，以期在众多鉴别诊断中排除可能性较低者', '### 详细信息\n内科学的内容包含了疾病的定义、病因、致病机转、流行病学、自然史、症状、征候、实验诊断、影像检查、鉴别诊断、诊断、治疗、预后。内科学的方法是透过病史询问或面谈后，进行理学检查，根据病史与检查所见做实验诊断与影像检查，以期在众多鉴别诊断中排除可能性较低者\n内科学的内容包含了疾病的定义、病因、致病机转、流行病学、自然史、症状、征候、实验诊断、影像检查、鉴别诊断、诊断、治疗、预后。内科学的方法是透过病史询问或面谈后，进行理学检查，根据病史与检查所见做实验诊断与影像检查，以期在众多鉴别诊断中排除可能性较低者\n### 留学经历\n内科学的内容包含了疾病的定义、病因、致病机转、流行病学、自然史、症状、征候、实验诊断、影像检查、鉴别诊断、诊断、治疗、预后。内科学的方法是透过病史询问或面谈后，进行理学检查，根据病史与检查所见做实验诊断与影像检查，以期在众多鉴别诊断中排除可能性较低者\n内科学的内容包含了疾病的定义、病因、致病机转、流行病学、自然史、症状、征候、实验诊断、影像检查、鉴别诊断、诊断、治疗、预后。内科学的方法是透过病史询问或面谈后，进行理学检查，根据病史与检查所见做实验诊断与影像检查，以期在众多鉴别诊断中排除可能性较低者', NULL, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-15//6a94bd43-93a0-44eb-8093-12bb867f2295_524088F7E24D73C185B2F5C81B274D41.png', '2025-05-15 19:55:45', '2025-05-15 19:56:12', '10年', 0.0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
