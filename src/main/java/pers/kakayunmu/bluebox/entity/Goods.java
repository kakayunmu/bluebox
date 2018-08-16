package pers.kakayunmu.bluebox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "goods")
public class Goods extends BaseEntity {
    private String imgUrl;
    @Column(length = 50,nullable = false)
    private String name;
    private float price;
    private String position;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Lable> lables=new HashSet();
}
