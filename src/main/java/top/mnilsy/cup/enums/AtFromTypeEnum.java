package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-17 下午6:51.
 */
public enum AtFromTypeEnum {
    TWEET(0, "推文"),
    DISCUSS(1, "评论"),
    WRITE_BACK(2, "回复"),
    PROCLAMATION(3, "公告"),
    LIKE(4, "被赞");

    public int vlue;
    public String desc;

    AtFromTypeEnum(int vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }
}
