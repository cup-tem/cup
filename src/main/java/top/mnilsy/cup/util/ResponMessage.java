package top.mnilsy.cup.util;

/**
 * Created by mnilsy on 19-4-19 上午12:05.
 * 用于返回客户端信息包
 * statu为返回状态码
 * message为返回对象
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
        this.status = 200;
        this.data = data;
    }

    public ResponMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功，不返回数据
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
     * 请求错误，并返回错误信息
     *
     * @param message 返回的错误信息
     * @return
     */
    public static ResponMessage error(String message) {
        return new ResponMessage(500, message, null);
    }
}
