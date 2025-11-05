# 医讯通 - 后端项目

基于即时通讯的在线轻问诊与健康管理平台 - 后端服务

## 项目简介

医讯通是一个基于Spring Boot的医疗问诊平台后端服务，提供用户管理、医生管理、挂号预约、诊断记录、药品管理等功能。

## 技术栈

- Spring Boot 2.3.3
- MyBatis Plus
- MySQL
- Redis
- Swagger (API文档)
- MQTT (消息推送)

## 项目结构

```
doctor/
├── src/main/java/com/xxx/modules/
│   ├── controller/     # 控制器层
│   ├── service/        # 服务层
│   ├── mapper/         # 数据访问层
│   ├── entity/         # 实体类
│   ├── config/         # 配置类
│   ├── utils/          # 工具类
│   └── ...
├── src/main/resources/
│   ├── application.properties  # 配置文件
│   └── mapper/         # MyBatis映射文件
└── pom.xml             # Maven配置
```

## 主要功能模块

- **用户管理**: 用户注册、登录、信息管理
- **医生管理**: 医生信息、科室管理
- **挂号预约**: 在线挂号、排班管理
- **诊断管理**: 诊断记录、处方管理
- **药品管理**: 药品信息、采购管理
- **好友系统**: 好友管理、消息通讯
- **评价系统**: 医生评价、评论管理

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+
- Redis 3.0+

### 运行步骤

1. 导入数据库脚本 `doctor11.sql`
2. 修改 `application.properties` 中的数据库配置
3. 运行 `mvn spring-boot:run` 或使用IDE直接运行

## API文档

启动项目后访问: http://localhost:8080/swagger-ui.html

## 分支说明

- `develop`: 开发分支
- `main`: 主分支（生产环境）

## 开发规范

- 代码提交到 `develop` 分支
- 使用有意义的commit信息
- 遵循RESTful API设计规范

## 联系方式

项目组成员

