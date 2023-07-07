package cn.bcf.conf.tenant;

import cn.bcf.entity.main.DataSourceConf;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.schema.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = {"cn.bcf.dao.tenant"}, entityManagerFactoryRef = "tenantEntityManagerFactory", transactionManagerRef = "tenantTransactionManager")
public class TenantDbConfig {

    @Autowired
    @Qualifier("tenantDataSource")
    TenantDataSource tenantDataSource;

    @Autowired
    EntityManagerFactoryBuilder builder;

    @Autowired
    TenantDataSourceFactory tenantDataSourceFactory;

    private static final String packagesToScan = "cn.bcf.entity.tenant";
    private static final String persitenceUnit = "cn.bcf.entity.tenant";

    @Bean(name = "tenantEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("tenantDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages(packagesToScan)
                .persistenceUnit(persitenceUnit)
                .properties(new HashMap<String, String>(){{
                    put(AvailableSettings.HBM2DDL_AUTO, Action.NONE.getExternalHbm2ddlName());
                }})
                .build();
    }

    @Bean(name = "tenantTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("tenantEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    public Boolean updateSchema(@RequestParam int tenantId) throws Exception {
        DataSource dataSource = tenantDataSourceFactory.getDs(tenantId);
        builder.dataSource(dataSource)
                .packages(packagesToScan)
                .persistenceUnit(persitenceUnit)
                .properties(new HashMap<String, String>(){{
                    put(AvailableSettings.HBM2DDL_AUTO, Action.UPDATE.getExternalHbm2ddlName());
                }})
                .build().afterPropertiesSet();
        DruidDataSourceStatManager.removeDataSource(dataSource);
        return true;
    }

    public boolean isConnection(@RequestParam int tenantId) throws Exception {
        boolean isConn = false;
        Connection conn = null;
        DruidDataSource dataSource = null;
        try {
            dataSource = (DruidDataSource) tenantDataSourceFactory.getDs(tenantId);
            conn = dataSource.getConnection();
            isConn = conn.isValid(10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(conn)) {
                conn.close();
            }
            DruidDataSourceStatManager.removeDataSource(dataSource);
        }
        return isConn;
    }



}