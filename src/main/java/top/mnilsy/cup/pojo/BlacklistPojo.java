package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午7:10.
 */
public class BlacklistPojo {
    private String blackList_Id;
    private String firstParty_User_Id;
    private String secondParty_User_Id;
    private String blackList_Time;

    public BlacklistPojo() {
    }

    public BlacklistPojo(String firstParty_User_Id, String secondParty_User_Id) {
        this.blackList_Id = String.valueOf(UUID.randomUUID());
        this.firstParty_User_Id = firstParty_User_Id;
        this.secondParty_User_Id = secondParty_User_Id;
    }

    public String getBlackList_Id() {
        return blackList_Id;
    }

    public void setBlackList_Id(String blackList_Id) {
        this.blackList_Id = blackList_Id;
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

    public String getBlackList_Time() {
        return blackList_Time;
    }

    public void setBlackList_Time(String blackList_Time) {
        this.blackList_Time = blackList_Time;
    }

    @Override
    public String toString() {
        return "BlacklistPojo{" +
                "blackList_Id='" + blackList_Id + '\'' +
                ", firstParty_User_Id='" + firstParty_User_Id + '\'' +
                ", secondParty_User_Id='" + secondParty_User_Id + '\'' +
                ", blackList_Time='" + blackList_Time + '\'' +
                '}';
    }
}
