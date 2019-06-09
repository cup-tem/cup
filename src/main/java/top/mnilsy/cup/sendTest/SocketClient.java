package top.mnilsy.cup.sendTest;

import okhttp3.*;

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
        SocketListener socketListener = new SocketListener("MNILSY");
        client.newWebSocket(request, socketListener);
    }
}
