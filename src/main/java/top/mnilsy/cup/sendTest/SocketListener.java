package top.mnilsy.cup.sendTest;

import com.alibaba.fastjson.JSON;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.DataContent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mnilsy on 19-5-12 上午11:04.
 */
public class SocketListener extends WebSocketListener {

    private Timer timer = new Timer();
    private WebSocket msocket;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        this.msocket = webSocket;
        //ping pong
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                webSocket.send(JSON.toJSONString(new DataContent(NettyActionEnum.KEEPALIVE.vule, null, null)));
                System.out.println("ping pong");
            }
        }, new Date(), 30000);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("我收到了");
        System.out.println(text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println(bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        timer.cancel();
        System.out.println("我被关闭了");
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        timer.cancel();
        System.out.println("我退出了");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        timer.cancel();
        System.out.println("我错误了");
        t.printStackTrace();
    }

    public WebSocket getMsocket() {
        return msocket;
    }

    public void setMsocket(WebSocket msocket) {
        this.msocket = msocket;
    }

    public void close(){
        msocket.close(1000,"exit");
    }
}
