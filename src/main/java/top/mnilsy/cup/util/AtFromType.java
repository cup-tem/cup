package top.mnilsy.cup.util;

/**
 * Created by mnilsy on 19-4-17 下午6:51.
 */
public enum AtFromType {
    TWEET(0, "推文"),
    DISCUSS(1, "评论"),
    WRITE_BACK(2, "回复"),
    PROCLAMATION(3, "公告"),
    LIKE(4, "被赞");

    private int vlue;
    private String desc;

    AtFromType(int vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }

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
}
