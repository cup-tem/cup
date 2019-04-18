package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午6:49.
 */
public class AtPojo {
    private String at_Id;
    private String user_Id;
    private String at_From_Id;
    private int at_From_Type;
    private String at_Time;
    private int at_Condition;

    public AtPojo() {
    }

    public AtPojo(String user_Id, String at_From_Id, int at_From_Type) {
        this.at_Id= String.valueOf(UUID.randomUUID());
        this.user_Id = user_Id;
        this.at_From_Id = at_From_Id;
        this.at_From_Type = at_From_Type;
    }

    public String getAt_Id() {
        return at_Id;
    }

    public void setAt_Id(String at_Id) {
        this.at_Id = at_Id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getAt_From_Id() {
        return at_From_Id;
    }

    public void setAt_From_Id(String at_From_Id) {
        this.at_From_Id = at_From_Id;
    }

    public int getAt_From_Type() {
        return at_From_Type;
    }

    public void setAt_From_Type(int at_From_Type) {
        this.at_From_Type = at_From_Type;
    }

    public String getAt_Time() {
        return at_Time;
    }

    public void setAt_Time(String at_Time) {
        this.at_Time = at_Time;
    }

    public int getAt_Condition() {
        return at_Condition;
    }

    public void setAt_Condition(int at_Condition) {
        this.at_Condition = at_Condition;
    }

    @Override
    public String toString() {
        return "AtPojo{" +
                "at_Id='" + at_Id + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", at_From_Id='" + at_From_Id + '\'' +
                ", at_From_Type=" + at_From_Type +
                ", at_Time='" + at_Time + '\'' +
                ", at_Condition=" + at_Condition +
                '}';
    }
}
