package top.mnilsy.cup.service;

import top.mnilsy.cup.BO.AtBO;
import top.mnilsy.cup.VO.MessageVO;

import java.util.List;

/**
 * Created by mnilsy on 19-4-23 下午6:20.
 */
public interface MessageService {
    /**
     * 存储聊天记录
     *
     * @param messageVO 用户发送的信息
     * @return 带message_id, message_Time的聊天记录包
     */
    MessageVO addMessage(MessageVO messageVO);

    /**
     * 信息签收
     *
     * @param message_Id 信息的id
     * @return 是否签收成功
     */
    boolean signfor(String message_Id);

    /**
     * 获取所有该用户未签收的信息
     *
     * @param user_Name 接受用户的用户名
     * @return 是否发送成功
     */
    List<MessageVO> getNotSignfor(String user_Name);

    /**
     * 将文字信息推送给用户
     *
     * @param messageVO 信息包
     * @return 是否推送成功
     */
    boolean sendMessageText(MessageVO messageVO);

}
