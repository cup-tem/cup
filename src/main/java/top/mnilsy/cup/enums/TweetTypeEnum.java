package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-17 下午5:23.
 */
public enum TweetTypeEnum {
    TEXT(0, "纯文本"),
    PHOTO(1, "图片"),
    VIDEO(2, "视频");
    public int vlue;
    public String desc;

    TweetTypeEnum(int vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }
}
