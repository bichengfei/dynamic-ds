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