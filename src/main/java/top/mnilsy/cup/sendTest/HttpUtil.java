package top.mnilsy.cup.sendTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import okhttp3.*;
import top.mnilsy.cup.enums.UrlEnum;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mnilsy on 19-5-9 上午12:54.
 */
public class HttpUtil {
    /**
     * 通用发送请求
     *
     * @param url            请求地址
     * @param requestMessage 请求数据包
     * @return 服务器返回数据
     * @author mnilsy
     */
    private static ResponMessage send(String url, RequestMessage requestMessage, String sessionId) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(requestMessage));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().addHeader("cookie", "JSESSIONID=" + sessionId).url(UrlEnum.HTTPSERVER.vlue + url).post(requestBody).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.body() == null) return null;
            String s = response.body().string();
            return JSON.parseObject(s, ResponMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 密码登录
     *
     * @param user   用户名||手机号码||电子邮箱
     * @param passwd 登录密码
     * @return 请求状态码status，失败信息message，用户信息userVO
     * @author mnilsy
     */
    public static ResponMessage passwdLogin(String user, String passwd) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("passwd", passwd);
        RequestMessage requestMessage = new RequestMessage(data);
        return send("open/passwdLogin.api", requestMessage, null);
    }


    /**
     * 请求手机验证码,用于登录时sessionid传null
     *
     * @param user_Phone 手机号码
     * @return 请求状态码status，失败信息message，会话sessionid
     * @author mnilsy
     */
    public static ResponMessage getPhoneCode(String user_Phone, String sessionid) {
        Map<String, Object> data = new HashMap<>();
        data.put("user_Phone", user_Phone);
        RequestMessage requestMessage = new RequestMessage(data);
        return send("open/getPhoneCode.api", requestMessage, sessionid);
    }


    /**
     * 验证码登录
     *
     * @param user_Phone 手机号码
     * @param code       验证码
     * @return 请求状态码status，失败信息message，用户信息userVO
     * @author mnilsy
     */
    public static ResponMessage codeLogin(String user_Phone, String code, String sessionid) {
        Map<String, Object> data = new HashMap<>();
        data.put("user_Phone", user_Phone);
        data.put("code", code);
        RequestMessage requestMessage = new RequestMessage(data);
        return send("open/codeLogin.api", requestMessage, sessionid);
    }

    /**
     * 登出
     *
     * @return 请求状态码status，失败信息message
     * @author mnilsy
     */
    public static ResponMessage logout(String sessionid) {
        return send("logout.api", null, sessionid);
    }

    /**
     * 账号注册
     *
     * @param user_Phone 手机号码
     * @param code       验证码
     * @return 请求状态码status，失败信息message
     */
    public static ResponMessage register(String user_Phone, String code, String sessionid) {
        Map<String, Object> data = new HashMap<>();
        data.put("user_Phone", user_Phone);
        data.put("code", code);
        RequestMessage requestMessage = new RequestMessage(data);
        return send("", requestMessage, sessionid);
    }
}
