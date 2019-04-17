package top.mnilsy.cup.pojo;

import java.util.UUID;

/**
 * Created by mnilsy on 19-4-17 下午2:11.
 */
public class Message {
    private String message_Id;
    private String message_Vlue;
    private String message_Sender_u_Id;
    private String message_Recipient_u_Id;
    private String message_Time;
    private int message_Condition;

    public Message() {
    }

    public Message(String message_Vlue, String message_Sender_u_Id, String message_Recipient_u_Id) {
        this.message_Id= String.valueOf(UUID.randomUUID());
        this.message_Vlue = message_Vlue;
        this.message_Sender_u_Id = message_Sender_u_Id;
        this.message_Recipient_u_Id = message_Recipient_u_Id;
    }

    public String getMessage_Id() {
        return message_Id;
    }

    public void setMessage_Id(String message_Id) {
        this.message_Id = message_Id;
    }

    public String getMessage_Vlue() {
        return message_Vlue;
    }

    public void setMessage_Vlue(String message_Vlue) {
        this.message_Vlue = message_Vlue;
    }

    public String getMessage_Sender_u_Id() {
        return message_Sender_u_Id;
    }

    public void setMessage_Sender_u_Id(String message_Sender_u_Id) {
        this.message_Sender_u_Id = message_Sender_u_Id;
    }

    public String getMessage_Recipient_u_Id() {
        return message_Recipient_u_Id;
    }

    public void setMessage_Recipient_u_Id(String message_Recipient_u_Id) {
        this.message_Recipient_u_Id = message_Recipient_u_Id;
    }

    public String getMessage_Time() {
        return message_Time;
    }

    public void setMessage_Time(String message_Time) {
        this.message_Time = message_Time;
    }

    public int getMessage_Condition() {
        return message_Condition;
    }

    public void setMessage_Condition(int message_Condition) {
        this.message_Condition = message_Condition;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_Id='" + message_Id + '\'' +
                ", message_Vlue='" + message_Vlue + '\'' +
                ", message_Sender_u_Id='" + message_Sender_u_Id + '\'' +
                ", message_Recipient_u_Id='" + message_Recipient_u_Id + '\'' +
                ", message_Time='" + message_Time + '\'' +
                ", message_Condition=" + message_Condition +
                '}';
    }
}
