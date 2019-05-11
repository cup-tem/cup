package top.mnilsy.cup.sendTest;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import okio.ByteString;
import top.mnilsy.cup.enums.NettyActionEnum;
import top.mnilsy.cup.netty.DataContent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by mnilsy on 19-5-11 下午3:30.
 */
public class SocketClient {
    public static void main(String[] args) {
        new SocketClient().start();
    }

    public void start() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build();
        Request request = new Request.Builder().url("ws://127.0.0.1:8088/ws").build();
        Timer timer = new Timer();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {

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
        });
    }
}
