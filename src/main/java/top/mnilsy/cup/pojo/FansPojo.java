package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午7:07.
 */
public class FansPojo {
    private String fans_Id;
    private String firstParty_User_Id;
    private String secondParty_User_Id;
    private String fans_Time;
    private String fans_Condition;

    public FansPojo() {
    }

    public FansPojo(String firstParty_User_Id, String secondParty_User_Id) {
        this.fans_Id = String.valueOf(UUID.randomUUID());
        this.firstParty_User_Id = firstParty_User_Id;
        this.secondParty_User_Id = secondParty_User_Id;
    }

    public String getFans_Id() {
        return fans_Id;
    }

    public void setFans_Id(String fans_Id) {
        this.fans_Id = fans_Id;
    }

    public String getFirstParty_User_Id() {
        return firstParty_User_Id;
    }

    public void setFirstParty_User_Id(String firstParty_User_Id) {
        this.firstParty_User_Id = firstParty_User_Id;
    }

    public String getSecondParty_User_Id() {
        return secondParty_User_Id;
    }

    public void setSecondParty_User_Id(String secondParty_User_Id) {
        this.secondParty_User_Id = secondParty_User_Id;
    }

    public String getFans_Time() {
        return fans_Time;
    }

    public void setFans_Time(String fans_Time) {
        this.fans_Time = fans_Time;
    }

    public String getFans_Condition() {
        return fans_Condition;
    }

    public void setFans_Condition(String fans_Condition) {
        this.fans_Condition = fans_Condition;
    }

    @Override
    public String toString() {
        return "FansPojo{" +
                "fans_Id='" + fans_Id + '\'' +
                ", firstParty_User_Id='" + firstParty_User_Id + '\'' +
                ", secondParty_User_Id='" + secondParty_User_Id + '\'' +
                ", fans_Time='" + fans_Time + '\'' +
                ", fans_Condition='" + fans_Condition + '\'' +
                '}';
    }
}
