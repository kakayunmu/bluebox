package pers.kakayunmu.bluebox.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "family")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Family {
    @Id
    @GeneratedValue(generator = "guieIdStrategy")
    @GenericGenerator(name = "guieIdStrategy",strategy = "guid")
    @Column(length = 36,nullable = false)
    private String id;
    @NotNull
    @Column(length = 50,nullable = false)
    private String name;

}
