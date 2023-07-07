package cn.bcf.dao.main;

import cn.bcf.entity.main.DataSourceConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourceConfDao extends JpaRepository<DataSourceConf, Long>, JpaSpecificationExecutor<DataSourceConf> {

    Optional<DataSourceConf> findByTenantId(long tenantId);
}