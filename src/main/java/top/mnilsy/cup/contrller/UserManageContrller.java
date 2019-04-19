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
     * @param requestMessage 用户名||手机号码||电子邮箱data.user，密码data.passwd
     * @return 请求状态码status，用户信息data.UserPojoOV,会话data.sessionid
     */
    @PostMapping("/passwdLogin.api")
    public ResponMessage passwdLogin(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 用户请求手机验证码，不需要带sessionid
     *
     * @param requestMessage 手机号码data.user_Phone
     * @return 请求状态码status，验证码data.code，会话data.sessionid
     */
    @GetMapping("/getPhoneCode.api")
    public ResponMessage getPhoneCode(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 验证码登录
     *
     * @param requestMessage 手机号码data.user_Phone，验证码data.code
     * @return 请求状态码status，用户信息data.UserPojoOV
     */
    @PostMapping("/codeLogin.api")
    public ResponMessage codeLogin(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 账号注册
     *
     * @param requestMessage 手机号码data.user_Phone，验证码data.code
     * @return 请求状态码status，错误信息message
     */
    @PostMapping("/register.api")
    public ResponMessage register(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 检测用户名是否唯一
     *
     * @param requestMessage 用户名data.user_Name
     * @return 请求状态码status
     */
    @PostMapping("/checkUserName.api")
    public ResponMessage checkUserName(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 设置用户名和密码
     *
     * @param requestMessage 用户名data.user_Name，密码data.passwd
     * @return 请求状态码status，用户信息data.UserPojoOV
     */
    @PostMapping("/setUserNamePasswd.api")
    public ResponMessage setUserNamePasswd(@RequestBody RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 上传头像
     * @param responMessage 头像base64编码data.user_Head
     * @return 请求状态码status，用户信息data.UserPojoOV
     */
    @PostMapping("/uploadingUserHead.api")
    public ResponMessage uploadingUserHead(@RequestBody ResponMessage responMessage){
        return new ResponMessage();
    }

    /**
     * 上传背景图
     * @param requestMessage 背景base64编码data.user_Background
     * @return 请求状态码status，用户信息data.UserPojoOV
     */
    @PostMapping("/uploadingUserBackgroundUrl.api")
    public ResponMessage uploadingUserBackgroundUrl(@RequestBody RequestMessage requestMessage){
        return new ResponMessage();
    }
}
