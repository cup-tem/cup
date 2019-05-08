package top.mnilsy.cup.enums;

/**
 * Created by mnilsy on 19-4-23 下午3:16.
 */

/**
 * netty通讯类型
 */
public enum NettyActionEnum {
    LOGIN(1, "上线"),//extand存放用户名user_Name
    CHAT_TEXT(2, "文字私信"),//data存放私信包MessagePojoVO
    CHAT_PHOTO(3, "图片私信"),
    SIGNFOR_MESSAGE(4, "签收私信"),//extand存放私信id message_Id
    KEEPALIVE(5, "保持心跳"),
    AT(6, "@"),//data存放AtPojoVO
    SIGNFOR_AT(7, "签收@"),//extand存放@id at_Id
    ERROR(8,"失败"),
    LOGOUT(9, "下线");//extand存放用户名user_Name

    public int vule;
    public String desc;

    NettyActionEnum(int vule, String desc) {
        this.vule = vule;
        this.desc = desc;
    }
}
