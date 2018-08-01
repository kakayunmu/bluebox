package pers.kakayunmu.bluebox.model.common;

import lombok.Getter;
import lombok.Setter;

public class RetDataModel extends RetModel {
    @Getter
    @Setter
    private Object data;

    public RetDataModel(int status, String message) {
        super(status, message);
    }
    public RetDataModel(int status,String message,Object data){
        super(status,message);
        this.data=data;
    }
}
