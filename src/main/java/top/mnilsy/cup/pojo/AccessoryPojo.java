package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午5:46.
 */
public class AccessoryPojo {
    private String accessory_Id;
    private String tweet_Id;
    private String accessory_Url;

    public AccessoryPojo(String tweet_Id, String accessory_Url) {
        this.accessory_Id= String.valueOf(UUID.randomUUID());
        this.tweet_Id = tweet_Id;
        this.accessory_Url = accessory_Url;
    }

    public String getAccessory_Id() {
        return accessory_Id;
    }

    public void setAccessory_Id(String accessory_Id) {
        this.accessory_Id = accessory_Id;
    }

    public String getTweet_Id() {
        return tweet_Id;
    }

    public void setTweet_Id(String tweet_Id) {
        this.tweet_Id = tweet_Id;
    }

    public String getAccessory_Url() {
        return accessory_Url;
    }

    public void setAccessory_Url(String accessory_Url) {
        this.accessory_Url = accessory_Url;
    }

    @Override
    public String toString() {
        return "AccessoryPojo{" +
                "accessory_Id='" + accessory_Id + '\'' +
                ", tweet_Id='" + tweet_Id + '\'' +
                ", accessory_Url='" + accessory_Url + '\'' +
                '}';
    }
}
