package top.mnilsy.cup.VO;

/**
 * Created by mnilsy on 19-4-28 上午12:04.
 */
public class Writeback_AtVO {
    private String user_HeadUrl_min;
    private String user_Name;
    private String writeBack_Time;
    private String writeBack_Vlue;
    private String writeBack_User_Id;
    private String discuss_Id;
    private String discuss_Vlue;

    public Writeback_AtVO() {
    }

    public Writeback_AtVO(String user_HeadUrl_min, String user_Name, String writeBack_Time, String writeBack_Vlue, String writeBack_User_Id, String discuss_Id, String discuss_Vlue) {
        this.user_HeadUrl_min = user_HeadUrl_min;
        this.user_Name = user_Name;
        this.writeBack_Time = writeBack_Time;
        this.writeBack_Vlue = writeBack_Vlue;
        this.writeBack_User_Id = writeBack_User_Id;
        this.discuss_Id = discuss_Id;
        this.discuss_Vlue = discuss_Vlue;
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

    public String getWriteBack_Time() {
        return writeBack_Time;
    }

    public void setWriteBack_Time(String writeBack_Time) {
        this.writeBack_Time = writeBack_Time;
    }

    public String getWriteBack_Vlue() {
        return writeBack_Vlue;
    }

    public void setWriteBack_Vlue(String writeBack_Vlue) {
        this.writeBack_Vlue = writeBack_Vlue;
    }

    public String getWriteBack_User_Id() {
        return writeBack_User_Id;
    }

    public void setWriteBack_User_Id(String writeBack_User_Id) {
        this.writeBack_User_Id = writeBack_User_Id;
    }

    public String getDiscuss_Id() {
        return discuss_Id;
    }

    public void setDiscuss_Id(String discuss_Id) {
        this.discuss_Id = discuss_Id;
    }

    public String getDiscuss_Vlue() {
        return discuss_Vlue;
    }

    public void setDiscuss_Vlue(String discuss_Vlue) {
        this.discuss_Vlue = discuss_Vlue;
    }
}
