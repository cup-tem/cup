package top.mnilsy.cup.service.impl;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.MessageVO;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.ChatHandler;
import top.mnilsy.cup.netty.DataContent;
import top.mnilsy.cup.netty.UserChannelRel;
import top.mnilsy.cup.service.MessageService;

import java.util.List;

/**
 * Created by mnilsy on 19-4-24 上午1:57.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Override
    public MessageVO addMessage(MessageVO messageVO) {
        return null;
    }

    @Override
    public boolean signfor(String message_Id) {
        return false;
    }

    @Override
    public List<MessageVO> getNotSignfor(String user_Name) {
        return null;
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
