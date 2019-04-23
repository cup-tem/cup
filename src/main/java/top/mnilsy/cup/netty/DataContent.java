package top.mnilsy.cup.netty;

import top.mnilsy.cup.pojoVO.MessagePojoVO;

import java.io.Serializable;

/**
 * 通讯数据包
 * 通讯动作类型action
 *
 * @see top.mnilsy.cup.enums.NettyActionEnum
 * 通讯数据messagePojoVO
 * 扩展数据extand
 */
public class DataContent implements Serializable {
    private static final long serialVersionUID = 4999569467670932331L;

    private int action;
    private MessagePojoVO messagePojoVO;
    private String extand;

    public DataContent() {
    }

    public DataContent(int action, MessagePojoVO messagePojoVO, String extand) {
        this.action = action;
        this.messagePojoVO = messagePojoVO;
        this.extand = extand;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public MessagePojoVO getMessagePojoVO() {
        return messagePojoVO;
    }

    public void setMessagePojoVO(MessagePojoVO messagePojoVO) {
        this.messagePojoVO = messagePojoVO;
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
                ",MessagePojoVO{" +
                "sender_Name='" + messagePojoVO.getSender_Name() + '\'' +
                ", recipient_Name='" + messagePojoVO.getRecipient_Name() + '\'' +
                ", message_Vlue='" + messagePojoVO.getMessage_Vlue() + '\'' +
                ", message_Time='" + messagePojoVO.getMessage_Time() + '\'' +
                ", message_Id='" + messagePojoVO.getMessage_Id() + '\'' +
                '}' +
                ", extand='" + extand + '\'' +
                '}';
    }
}
