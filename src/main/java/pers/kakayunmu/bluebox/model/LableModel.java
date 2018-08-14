package pers.kakayunmu.bluebox.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pers.kakayunmu.bluebox.entity.Goods;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class LableModel {
    private long id;
    private String name;//标签名称
    private String remark;//备注
    private String icon;//图标
    private Set<Goods> goods = new HashSet<>(); //标签包含的物件
    private int isSys;//是否是系统添加的标签 0 系统 1 用户
}
