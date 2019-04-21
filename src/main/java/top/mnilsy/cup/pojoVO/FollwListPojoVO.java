package top.mnilsy.cup.pojoVO;

/**
 * Created by mnilsy on 19-4-21 下午10:37.
 */
public class FollwListPojoVO {
    private String user_HeadUrl_min;
    private String user_NickName;
    private String user_Name;

    public FollwListPojoVO() {
    }

    public FollwListPojoVO(String user_HeadUrl_min, String user_NickName, String user_Name) {
        this.user_HeadUrl_min = user_HeadUrl_min;
        this.user_NickName = user_NickName;
        this.user_Name = user_Name;
    }

    public String getUser_HeadUrl_min() {
        return user_HeadUrl_min;
    }

    public void setUser_HeadUrl_min(String user_HeadUrl_min) {
        this.user_HeadUrl_min = user_HeadUrl_min;
    }

    public String getUser_NickName() {
        return user_NickName;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    @Override
    public String toString() {
        return "FollwListPojoVO{" +
                "user_HeadUrl_min='" + user_HeadUrl_min + '\'' +
                ", user_NickName='" + user_NickName + '\'' +
                ", user_Name='" + user_Name + '\'' +
                '}';
    }
}
