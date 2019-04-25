package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-23 下午3:16.
 */

/**
 * netty通讯类型
 */
public enum NettyActionEnum {
    LOGIN(1, "上线"),
    CHAT_TEXT(2, "文字私信"),
    CHAT_PHOTO(3, "图片私信"),
    SIGN_FOR(4, "签收"),
    KEEPALIVE(5, "保持心跳");
    public int vule;
    public String desc;

    NettyActionEnum(int vule, String desc) {
        this.vule = vule;
        this.desc = desc;
    }
}
