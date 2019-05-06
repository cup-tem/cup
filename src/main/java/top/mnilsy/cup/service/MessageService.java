package top.mnilsy.cup.service;

import top.mnilsy.cup.VO.MessageVO;

import java.util.List;

/**
 * Created by mnilsy on 19-4-23 下午6:20.
 */
public interface MessageService {
    /**
     * 判断聊天是否可被允许，存储聊天记录
     *
     * @param messageVO 用户发送的信息,带发送者id，接受者id，聊天内容
     * @return 带message_id, message_Time的聊天记录包
     * @author mnilsy
     */
    MessageVO addMessage(MessageVO messageVO);

    /**
     * 信息签收
     *
     * @param message_Id 信息的id
     * @return 是否签收成功
     * @author mnilsy
     */
    boolean signfor(String message_Id);

    /**
     * 获取所有该用户未签收的信息
     *
     * @param user_Name 接受用户的用户名
     * @return 是否发送成功
     * @author mnilsy
     */
    List<MessageVO> getNotSignfor(String user_Name);

    /**
     * 将文字信息推送给用户
     *
     * @param messageVO 信息包
     * @return 是否推送成功
     * @author mnilsy
     */
    boolean sendMessageText(MessageVO messageVO);

}
