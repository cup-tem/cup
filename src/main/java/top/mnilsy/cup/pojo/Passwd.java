package top.mnilsy.cup.pojo;

/**
 * Created by mnilsy on 19-4-17 下午2:08.
 */
public class Passwd {
    private String user_Id;
    private String passwd_Normal;
    private String passwd_Old1;
    private String passwd_Old2;
    private String passwd_Old3;
    private int passwd_Count;

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getPasswd_Normal() {
        return passwd_Normal;
    }

    public void setPasswd_Normal(String passwd_Normal) {
        this.passwd_Normal = passwd_Normal;
    }

    public String getPasswd_Old1() {
        return passwd_Old1;
    }

    public void setPasswd_Old1(String passwd_Old1) {
        this.passwd_Old1 = passwd_Old1;
    }

    public String getPasswd_Old2() {
        return passwd_Old2;
    }

    public void setPasswd_Old2(String passwd_Old2) {
        this.passwd_Old2 = passwd_Old2;
    }

    public String getPasswd_Old3() {
        return passwd_Old3;
    }

    public void setPasswd_Old3(String passwd_Old3) {
        this.passwd_Old3 = passwd_Old3;
    }

    public int getPasswd_Count() {
        return passwd_Count;
    }

    public void setPasswd_Count(int passwd_Count) {
        this.passwd_Count = passwd_Count;
    }

    @Override
    public String toString() {
        return "Passwd{" +
                "user_Id='" + user_Id + '\'' +
                ", passwd_Normal='" + passwd_Normal + '\'' +
                ", passwd_Old1='" + passwd_Old1 + '\'' +
                ", passwd_Old2='" + passwd_Old2 + '\'' +
                ", passwd_Old3='" + passwd_Old3 + '\'' +
                ", passwd_Count=" + passwd_Count +
                '}';
    }
}
