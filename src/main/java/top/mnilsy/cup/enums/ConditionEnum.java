package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-17 下午7:30.
 */
public enum ConditionEnum {
    NORMAL(0, "正常"),
    DELETE(1, "停封"),
    SIGN_FOR(1,"签收");

    public int vlue;
    public String desc;

    ConditionEnum(int vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }
}
