package top.mnilsy.cup.util;

/**
 * Created by mnilsy on 19-4-17 下午7:30.
 */
public enum Condition {
    NORMAL(0, "正常"),
    DELETE(1, "停封");
    private int vlue;
    private String desc;

    Condition(int vlue, String desc) {
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
