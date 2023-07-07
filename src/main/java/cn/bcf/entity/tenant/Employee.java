package cn.bcf.entity.tenant;

import cn.bcf.entity.AbstractBaseEntity;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_employee")
public class Employee extends AbstractBaseEntity {

    String username;

}
