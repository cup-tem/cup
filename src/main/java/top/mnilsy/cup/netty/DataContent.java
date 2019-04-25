package top.mnilsy.cup.netty;

import top.mnilsy.cup.VO.MessageVO;

import java.io.Serializable;

/**
 * 通讯数据包
 * 通讯动作类型action
 *
 * @see top.mnilsy.cup.enums.NettyActionEnum
 * 通讯数据messageVO
 * 扩展数据extand
 */
public class DataContent implements Serializable {

    private static final long serialVersionUID = 8543830607305056646L;
    private int action;
    private Object data;
    private String extand;

    public DataContent() {
    }

    public DataContent(int action, Object data, String extand) {
        this.action = action;
        this.data = data;
        this.extand = extand;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getExtand() {
        return extand;
    }

    public void setExtand(String extand) {
        this.extand = extand;
    }

    @Override
    public String toString() {
        return "DataContent{" +
                "action=" + action +
                ", data=" + data +
                ", extand='" + extand + '\'' +
                '}';
    }
}
