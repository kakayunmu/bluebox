package pers.kakayunmu.bluebox.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String openid;
    @Getter
    @Setter
    private String session_key;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int gender;
    @Getter
    @Setter
    private String headImgUrl;
    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private String province;
    @Getter
    @Setter
    private String city;
    @ManyToOne
    @JoinColumn(name = "family_id")
    @JsonBackReference
    @Getter
    @Setter
    private Family family;

}
