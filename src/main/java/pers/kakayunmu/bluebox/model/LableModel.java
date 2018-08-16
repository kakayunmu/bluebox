package pers.kakayunmu.bluebox.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class LableModel {
    private String id="";
    private String name;//标签名称
    private String remark;//备注
    private String icon;//图标
    private Set<GoodsModel> goods = new HashSet<>(); //标签包含的物件
    private int isSys;//是否是系统添加的标签 0 系统 1 用户
}
