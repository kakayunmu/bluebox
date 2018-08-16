package pers.kakayunmu.bluebox.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GoodsModel {
    private String id;
    private String imgUrl;
    private String name;
    private float price;
    private String position;
    private Set<LableModel> lables=new HashSet();
}
