package top.mnilsy.cup.service.impl;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import top.mnilsy.cup.BO.AtBO;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.dao.AtMapper;
import top.mnilsy.cup.dao.TweetMapper;
import top.mnilsy.cup.enums.AtFromTypeEnum;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.ChatHandler;
import top.mnilsy.cup.netty.DataContent;
import top.mnilsy.cup.netty.UserChannelRel;
import top.mnilsy.cup.pojo.AtPojo;
import top.mnilsy.cup.service.AtService;

import javax.annotation.Resource;

/**
 * Created by mnilsy on 19-4-25 上午1:37.
 */
public class AtServiceImpl implements AtService {
    @Resource(name = "tweetMapper")
    private TweetMapper tweetMapper;

    @Resource(name = "atMapper")
    private AtMapper atMapper;

    @Override
    public boolean tweetAt(String at_From_Id, String user_Id) {
        //组装AtPojo，存库
        AtPojo atPojo = new AtPojo(user_Id, at_From_Id, AtFromTypeEnum.TWEET.vlue);
        if (!atMapper.addAt(atPojo))
            return false;
        // 从全局用户Channel关系中获取接受方的channel
        ChannelId recipientChannelId = UserChannelRel.get(user_Id);
        if (recipientChannelId != null) {
            // 当recipientChannelId不为空的时候，从ChannelGroup去查找对应的channel是否存在
            Channel findChannel = ChatHandler.users.find(recipientChannelId);
            if (findChannel != null) {
                // 用户在线
                TweetVO tweetVO = tweetMapper.getTweetVO(atPojo.getAt_From_Id());
                findChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(new DataContent(NettyActionEnum.AT.vule, new AtBO(atPojo.getAt_Id(), atPojo.getAt_Time(), atPojo.getAt_From_Type(), tweetVO), null))));
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
