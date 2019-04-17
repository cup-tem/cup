package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午5:50.
 */
public class Discuss {
    private String discuss_Id;
    private String tweet_Id;
    private String user_Id;
    private String discuss_Vlue;
    private String discuss_Time;
    private int discuss_Condition;

    public Discuss() {
    }

    public Discuss(String tweet_Id, String user_Id, String discuss_Vlue) {
        this.discuss_Id = String.valueOf(UUID.randomUUID());
        this.tweet_Id = tweet_Id;
        this.user_Id = user_Id;
        this.discuss_Vlue = discuss_Vlue;
    }

    public String getDiscuss_Id() {
        return discuss_Id;
    }

    public void setDiscuss_Id(String discuss_Id) {
        this.discuss_Id = discuss_Id;
    }

    public String getTweet_Id() {
        return tweet_Id;
    }

    public void setTweet_Id(String tweet_Id) {
        this.tweet_Id = tweet_Id;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getDiscuss_Vlue() {
        return discuss_Vlue;
    }

    public void setDiscuss_Vlue(String discuss_Vlue) {
        this.discuss_Vlue = discuss_Vlue;
    }

    public String getDiscuss_Time() {
        return discuss_Time;
    }

    public void setDiscuss_Time(String discuss_Time) {
        this.discuss_Time = discuss_Time;
    }

    public int getDiscuss_Condition() {
        return discuss_Condition;
    }

    public void setDiscuss_Condition(int discuss_Condition) {
        this.discuss_Condition = discuss_Condition;
    }
}
