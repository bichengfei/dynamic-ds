# Spring Boot + JPA 动态多数据源
## 这个项目能做什么
在不重启服务的情况下，通过在租户数据库表中，添加数据库的 driverClassName、url、username、password 等 DataSource 配置，既可动态新增数据源
## 租户数据库只能从数据库读取吗
目前实现中，只有数据库版本，如需从配置文件或者配置中心读取，只需要对 `TenantDataSourceFactory.java` 中的配置获取方式进行改造，很简单的
## 这个项目能直接应用生产吗
我这里只是对思路进行了实现，生产的话需要自己做下适配及改造
## 持久层框架必须使用 Spring Data JPA 吗
目前只针对 JPA 做了实现，如果需要使用 Mybatis，需要自己对项目做下改造，思路其实是一样的。（如果需要，留下 issues，或者给我发送邮件，有时间可以增加 Mybatis 版本）
## 项目启动条件
|  框架   | 版本  |
|  ----  | ----  |
| JDK  | 20 |
| maven  |  |
| Spring Boot  | 3.1 |
| MySQL  | 8.0 |
## 演示功能
+ 
+ 创建主数据库`create database main;`
+ 
## 项目结构
```shell
.
└── src
    └── main
        ├── java
        │   └── cn
        │       └── bcf
        │           ├── DynamicDsApplication.java
        │           ├── conf
        │           │   ├── DruidConfig.java
        │           │   ├── HelpfulRunner.java
        │           │   ├── TenantHolder.java
        │           │   ├── WebConfig.java
        │           │   ├── main
        │           │   │   └── MainDbConfig.java
        │           │   └── tenant
        │           │       ├── TenantDataSource.java
        │           │       ├── TenantDataSourceFactory.java
        │           │       └── TenantDbConfig.java
        │           ├── controller
        │           │   ├── main
        │           │   │   ├── DataSourceConfController.java
        │           │   │   └── TenantDbUtilController.java
        │           │   └── tenant
        │           │       └── EmployeeController.java
        │           ├── dao
        │           │   ├── main
        │           │   │   └── DataSourceConfDao.java
        │           │   └── tenant
        │           │       └── EmployeeDao.java
        │           ├── entity
        │           │   ├── AbstractBaseEntity.java
        │           │   ├── main
        │           │   │   └── DataSourceConf.java
        │           │   └── tenant
        │           │       └── Employee.java
        │           └── interceptor
        │               └── TenantIdInterceptor.java
        └── resources
            └── application.yml
    ├── README.md
├── pom.xml
```
## 项目说明
### 前提知识
如果想看懂这个项目，需要开发者先明白如何在项目中配置多数据源，只有明白多数据源的配置原理，才能明白如何动态配置多数据源
