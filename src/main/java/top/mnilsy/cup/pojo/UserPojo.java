package top.mnilsy.cup.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午1:39.
 */
public class UserPojo {
    private String user_Id;
    private String user_SignlnTime;
    private int user_Condition;
    private String user_Name;
    private String user_NickName;
    private String user_Sex;
    private String user_Phone;
    private String user_email;

    public UserPojo() {
    }

    public UserPojo(String user_Id, String user_Name, String user_NickName, String user_Sex, String user_Phone, String user_email) {
        this.user_Id = user_Id;
        this.user_Name = user_Name;
        this.user_NickName = user_NickName;
        this.user_Sex = user_Sex;
        this.user_Phone = user_Phone;
        this.user_email = user_email;
    }

    public UserPojo(String user_Phone) {
        this.user_Id= String.valueOf(UUID.randomUUID());
        this.user_Name=this.user_Id.substring(22,this.user_Id.length());
        this.user_NickName="cup"+user_Id.substring(25,user_Id.length());
        this.user_Phone = user_Phone;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_SignlnTime() {
        return user_SignlnTime;
    }

    public void setUser_SignlnTime(String user_SignlnTime) {
        this.user_SignlnTime = user_SignlnTime;
    }

    public int getUser_Condition() {
        return user_Condition;
    }

    public void setUser_Condition(int user_Condition) {
        this.user_Condition = user_Condition;
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

    @Override
    public String toString() {
        return "UserPojo{" +
                "user_Id='" + user_Id + '\'' +
                ", user_SignlnTime='" + user_SignlnTime + '\'' +
                ", user_Condition=" + user_Condition +
                ", user_Name='" + user_Name + '\'' +
                ", user_NickName='" + user_NickName + '\'' +
                ", user_Sex='" + user_Sex + '\'' +
                ", user_Phone='" + user_Phone + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}
