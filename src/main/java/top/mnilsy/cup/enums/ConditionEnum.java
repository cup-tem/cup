package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-17 下午7:30.
 */
public enum ConditionEnum {
    NORMAL(0, "正常"),
    DELETE(1, "停封"),
    SIGN_FOR(1,"签收");

    private int vlue;
    private String desc;

    ConditionEnum(int vlue, String desc) {
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
