package top.mnilsy.cup.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mnilsy.cup.util.RequestMessage;
import top.mnilsy.cup.util.ResponMessage;

/**
 * Created by mnilsy on 19-4-18 下午11:59.
 * 用户管理控制器
 */

@Controller
@ResponseBody
public class UserManageContrller {

    /**
     * 用户密码登录，不需要带sessionid
     *
     * @param requestMessage data.user为用户名||手机号码||电子邮箱，data.passwd为密码
     * @return 请求状态码statu，用户信息UserPojoOV,会话sessionid
     */
    @PostMapping("/passwdLogin.api")
    public ResponMessage passwdLogin(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 用户请求手机验证码，不需要带sessionid
     *
     * @param requestMessage data.user_Phone为手机号码
     * @return 请求状态码statu，验证码data.code，会话sessionid
     */
    @GetMapping("/getPhoneCode.api")
    public ResponMessage getPhoneCode(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }


    /**
     * 验证码登录
     *
     * @param requestMessage data.user_Phone为手机号码，data.code为验证码
     * @return 请求状态码statu，用户信息UserPojoOV
     */
    @PostMapping("/codeLogin.api")
    public ResponMessage codeLogin(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

}
