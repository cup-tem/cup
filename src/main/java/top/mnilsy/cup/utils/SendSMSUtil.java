package top.mnilsy.cup.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * Created by mnilsy on 19-4-26 下午6:52.
 * 发送短信
 */
public class SendSMSUtil {

    /**
     * 发送短信验证码
     *
     * @param phoneNumber 手机号码
     * @param code        验证码
     * @return
     */
    public static boolean send(String phoneNumber, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIvGygzcCRkahy", "");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", "推点");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateCode", "SMS_164156022");
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return JSONObject.parseObject(response.getData()).get("Message").equals("OK");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
