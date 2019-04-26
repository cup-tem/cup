package top.mnilsy.cup.service.impl;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import top.mnilsy.cup.BO.AtBO;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.dao.AtMapper;
import top.mnilsy.cup.dao.TweetMapper;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.enums.AtFromTypeEnum;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.ChatHandler;
import top.mnilsy.cup.netty.DataContent;
import top.mnilsy.cup.netty.UserChannelRel;
import top.mnilsy.cup.pojo.AtPojo;
import top.mnilsy.cup.service.AtService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mnilsy on 19-4-25 上午1:37.
 */
public class AtServiceImpl implements AtService {
    @Resource(name = "tweetMapper")
    private TweetMapper tweetMapper;

    @Resource(name = "atMapper")
    private AtMapper atMapper;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public boolean tweetAt(String at_From_Id, String user_Id) {
        //组装AtPojo，存库
        AtPojo atPojo = new AtPojo(user_Id, at_From_Id, AtFromTypeEnum.TWEET.vlue);
        if (!atMapper.insertAt(atPojo))
            return false;
        // 从全局用户Channel关系中获取接受方的channel
        ChannelId recipientChannelId = UserChannelRel.get(userMapper.getUserName(user_Id));
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

    @Override
    public boolean discussAt(String at_From_Id) {
        //组装AtPojo，存库
        String user_Id = tweetMapper.getUserId(at_From_Id);//获取推文发送者id
        AtPojo atPojo = new AtPojo(user_Id, at_From_Id, AtFromTypeEnum.DISCUSS.vlue);
        if (!atMapper.insertAt(atPojo))
            return false;
        // 从全局用户Channel关系中获取接受方的channel
        ChannelId recipientChannelId = UserChannelRel.get(userMapper.getUserName(user_Id));
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

    @Override
    public boolean writebackAt(String at_From_Id, String user_Id) {
        return false;
    }

    @Override
    public boolean proclamationAt(String at_From_Id) {
        return false;
    }

    @Override
    public boolean likeAt(String at_From_Id, String user_Id) {
        return false;
    }

    @Override
    public boolean signfor(String at_Id) {
        return false;
    }

    @Override
    public List<AtBO> getNotSignfor(String user_Name) {
        List<AtPojo> pojoList = atMapper.selectNotSignforByUserName(user_Name);
        for (AtPojo atPojo : pojoList) {
            //判断类型
        }
        return null;
    }

    @Override
    public boolean sendAt(AtBO atBO) {
        // 从全局用户Channel关系中获取接受方的channel
        ChannelId recipientChannelId = UserChannelRel.get(atMapper.selectUser_Name(atBO.getAt_Id()));
        if (recipientChannelId != null) {
            // 当recipientChannelId不为空的时候，从ChannelGroup去查找对应的channel是否存在
            Channel findChannel = ChatHandler.users.find(recipientChannelId);
            if (findChannel != null) {
                // 用户在线
                findChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(new DataContent(NettyActionEnum.AT.vule, atBO, null))));
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean sendAt(AtBO atBO, String user_Name) {
        // 从全局用户Channel关系中获取接受方的channel
        ChannelId recipientChannelId = UserChannelRel.get(user_Name);
        if (recipientChannelId != null) {
            // 当recipientChannelId不为空的时候，从ChannelGroup去查找对应的channel是否存在
            Channel findChannel = ChatHandler.users.find(recipientChannelId);
            if (findChannel != null) {
                // 用户在线
                findChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(new DataContent(NettyActionEnum.AT.vule, atBO, null))));
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
