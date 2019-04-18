package top.mnilsy.cup.pojoOV;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午1:39.
 */
public class UserPojoOV {
    private String user_Name;
    private String user_NickName;
    private String user_Sex;
    private String user_Phone;
    private String user_email;

    public UserPojoOV() {
    }

    public UserPojoOV(String user_Name, String user_NickName, String user_Sex, String user_Phone, String user_email) {
        this.user_Name = user_Name;
        this.user_NickName = user_NickName;
        this.user_Sex = user_Sex;
        this.user_Phone = user_Phone;
        this.user_email = user_email;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_NickName() {
        return user_NickName;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }

    public String getUser_Sex() {
        return user_Sex;
    }

    public void setUser_Sex(String user_Sex) {
        this.user_Sex = user_Sex;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
