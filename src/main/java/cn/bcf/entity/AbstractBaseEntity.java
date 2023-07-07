package cn.bcf.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractBaseEntity implements Serializable {

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timeCreated;

    @Column(nullable = false)
    private LocalDateTime timeLastUpdated;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(timeCreated)) {
            timeCreated = LocalDateTime.now();
        }
        if (Objects.isNull(timeLastUpdated)) {
            timeLastUpdated = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        timeLastUpdated = LocalDateTime.now();
    }

}