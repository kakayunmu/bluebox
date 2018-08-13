package pers.kakayunmu.bluebox.model.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
public class GlobalParamValue<T> implements Serializable {
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
