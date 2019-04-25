package top.mnilsy.cup.VO;

import java.io.Serializable;

/**
 * Created by mnilsy on 19-4-23 上午1:59.
 */
public class MessageVO implements Serializable {
    private static final long serialVersionUID = -2784125080402473724L;
    private String sender_Name;
    private String recipient_Name;
    private String message_Vlue;
    private String message_Time;
    private String message_Id;

    public MessageVO() {
    }

    public MessageVO(String sender_Name, String recipient_Name, String message_Vlue, String message_Time, String message_Id) {
        this.sender_Name = sender_Name;
        this.recipient_Name = recipient_Name;
        this.message_Vlue = message_Vlue;
        this.message_Time = message_Time;
        this.message_Id = message_Id;
    }

    public String getSender_Name() {
        return sender_Name;
    }

    public void setSender_Name(String sender_Name) {
        this.sender_Name = sender_Name;
    }

    public String getRecipient_Name() {
        return recipient_Name;
    }

    public void setRecipient_Name(String recipient_Name) {
        this.recipient_Name = recipient_Name;
    }

    public String getMessage_Vlue() {
        return message_Vlue;
    }

    public void setMessage_Vlue(String message_Vlue) {
        this.message_Vlue = message_Vlue;
    }

    public String getMessage_Time() {
        return message_Time;
    }

    public void setMessage_Time(String message_Time) {
        this.message_Time = message_Time;
    }

    public String getMessage_Id() {
        return message_Id;
    }

    public void setMessage_Id(String message_Id) {
        this.message_Id = message_Id;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "sender_Name='" + sender_Name + '\'' +
                ", recipient_Name='" + recipient_Name + '\'' +
                ", message_Vlue='" + message_Vlue + '\'' +
                ", message_Time='" + message_Time + '\'' +
                ", message_Id='" + message_Id + '\'' +
                '}';
    }
}
