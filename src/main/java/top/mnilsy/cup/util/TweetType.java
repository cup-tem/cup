package top.mnilsy.cup.util;

/**
 * Created by mnilsy on 19-4-17 下午5:23.
 */
public enum TweetType {
    TEXT(0, "纯文本"),
    PHOTO(1, "图片"),
    VIDEO(2, "视频");
    private int vlue;
    private String desc;

    public int getVlue() {
        return vlue;
    }

    public void setVlue(int vlue) {
        this.vlue = vlue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    TweetType(int vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }
}
