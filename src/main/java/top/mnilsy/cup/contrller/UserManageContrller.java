package top.mnilsy.cup.contrller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.UserService;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by mnilsy on 19-4-18 下午11:59.
 * 用户管理控制器
 */

@RestController
public class UserManageContrller {

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 密码登录，不需要带sessionid
     *
     * @param requestMessage 用户名||手机号码||电子邮箱data.get("user")，密码data.get("passwd")
     * @return 请求状态码status，失败信息message，用户信息data.userPojoVO,会话data.sessionid
     */
    @PostMapping("/passwdLogin.api")
    public ResponMessage passwdLogin(RequestMessage requestMessage, HttpSession session) {
       String user = userService.getPasswdLogin((String)requestMessage.getData().get("user"),(String)requestMessage.getData().get("passwd"));
       if (user != null){
           session.setAttribute("user",user);
           Map<String, String> map = new HashMap<>();
           map.put("sessionId", session.getId());
           map.put("user",user);
           return ResponMessage.ok(map);
       }
       return ResponMessage.error("密码登录失败");
    }

    /**
     * 请求手机验证码，不需要带sessionid
     *
     * @param requestMessage 手机号码data.get("user_Phone“）
     * @return 请求状态码status，失败信息message，会话data.sessionid
     */
    @GetMapping("/getPhoneCode.api")
    public ResponMessage getPhoneCode(RequestMessage requestMessage, HttpSession session) {
        String code = userService.getPhoneCode((String) requestMessage.getData().get("user_Phone"));
        if (code != null) {
            session.setAttribute("phoneCode", code);
            Map<String, String> map = new HashMap<>();
            map.put("sessionId", session.getId());
            return ResponMessage.ok(map);
        }
        return ResponMessage.error("获取验证码失败");
    }

    /**
     * 验证码登录
     *
     * @param requestMessage 手机号码data.get("user_Phone")，验证码data.get("code")
     * @return 请求状态码status，失败信息message，用户信息data.userPojoVO
     */
    @PostMapping("/codeLogin.api")
    public ResponMessage codeLogin(RequestMessage requestMessage, HttpSession session) {
        String codeLogin = userService.codeLogin((String)requestMessage.getData().get("user_Phone"),(String)requestMessage.getData().get("code"));
        String code = userService.getPhoneCode((String)requestMessage.getData().get("code"));
            if (codeLogin != null){
                session.setAttribute("codeLogin",codeLogin);
                Map<String, String> map = new HashMap<>();
                map.put("codeLogin",codeLogin);
                return ResponMessage.ok(map);
            }
            return ResponMessage.error("验证码登陆失败");
        }

    /**
     * 账号注册
     *
     * @param requestMessage 手机号码data.get("user_Phone")，验证码data.get("code")
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/register.api")
    public ResponMessage register(RequestMessage requestMessage) {
        String register = userService.register((String)requestMessage.getData().get("user_Phone"),(String)requestMessage.getData().get("code"));
        if (register != null){
            Map<String, String> map = new HashMap<>();
            map.put("register",register);
            return ResponMessage.ok(map);
        }
        return ResponMessage.error("账号注册失败");
    }

    /**
     * 检测用户名是否唯一
     *
     * @param requestMessage 用户名data.get("user_Name")
     * @return 请求状态码status
     */
    @PostMapping("/checkUserName.api")
    public ResponMessage checkUserName(RequestMessage requestMessage) {
        String checkUserName = userService.checkUserName((String)requestMessage.getData().get("user_Name"));
        if (checkUserName != null){
            Map<String, String> map = new HashMap<>();
            map.put("checkUserName",checkUserName);
            return ResponMessage.ok(map);
        }
        return ResponMessage.error("检测用户名是否唯一失败");
    }

    /**
     * 设置用户名和密码
     *
     * @param requestMessage 用户名data.get("user_Name")，密码data.get("passwd")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/setUserNamePasswd.api")
    public ResponMessage setUserNamePasswd(RequestMessage requestMessage,HttpSession session) {
        return new ResponMessage();
    }

    /**
     * 上传头像
     *
     * @param requestMessage 头像base64编码data.get("user_Head")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/uploadingUserHead.api")
    public ResponMessage uploadingUserHead(RequestMessage requestMessage,HttpSession session) {
        return new ResponMessage();
    }

    /**
     * 上传背景图
     *
     * @param requestMessage 背景base64编码data.get("user_Background")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/uploadingUserBackgroundUrl.api")
    public ResponMessage uploadingUserBackgroundUrl(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 修改昵称
     *
     * @param requestMessage 用户昵称data.get("user_NickName")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/updateUserNickName.api")
    public ResponMessage updateUserNickName(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 修改性别
     *
     * @param requestMessage 用户性别data.get("user_Sex")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/updateUserSex.api")
    public ResponMessage updateUserSex(RequestMessage requestMessage) {
        return new  ResponMessage();
    }

    /**
     * 修改密码
     *
     * @param requestMessage 用户旧密码data.get("oldPasswd")，用户新密码data.get("newPasswd")
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/updatePasswd.api")
    public ResponMessage updatePasswd(RequestMessage requestMessage,HttpSession session) {
        String updatePasswd = userService.updatePasswd((String)requestMessage.getData().get("oldPasswd"),(String)requestMessage.getData().get("newPasswd"));
        if (updatePasswd != null){

        }
        return ResponMessage.error("修改密码失败");
    }

    /**
     * 找回密码
     *
     * @param requestMessage 用户新密码data.get("newPasswd")，手机验证码data.get("code")
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/retrievePasswd.api")
    public ResponMessage retrievePasswd(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 修改手机号码
     *
     * @param requestMessage 用户新手机号码data.get("user_Phone")，手机验证码data.get("code")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/updateUserPhone.api")
    public ResponMessage updateUserPhone(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 请求电子邮箱验证码
     *
     * @param requestMessage 用户电子邮箱data.get("user_Email")
     * @return 请求状态码status，失败信息message
     */
    @GetMapping("/getEmailCode.api")
    public ResponMessage getEmailCode(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 绑定电子邮箱
     *
     * @param requestMessage 用户邮箱data.get("user_Email")，邮箱验证码data.get("code")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/bindUserEmail.api")
    public ResponMessage bindUserEmail(RequestMessage requestMessage) {
        return new ResponMessage();
    }

    /**
     * 修改电子邮箱
     *
     * @param requestMessage 用户新邮箱data.get("user_Email")，新邮箱验证码data.get("newCode")，旧邮箱验证码data.get("oldCode")
     * @return 请求状态码status，用户信息data.userPojoVO
     */
    @PostMapping("/updateUserEmail.api")
    public ResponMessage updateUserEmail(RequestMessage requestMessage) {
        return new ResponMessage();
    }
}
