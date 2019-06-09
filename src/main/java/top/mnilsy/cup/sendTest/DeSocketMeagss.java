package top.mnilsy.cup.sendTest;

import com.alibaba.fastjson.JSON;
import okhttp3.WebSocket;
import top.mnilsy.cup.BO.AtBO;
import top.mnilsy.cup.VO.*;
import top.mnilsy.cup.enums.AtFromTypeEnum;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.DataContent;

/**
 * Created by mnilsy on 19-5-12 下午2:54.
 */
public class DeSocketMeagss {
    public static void deCode(String data, Object handler, WebSocket webSocket) {
        DataContent dataContent = JSON.parseObject(data, DataContent.class);
        if (dataContent.getAction() == NettyActionEnum.LOGIN.vule) {
            MessageVO messageVO = (MessageVO) dataContent.getData();
            meagess(handler, messageVO);
            webSocket.send(JSON.toJSONString(new DataContent(NettyActionEnum.SIGNFOR_MESSAGE.vule, null, messageVO.getMessage_Id())));
            return;
        }
        if (dataContent.getAction() == NettyActionEnum.ERROR.vule) {
            error(handler);
            return;
        }
        if (dataContent.getAction() == NettyActionEnum.LOGOUT.vule) {
            webSocket.close(1000, "exit");
            logout(handler);
            return;
        }
        if (dataContent.getAction() == NettyActionEnum.AT.vule) {
            AtBO atBO = (AtBO) dataContent.getData();
            if (atBO.getAt_From_Type() == AtFromTypeEnum.TWEET.vlue) {
                webSocket.send(JSON.toJSONString(new DataContent(NettyActionEnum.SIGNFOR_AT.vule, null, atBO.getAt_Id())));
                tweet(handler, (TweetVO) atBO.getData());
                return;
            }
            if (atBO.getAt_From_Type() == AtFromTypeEnum.DISCUSS.vlue) {
                webSocket.send(JSON.toJSONString(new DataContent(NettyActionEnum.SIGNFOR_AT.vule, null, atBO.getAt_Id())));
                discuss(handler, (Discuss_AtVO) atBO.getData());
                return;
            }
            if (atBO.getAt_From_Type() == AtFromTypeEnum.WRITE_BACK.vlue) {
                webSocket.send(JSON.toJSONString(new DataContent(NettyActionEnum.SIGNFOR_AT.vule, null, atBO.getAt_Id())));
                writeback(handler, (Writeback_AtVO) atBO.getData());
                return;
            }
            if (atBO.getAt_From_Type() == AtFromTypeEnum.LIKE.vlue) {
                webSocket.send(JSON.toJSONString(new DataContent(NettyActionEnum.SIGNFOR_AT.vule, null, atBO.getAt_Id())));
                like(handler, (Like_AtVO) atBO.getData());
            }
        }
    }

    private static void meagess(Object handler, MessageVO messageVO) {
        //保存聊天记录到本地
        //将聊天信息渲染到页面
    }

    private static void error(Object handler) {
        //信息发送失败
    }

    private static void logout(Object handler) {
        //退出登录
    }

    private static void tweet(Object handler, TweetVO tweetVO) {
        //有人发推文@你
    }

    private static void discuss(Object handler, Discuss_AtVO discuss_atVO) {
        //有人评论你的推文
    }

    private static void writeback(Object handler, Writeback_AtVO writeback_atVO) {
        //有人回复你的评论
    }

    private static void like(Object handler, Like_AtVO like_atVO) {
        //有人赞了你的推文
    }

}
