package pers.kakayunmu.bluebox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 标签 实体类
 */
@Entity
@Table(name = "lable")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lable extends BaseEntity {
    private String name;//标签名称
    private String remark;//备注
    private String icon;//图标
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Goods> goods = new HashSet<>(); //标签包含的物件
    private int isSys;//是否是系统添加的标签 0 系统 1 用户
    /**
     * 创建标签
     *
     * @param name   标签名称
     * @param remark 备注
     * @param icon   标签图标
     * @param isSys  是否是系统添加的标签
     */
    public Lable(String name, String remark, String icon, int isSys) {
        this.name = name;
        this.remark = remark;
        this.icon = icon;
        this.isSys = isSys;
    }
}
