package top.mnilsy.cup.sendTest;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import top.mnilsy.cup.enums.UrlEnum;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mnilsy on 19-5-9 上午12:54.
 */
public class HttpUtil {
    /**
     * 通用发送请求
     * @param url 请求地址
     * @param requestBody 请求requesbody
     * @return 服务器返回数据
     * @author mnilsy
     */
    private static ResponMessage send(String url, RequestBody requestBody) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(UrlEnum.HTTPSERVER.vlue+url).post(requestBody).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.body() == null) return null;
            String s=response.body().string();
            return JSON.parseObject(s, ResponMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 密码登录
     * @param user 用户名||手机号码||电子邮箱
     * @param passwd 登录密码
     * @return 服务器返回数据
     * @author mnilsy
     */
    public static ResponMessage passwdLogin(String user, String passwd) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("passwd", passwd);
        RequestMessage requestMessage = new RequestMessage(data);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(requestMessage));

        return send("open/passwdLogin.api", requestBody);
    }
}
