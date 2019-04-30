package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午2:20.
 */
public class TweetPojo {
    private String tweet_Id;
    private int tweet_Type;
    private String tweet_Text;
    private String tweet_Time;
    private String user_Id;
    private int tweet_Condition;

    public TweetPojo() {
    }

    public TweetPojo(int tweet_Type, String tweet_Text) {
        this.tweet_Id= String.valueOf(UUID.randomUUID());
        this.tweet_Type = tweet_Type;
        this.tweet_Text = tweet_Text;
    }

    public TweetPojo(int tweet_Type, String tweet_Text, String user_Id) {
        this.tweet_Id= String.valueOf(UUID.randomUUID());
        this.tweet_Type = tweet_Type;
        this.tweet_Text = tweet_Text;
        this.user_Id = user_Id;
    }

    public String getTweet_Id() {
        return tweet_Id;
    }

    public void setTweet_Id(String tweet_Id) {
        this.tweet_Id = tweet_Id;
    }

    public int getTweet_Type() {
        return tweet_Type;
    }

    public void setTweet_Type(int tweet_Type) {
        this.tweet_Type = tweet_Type;
    }

    public String getTweet_Text() {
        return tweet_Text;
    }

    public void setTweet_Text(String tweet_Text) {
        this.tweet_Text = tweet_Text;
    }

    public String getTweet_Time() {
        return tweet_Time;
    }

    public void setTweet_Time(String tweet_Time) {
        this.tweet_Time = tweet_Time;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public int getTweet_Condition() {
        return tweet_Condition;
    }

    public void setTweet_Condition(int tweet_Condition) {
        this.tweet_Condition = tweet_Condition;
    }

    @Override
    public String toString() {
        return "TweetPojo{" +
                "tweet_Id='" + tweet_Id + '\'' +
                ", tweet_Type=" + tweet_Type +
                ", tweet_Text='" + tweet_Text + '\'' +
                ", tweet_Time='" + tweet_Time + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", tweet_Condition=" + tweet_Condition +
                '}';
    }
}
