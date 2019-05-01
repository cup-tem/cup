package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午6:45.
 */
public class LikePojo {
    private String like_Id;
    private String tweet_Id;
    private String user_Id;
    private String like_Time;
    private int like_Condition;

    public LikePojo() {
    }

    public LikePojo(String tweet_Id, String user_Id) {
        this.like_Id= String.valueOf(UUID.randomUUID());
        this.tweet_Id = tweet_Id;
        this.user_Id = user_Id;
    }

    public String getLike_Id() {
        return like_Id;
    }

    public void setLike_Id(String like_Id) {
        this.like_Id = like_Id;
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

    public String getLike_Time() {
        return like_Time;
    }

    public void setLike_Time(String like_Time) {
        this.like_Time = like_Time;
    }

    public int getLike_Condition() {
        return like_Condition;
    }

    public void setLike_Condition(int like_Condition) {
        this.like_Condition = like_Condition;
    }

    @Override
    public String toString() {
        return "LikePojo{" +
                "like_Id='" + like_Id + '\'' +
                ", tweet_Id='" + tweet_Id + '\'' +
                ", user_Id='" + user_Id + '\'' +
                ", like_Time='" + like_Time + '\'' +
                ", like_Condition=" + like_Condition +
                '}';
    }
}
