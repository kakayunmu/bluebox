package pers.kakayunmu.bluebox.model.wechat;

import lombok.Getter;
import lombok.Setter;

public class UserInfo {
    @Getter
    @Setter
    private String nick_name;
    @Getter
    @Setter
    private String avatar_url;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String province;
    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private String language;
}
