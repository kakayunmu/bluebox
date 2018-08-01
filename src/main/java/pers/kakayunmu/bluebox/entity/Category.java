package pers.kakayunmu.bluebox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull
    private String name;

    @Getter
    @Setter
    private int parentId;

    @Getter
    @Setter
    private String remarks;

    @Getter
    @Setter
    private String icon="default";

    @Getter
    @Setter
    private int isDel=0;
}
