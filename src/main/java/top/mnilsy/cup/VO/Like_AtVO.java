package top.mnilsy.cup.VO;

/**
 * Created by mnilsy on 19-4-28 上午1:27.
 */
public class Like_AtVO {
    private String user_HeadUrl_min;
    private String user_Name;
    private String user_NickName;
    private String like_Time;
    private String tweet_Id;
    private String tweet_Text;

    public Like_AtVO() {
    }

    public Like_AtVO(String user_HeadUrl_min, String user_Name, String user_NickName, String like_Time, String tweet_Id, String tweet_Text) {
        this.user_HeadUrl_min = user_HeadUrl_min;
        this.user_Name = user_Name;
        this.user_NickName = user_NickName;
        this.like_Time = like_Time;
        this.tweet_Id = tweet_Id;
        this.tweet_Text = tweet_Text;
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

    public String getUser_NickName() {
        return user_NickName;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }

    public String getLike_Time() {
        return like_Time;
    }

    public void setLike_Time(String like_Time) {
        this.like_Time = like_Time;
    }

    public String getTweet_Id() {
        return tweet_Id;
    }

    public void setTweet_Id(String tweet_Id) {
        this.tweet_Id = tweet_Id;
    }

    public String getTweet_Text() {
        return tweet_Text;
    }

    public void setTweet_Text(String tweet_Text) {
        this.tweet_Text = tweet_Text;
    }

    @Override
    public String toString() {
        return "Like_AtVO{" +
                "user_HeadUrl_min='" + user_HeadUrl_min + '\'' +
                ", user_Name='" + user_Name + '\'' +
                ", user_NickName='" + user_NickName + '\'' +
                ", like_Time='" + like_Time + '\'' +
                ", tweet_Id='" + tweet_Id + '\'' +
                ", tweet_Text='" + tweet_Text + '\'' +
                '}';
    }
}
