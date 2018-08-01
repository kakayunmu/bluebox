package pers.kakayunmu.bluebox.model;

import lombok.Getter;
import lombok.Setter;

public class CategoryModel {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String remarks;

    @Getter
    @Setter
    private String icon="default";

    @Getter
    @Setter
    private int parent_id;

}
