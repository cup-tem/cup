package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-17 下午5:23.
 */
public enum ResponMessageTypeEnum {
    SUCCEE(200, "成功"),
    ERROR(500, "错误"),
    NOT_LOGIN(505, "未登录"),
    FALSE(400, "否");

    public int vlue;
    public String desc;

    ResponMessageTypeEnum(int vlue, String desc) {
        this.vlue = vlue;
        this.desc = desc;
    }
}
