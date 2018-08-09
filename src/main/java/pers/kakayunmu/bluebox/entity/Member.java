package pers.kakayunmu.bluebox.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String openid;
    private String session_key;
    private String name;
    private int gender;
    private String headImgUrl;
    private String country;
    private String province;
    private String city;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;

}
