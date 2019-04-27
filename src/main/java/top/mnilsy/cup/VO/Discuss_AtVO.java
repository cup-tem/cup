package top.mnilsy.cup.VO;

/**
 * Created by mnilsy on 19-4-27 下午11:14.
 */
public class Discuss_AtVO {
    private String user_HeadUrl_min;
    private String user_Name;
    private String discuss_Time;
    private String discuss_Vlue;
    private String discuss_Id;
    private String tweet_Id;
    private String tweet_Text;

    public Discuss_AtVO() {
    }

    public Discuss_AtVO(String user_HeadUrl_min, String user_Name, String discuss_Time, String discuss_Vlue, String discuss_Id, String tweet_Id, String tweet_Text) {
        this.user_HeadUrl_min = user_HeadUrl_min;
        this.user_Name = user_Name;
        this.discuss_Time = discuss_Time;
        this.discuss_Vlue = discuss_Vlue;
        this.discuss_Id = discuss_Id;
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

    public String getDiscuss_Time() {
        return discuss_Time;
    }

    public void setDiscuss_Time(String discuss_Time) {
        this.discuss_Time = discuss_Time;
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
}
