package cn.bcf.entity.main;

import cn.bcf.entity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "t_ent_data_source_conf",
        indexes = {
                @Index(unique = true, name = "uqi_tenantId", columnList = "tenantId")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceConf extends AbstractBaseEntity {

    @Column(nullable = false)
    private long tenantId;

    @Column(nullable = false)
    private String driverClassName;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}