package top.mnilsy.cup.VO;

/**
 * Created by mnilsy on 19-4-27 下午6:50.
 */
public class DiscussVO {
    private String user_HeadUrl_min;
    private String user_Name;
    private String discuss_Vlue;
    private String discuss_Id;

    public DiscussVO(String user_HeadUrl_min, String user_Name, String discuss_Vlue, String discuss_Id) {
        this.user_HeadUrl_min = user_HeadUrl_min;
        this.user_Name = user_Name;
        this.discuss_Vlue = discuss_Vlue;
        this.discuss_Id = discuss_Id;
    }

    public DiscussVO() {
    }

    public String getUser_HeadUrl_min() {
        return user_HeadUrl_min;
    }

    public void setUser_HeadUrl_min(String user_HeadUrl_min) {
        this.user_HeadUrl_min = user_HeadUrl_min;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getDiscuss_Vlue() {
        return discuss_Vlue;
    }

    public void setDiscuss_Vlue(String discuss_Vlue) {
        this.discuss_Vlue = discuss_Vlue;
    }

    public String getDiscuss_Id() {
        return discuss_Id;
    }

    public void setDiscuss_Id(String discuss_Id) {
        this.discuss_Id = discuss_Id;
    }

    @Override
    public String toString() {
        return "DiscussVO{" +
                "user_HeadUrl_min='" + user_HeadUrl_min + '\'' +
                ", user_Name='" + user_Name + '\'' +
                ", discuss_Vlue='" + discuss_Vlue + '\'' +
                ", discuss_Id='" + discuss_Id + '\'' +
                '}';
    }
}
