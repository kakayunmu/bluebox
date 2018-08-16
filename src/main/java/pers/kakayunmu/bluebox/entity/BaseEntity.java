package pers.kakayunmu.bluebox.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "guieIdStrategy")
    @GenericGenerator(name = "guieIdStrategy",strategy = "guid")
    @Column(length = 36,nullable = false)
    private String id; //主键
    @CreatedDate
    private Date createDate; //创建日期
    @CreatedBy
    private String createBy;//创建人
    @Column(length = 36)
    @LastModifiedDate
    private Date lastModifiedDate;//最后更新日期
    @LastModifiedBy
    @Column(length = 36)
    private String lastModifiedBy;//最后更新人
}
