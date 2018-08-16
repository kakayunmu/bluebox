package pers.kakayunmu.bluebox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    @Column(length = 36, nullable = false)
    private String name;
    @Column(length = 36)
    private String parentId;
    @Column(length = 500)
    private String remarks;
    @Column(length = 50)
    private String icon = "default";
    private int isDel = 0;
}
