package top.mnilsy.cup.service.impl;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.MessageVO;
import top.mnilsy.cup.dao.BlacklistMapper;
import top.mnilsy.cup.dao.MessageMapper;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.ChatHandler;
import top.mnilsy.cup.netty.DataContent;
import top.mnilsy.cup.netty.UserChannelRel;
import top.mnilsy.cup.service.MessageService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mnilsy on 19-4-24 上午1:57.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource(name = "messageMapper")
    private MessageMapper messageMapper;

    @Resource(name = "blacklistMapper")
    private BlacklistMapper blacklistMapper;

    @Override
    public MessageVO addMessage(MessageVO messageVO) {
        if (messageVO.getMessage_Vlue() == null || messageVO.getMessage_Vlue().trim().length() == 0) return null;
        if (blacklistMapper.isBlacklistByuserName(messageVO.getSender_Name(), messageVO.getRecipient_Name()) == 1) return null;
        messageVO.setMessage_Id(UUID.randomUUID().toString());
        messageVO.setMessage_Time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (messageMapper.insertMessage(messageVO) == 1)
            return messageVO;
        else return null;
    }

    @Override
    public boolean signfor(String message_Id) {
        if (message_Id.length() != 36) return false;
        return messageMapper.updataCondition(message_Id) == 1;
    }

    @Override
    public List<MessageVO> getNotSignfor(String user_Name) {
        if (user_Name == null) return null;
        return messageMapper.getRecipientAllCondition_0(user_Name);
    }

    @Override
    public boolean sendMessageText(MessageVO messageVO) {
        // 从全局用户Channel关系中获取接受方的channel
        ChannelId recipientChannelId = UserChannelRel.get(messageVO.getRecipient_Name());
        if (recipientChannelId != null) {
            // 当recipientChannelId不为空的时候，从ChannelGroup去查找对应的channel是否存在
            Channel findChannel = ChatHandler.users.find(recipientChannelId);
            if (findChannel != null) {
                // 用户在线
                findChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(new DataContent(NettyActionEnum.CHAT_TEXT.vule, messageVO, null))));
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
