package top.mnilsy.cup.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.pojoVO.UserPojoVO;

/**
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channle
    public static ChannelGroup users =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        // 获取客户端传输过来的消息
        String content = msg.text();

        Channel currentChannel = ctx.channel();

        // 获取客户端发来的消息
        DataContent dataContent = (DataContent) JSON.parse(content);
        int action = dataContent.getAction();
        // 判断消息类型，根据不同的类型来处理不同的业务

        if (action == NettyActionEnum.LOGIN.vule) {
            //当websocket 第一次open的时候，初始化channel，把用的channel和user_Name关联起来
            String senderName = dataContent.getMessagePojoVO().getSender_Name();
            UserChannelRel.put(senderName,currentChannel.id());

            // 测试
            for (Channel c : users) {
                System.out.println(c.id().asLongText());
            }
            UserChannelRel.output();
        } else if (action == NettyActionEnum.CHAT_TEXT.vule) {
            //  聊天类型的消息，把聊天记录保存到数据库

            UserPojoVO userPojoVO=new UserPojoVO();

            // 发送消息
            // 从全局用户Channel关系中获取接受方的channel
            ChannelId recipientChannelId = UserChannelRel.get(dataContent.getMessagePojoVO().getRecipient_Name());
            if (recipientChannelId == null) {
                //未登录处理

            } else {
                // 当recipientChannelId不为空的时候，从ChannelGroup去查找对应的channel是否存在
                Channel findChannel = users.find(recipientChannelId);
                if (findChannel != null) {
                    // 用户在线
                    findChannel.writeAndFlush(new TextWebSocketFrame());
                } else {
                    // 用户离线操作
                }
            }

        } else if (action == NettyActionEnum.SIGN_FOR.vule) {
            //  签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]

            // 扩展字段在signed类型的消息中，代表需要去签收的消息id，逗号间隔
            String msgIdsStr = dataContent.getExtand();
            String msgIds[] = msgIdsStr.split(",");

           /* List<String> msgIdList = new ArrayList<>();
            for (String mid : msgIds) {
                if (StringUtils.isNotBlank(mid)) {
                    msgIdList.add(mid);
                }
            }*/

       /*     System.out.println(msgIdList.toString());

            if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() > 0) {
                // 批量签收
                userService.updateMsgSigned(msgIdList);
            }*/

        } else if (action == NettyActionEnum.KEEPALIVE.vule) {
            //  2.4  心跳类型的消息
            System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");
        }
    }

    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channle，并且放到ChannelGroup中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        String channelId = ctx.channel().id().asShortText();
        System.out.println("客户端被移除，channelId为：" + channelId);

        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
