package top.mnilsy.cup.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.pojoVO.MessagePojoVO;
import top.mnilsy.cup.service.MessageService;
import top.mnilsy.cup.utils.SpringUtil;

import java.util.List;

/**
 * @Description: 处理消息的handler
 * TextWebSocketFrame： 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channle
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private MessageService messageService = (MessageService) SpringUtil.getBean("messageService");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();// 获取客户端传输过来的消息
        Channel currentChannel = ctx.channel();
        // 获取客户端发来的消息
        DataContent dataContent = (DataContent) JSON.parse(content);
        int action = dataContent.getAction();
        // 判断消息类型，根据不同的类型来处理不同的业务

        if (action == NettyActionEnum.LOGIN.vule) {
            //当websocket 第一次open的时候，初始化channel，把用的channel和user_Name关联起来
            //扩展字段在login类型的消息中，代表登录的用户名
            String user_Name = dataContent.getExtand();
            UserChannelRel.put(user_Name, currentChannel.id());


            // ====================== 测试 start  ======================
            for (Channel c : users) {
                System.out.println(c.id().asLongText());
            }
            UserChannelRel.output();
            // ====================== 测试 end    ======================


            //发送未签收的信息给客户端
            List<MessagePojoVO> notSignforMessageList = messageService.getNotSignfor(user_Name);
            for (MessagePojoVO messagePojoVO : notSignforMessageList) {
                messageService.sendMessageText(messagePojoVO);
            }
            return;
        }
        if (action == NettyActionEnum.CHAT_TEXT.vule) {
            //聊天类型的消息，把聊天记录保存到数据库
            MessagePojoVO sendmessagePojoVO = messageService.addMessage(dataContent.getMessagePojoVO());
            //发送消息
            messageService.sendMessageText(sendmessagePojoVO);
            return;
        }
        if (action == NettyActionEnum.SIGN_FOR.vule) {
            //签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]
            //扩展字段在signed类型的消息中，代表需要去签收的消息id
            messageService.signfor(dataContent.getExtand());
            return;
        }
        if (action == NettyActionEnum.KEEPALIVE.vule) {
            //心跳类型的消息
            System.out.println("收到来自channel为[" + UserChannelRel.get(currentChannel.id()) + "]的心跳包");
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
