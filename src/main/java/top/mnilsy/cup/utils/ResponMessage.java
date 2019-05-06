package top.mnilsy.cup.utils;

import top.mnilsy.cup.enums.ResponMessageTypeEnum;

/**
 * Created by mnilsy on 19-4-19 上午12:05.
 * 用于返回客户端信息包
 * statu为返回状态码
 *
 * @see top.mnilsy.cup.enums.ResponMessageTypeEnum
 * message为返回信息
 * data为返回对象，多对象时存储Map
 */
public class ResponMessage {
    private int status;
    private String message;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponMessage() {
    }

    public ResponMessage(Object data) {
        this.status = ResponMessageTypeEnum.SUCCEE.vlue;
        this.data = data;
    }

    public ResponMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 请求成功或者判断为true
     *
     * @return
     */
    public static ResponMessage ok() {
        return new ResponMessage(null);
    }

    /**
     * 请求成功，并返回数据
     *
     * @param data 返回的数据对象
     * @return
     */
    public static ResponMessage ok(Object data) {
        return new ResponMessage(data);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static ResponMessage error() {
        return new ResponMessage(ResponMessageTypeEnum.ERROR.vlue, null, null);
    }

    /**
     * 请求失败，并返回错误信息
     *
     * @param message 返回的错误信息
     * @return
     */
    public static ResponMessage error(String message) {
        return new ResponMessage(ResponMessageTypeEnum.ERROR.vlue, message, null);
    }

    /**
     * 未登录
     *
     * @return
     */
    public static ResponMessage not_login() {
        return new ResponMessage(ResponMessageTypeEnum.NOT_LOGIN.vlue, "未登录，权限不够", null);
    }

    /**
     * 判断为false
     *
     * @return
     */
    public static ResponMessage no() {
        return new ResponMessage(ResponMessageTypeEnum.FALSE.vlue, null, null);
    }
}
