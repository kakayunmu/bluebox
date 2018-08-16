package pers.kakayunmu.bluebox.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Member extends BaseEntity implements Serializable {

    @Column(length = 50)
    private String openid;
    @Column(length = 50)
    private String session_key;
    @Column(length = 50)
    private String name;
    private int gender;
    private String headImgUrl;
    @Column(length = 50)
    private String country;
    @Column(length = 50)
    private String province;
    @Column(length = 50)
    private String city;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;

}
