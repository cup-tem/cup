package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午5:56.
 */
public class Writeback {
    private String writeBack_Id;
    private String discuss_Id;
    private String user_Id;
    private String writeBack_Vlue;
    private String write_Date;
    private int writeBack_Condition;

    public Writeback() {
    }

    public Writeback(String discuss_Id, String user_Id, String writeBack_Vlue) {
        this.writeBack_Id= String.valueOf(UUID.randomUUID());
        this.discuss_Id = discuss_Id;
        this.user_Id = user_Id;
        this.writeBack_Vlue = writeBack_Vlue;
    }

    public String getWriteBack_Id() {
        return writeBack_Id;
    }

    public void setWriteBack_Id(String writeBack_Id) {
        this.writeBack_Id = writeBack_Id;
    }

    public String getDiscuss_Id() {
        return discuss_Id;
    }

    public void setDiscuss_Id(String discuss_Id) {
        this.discuss_Id = discuss_Id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getWriteBack_Vlue() {
        return writeBack_Vlue;
    }

    public void setWriteBack_Vlue(String writeBack_Vlue) {
        this.writeBack_Vlue = writeBack_Vlue;
    }

    public String getWrite_Date() {
        return write_Date;
    }

    public void setWrite_Date(String write_Date) {
        this.write_Date = write_Date;
    }

    public int getWriteBack_Condition() {
        return writeBack_Condition;
    }

    public void setWriteBack_Condition(int writeBack_Condition) {
        this.writeBack_Condition = writeBack_Condition;
    }
}
