package pers.kakayunmu.bluebox.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
public class GlobalParamValue<T> {

    @Getter
    @Setter
    private T parame;
    @Getter
    @Setter
    private Date expiryTime;
    @Getter
    @Setter
    private int expiryMin;

}
