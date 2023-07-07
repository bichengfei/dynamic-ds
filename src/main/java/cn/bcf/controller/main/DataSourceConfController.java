package cn.bcf.controller.main;

import cn.bcf.conf.tenant.TenantDataSource;
import cn.bcf.dao.main.DataSourceConfDao;
import cn.bcf.entity.main.DataSourceConf;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSort;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/main/ds")
@Tag(name = "数据源配置", description = "配置中心-数据源配置")
public class DataSourceConfController {

    @Autowired
    DataSourceConfDao dataSourceConfDao;

    @PostMapping("findAll")
    public List<DataSourceConf> findByPage() {
        return dataSourceConfDao.findAll();
    }

    @PostMapping("saveOrUpdate")
    public DataSourceConf saveOrUpdate(@RequestBody DataSourceConf request) {
        return dataSourceConfDao.save(request);
    }



}