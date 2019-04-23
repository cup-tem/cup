package top.mnilsy.cup.service;

import top.mnilsy.cup.pojoVO.MessagePojoVO;

import java.util.List;

/**
 * Created by mnilsy on 19-4-23 下午6:20.
 */
public interface MessageService {
    /**
     * 存储聊天记录
     *
     * @param messagePojoVO 用户发送的信息
     * @return 带message_id, message_Time的聊天记录包
     */
    MessagePojoVO addMessage(MessagePojoVO messagePojoVO);

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
    List<MessagePojoVO> getNotSignfor(String user_Name);

    boolean sendMessageText(MessagePojoVO messagePojoVO);
}
