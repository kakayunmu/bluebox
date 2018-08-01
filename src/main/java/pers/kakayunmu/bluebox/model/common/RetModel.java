package pers.kakayunmu.bluebox.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RetModel {

    @Getter @Setter private int status;
    @Getter @Setter private String message;
}
