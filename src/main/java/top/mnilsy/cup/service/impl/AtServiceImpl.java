package top.mnilsy.cup.service.impl;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import top.mnilsy.cup.BO.AtBO;
import top.mnilsy.cup.VO.Discuss_AtVO;
import top.mnilsy.cup.VO.Like_AtVO;
import top.mnilsy.cup.VO.TweetVO;
import top.mnilsy.cup.VO.Writeback_AtVO;
import top.mnilsy.cup.dao.*;
import top.mnilsy.cup.enums.AtFromTypeEnum;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.ChatHandler;
import top.mnilsy.cup.netty.DataContent;
import top.mnilsy.cup.netty.UserChannelRel;
import top.mnilsy.cup.pojo.AtPojo;
import top.mnilsy.cup.service.AtService;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource(name = "discussMapper")
    private DiscussMapper discussMapper;

    @Resource(name = "writebackMapper")
    private WritebackMapper writebackMapper;

    @Resource(name = "likeMapper")
    private LikeMapper likeMapper;

    @Override
    public boolean tweetAt(String at_From_Id, String user_Name) {
        //组装AtPojo，存库
        AtPojo atPojo = new AtPojo(userMapper.getUser_Id(user_Name), at_From_Id, AtFromTypeEnum.TWEET.vlue);
        if (atMapper.insertAt(atPojo) != 1)
            return false;
        TweetVO tweetVO = tweetMapper.getTweetVO(atPojo.getAt_From_Id());
        AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), tweetVO);
        return sendAt(atBO, user_Name);
    }

    @Override
    public boolean discussAt(String at_From_Id) {
        //组装AtPojo，存库
        String user_Id = discussMapper.getTweetUserId(at_From_Id);//获取推文发送者id
        AtPojo atPojo = new AtPojo(user_Id, at_From_Id, AtFromTypeEnum.DISCUSS.vlue);
        if (atMapper.insertAt(atPojo) != 1)
            return false;
        Discuss_AtVO discussAtVO = discussMapper.getDiscuss_AtVO(at_From_Id);
        AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), discussAtVO);
        return sendAt(atBO, userMapper.getUserName(user_Id));
    }

    @Override
    public boolean writebackAt(String at_From_Id) {
        //组装AtPojo，存库
        String user_Id = writebackMapper.getDiscussUserId(at_From_Id);//获取评论者id
        AtPojo atPojo = new AtPojo(user_Id, at_From_Id, AtFromTypeEnum.WRITE_BACK.vlue);
        if (atMapper.insertAt(atPojo) != 1)
            return false;
        Writeback_AtVO writebackAtVO = writebackMapper.getWriteback_AtVO(at_From_Id);
        if (writebackAtVO.getWriteBack_User_Id() != null) {
            AtPojo atPojo1 = new AtPojo(writebackAtVO.getWriteBack_User_Id(), at_From_Id, AtFromTypeEnum.WRITE_BACK.vlue);
            if (atMapper.insertAt(atPojo1) != 1)
                return false;
        }
        AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), writebackAtVO);
        return sendAt(atBO, userMapper.getUserName(user_Id)) && sendAt(atBO, userMapper.getUserName(writebackAtVO.getWriteBack_User_Id()));
    }

    @Override
    public boolean proclamationAt(String at_From_Id) {
        return false;
    }

    @Override
    public boolean likeAt(String at_From_Id) {
        //组装AtPojo，存库
        String user_Id = likeMapper.getTweetUserId(at_From_Id);//获取推文发送者id
        AtPojo atPojo = new AtPojo(user_Id, at_From_Id, AtFromTypeEnum.LIKE.vlue);
        if (atMapper.insertAt(atPojo) != 1)
            return false;
        Like_AtVO likeAtVO = likeMapper.getLike_AtVO(at_From_Id);
        AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), likeAtVO);
        return sendAt(atBO, userMapper.getUserName(user_Id));
    }

    @Override
    public boolean signfor(String at_Id) {
        return false;
    }

    @Override
    public List<AtBO> getNotSignfor(String user_Name) {
        List<AtPojo> pojoList = atMapper.selectNotSignforByUserName(user_Name);
        List<AtBO> atBOList = new ArrayList<>();
        for (AtPojo atPojo : pojoList) {
            //判断类型
            if (atPojo.getAt_From_Type() == AtFromTypeEnum.TWEET.vlue) {
                AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), tweetMapper.getTweetVO(atPojo.getAt_From_Id()));
                atBOList.add(atBO);
                continue;
            }
            if (atPojo.getAt_From_Type() == AtFromTypeEnum.DISCUSS.vlue) {
                AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), discussMapper.getDiscuss_AtVO(atPojo.getAt_From_Id()));
                atBOList.add(atBO);
                continue;
            }
            if (atPojo.getAt_From_Type() == AtFromTypeEnum.WRITE_BACK.vlue) {
                AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), writebackMapper.getWriteback_AtVO(atPojo.getAt_From_Id()));
                atBOList.add(atBO);
                continue;
            }
            if (atPojo.getAt_From_Type() == AtFromTypeEnum.PROCLAMATION.vlue) {
                AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), new Object());//公告实体待定
                atBOList.add(atBO);
                continue;
            }
            if (atPojo.getAt_From_Type() == AtFromTypeEnum.LIKE.vlue) {
                AtBO atBO = new AtBO(atPojo.getAt_Id(), atPojo.getAt_From_Type(), likeMapper.getLike_AtVO(atPojo.getAt_From_Id()));
                atBOList.add(atBO);
            }
        }
        return atBOList;
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
