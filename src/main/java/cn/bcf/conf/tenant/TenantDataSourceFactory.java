package cn.bcf.conf.tenant;

import cn.bcf.conf.TenantHolder;
import cn.bcf.dao.main.DataSourceConfDao;
import cn.bcf.entity.main.DataSourceConf;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("singleton")
public class TenantDataSourceFactory {

    @Autowired
    DataSourceConfDao dataSourceConfDao;

    @Autowired
    DataSourceProperties dataSourceProperties;

    Map<Integer, DataSource> dsMap = new ConcurrentHashMap<>();

    DataSource getDs() {
        int tenantId = TenantHolder.getTenantId();
        if (dsMap.containsKey(tenantId)) {
            return dsMap.get(tenantId);
        }
        Map<String, String> map = new HashMap<>();
        if (TenantHolder.isDefault()) {
            map.put(DruidDataSourceFactory.PROP_NAME, "默认租户数据库，不使用");
            map.put(DruidDataSourceFactory.PROP_URL, dataSourceProperties.getUrl());
            map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, dataSourceProperties.getDriverClassName());
            map.put(DruidDataSourceFactory.PROP_USERNAME, dataSourceProperties.getUsername());
            map.put(DruidDataSourceFactory.PROP_PASSWORD, dataSourceProperties.getPassword());
        } else {
            DataSourceConf conf = dataSourceConfDao.findByTenantId(tenantId).orElseThrow(() -> new RuntimeException("租户不存在"));

            map.put(DruidDataSourceFactory.PROP_NAME, String.valueOf(tenantId));
            map.put(DruidDataSourceFactory.PROP_URL, conf.getUrl());
            map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, conf.getDriverClassName());
            map.put(DruidDataSourceFactory.PROP_USERNAME, conf.getUsername());
            map.put(DruidDataSourceFactory.PROP_PASSWORD, conf.getPassword());
        }

        try {
            DataSource ds = DruidDataSourceFactory.createDataSource(map);
            dsMap.put(tenantId, ds);
        } catch (Exception e) {
            throw new  RuntimeException("租户数据库配置错误");
        }
        return dsMap.get(tenantId);
    }

    DataSource getDs(int tenantId) throws Exception {
        DataSourceConf entity = dataSourceConfDao.findByTenantId(tenantId).orElseThrow(() -> new RuntimeException("租户不存在"));
        Map<String, String> map = new HashMap<>();
        map.put(DruidDataSourceFactory.PROP_NAME, String.valueOf(tenantId));
        map.put(DruidDataSourceFactory.PROP_URL, entity.getUrl());
        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, entity.getDriverClassName());
        map.put(DruidDataSourceFactory.PROP_USERNAME, entity.getUsername());
        map.put(DruidDataSourceFactory.PROP_PASSWORD, entity.getPassword());
        return DruidDataSourceFactory.createDataSource(map);
    }


}