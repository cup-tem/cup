package top.mnilsy.cup.util;

/**
 * Created by mnilsy on 19-4-19 上午12:52.
 * 用于接收客户端传来的信息包
 * sessionid 为客户端登录是的sessionid
 * data 为客户端传过来的数据包
 */
public class RequestMessage {
    private String sessionid;
    private Object data;

    public RequestMessage() {
    }

    public RequestMessage(String sessionid, Object data) {
        this.sessionid = sessionid;
        this.data = data;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
