package top.mnilsy.cup.contrller;


import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.UserService;
import top.mnilsy.cup.utils.RequestMessage;
import top.mnilsy.cup.utils.ResponMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    /**
     * 密码登录，不需要带sessionid
     * @author Jason_Jane
     * @param requestMessage 用户名||手机号码||电子邮箱data.get("user")，密码data.get("passwd")
     * @return 请求状态码status，失败信息message，用户信息data.userVO,会话data.sessionid
     */
    @PostMapping("/open/passwdLogin.api")
    public ResponMessage passwdLogin(RequestMessage requestMessage, HttpSession session) {
       UserPojo userPojo = userService.getPasswdLogin((String)requestMessage.getData().get("user"),(String)requestMessage.getData().get("passwd"));
       if (userPojo != null){
           session.setAttribute("userPojo",userPojo);
           UserVO userVO = userService.getUserByUsername(userPojo.getUser_Name());
           Map<String, String> map = new HashMap<>();
           map.put("sessionId", session.getId());
           map.put("userVO",userVO.toString());
           return ResponMessage.ok(map);
       }
       return ResponMessage.error("密码登录失败");
    }

    /**
     * 请求手机验证码，不需要带sessionid
     * @author mnilsy
     * @param requestMessage 手机号码data.get("user_Phone“）
     * @return 请求状态码status，失败信息message，会话data.sessionid
     */
    @GetMapping("/open/getPhoneCode.api")
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
     * @author Jason_Jane
     * @param requestMessage 手机号码data.get("user_Phone")，验证码data.get("code")
     * @return 请求状态码status，失败信息message，用户信息data.userVO
     */
    @PostMapping("/open/codeLogin.api")
    public ResponMessage codeLogin(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = userService.codeLogin((String)requestMessage.getData().get("user_Phone"),(String)requestMessage.getData().get("code"));
            if (userPojo != null){
                session.setAttribute("userPojo",userPojo);
                UserVO userVO = userService.getUserByUsername(userPojo.getUser_Name());
                return ResponMessage.ok(userVO);
            }
            return ResponMessage.error("验证码登陆失败");
        }

    /**
     * 登出
     * @return message
     */
    @PostMapping("/logout.api")
    public ResponMessage logout(HttpSession session, HttpServletRequest request){
        session = request.getSession();
        if (session != null){
            UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
            session.invalidate();
            return ResponMessage.ok("登出成功");
        }
        return ResponMessage.error("无需登出");
    }

    /**
     * 账号注册
     * @author Jason_Jane
     * @param requestMessage 手机号码data.get("user_Phone")，验证码data.get("code")
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/open/register.api")
    public ResponMessage register(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = userService.register((String)requestMessage.getData().get("user_Phone"),(String)requestMessage.getData().get("code"));
        if (userPojo != null){
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok();
        }
        return ResponMessage.error("账号注册失败");
    }

    /**
     * 检测用户名是否唯一
     * @author Jason_Jane
     * @param requestMessage 用户名data.get("user_Name")
     * @return 请求状态码status
     */
    @PostMapping("/open/checkUserName.api")
    public ResponMessage checkUserName(RequestMessage requestMessage) {
        String checkUserName = userService.checkUserName((String)requestMessage.getData().get("user_Name"));
        if (checkUserName != null){
            return ResponMessage.ok();
        }
        return ResponMessage.error("用户名重复");
    }

    /**
     * 设置用户名和密码
     * @author Jason_Jane
     * @param requestMessage 用户名data.get("user_Name")，密码data.get("passwd")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/setUserNamePasswd.api")
    public ResponMessage setUserNamePasswd(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.setUserNamePasswd((String)requestMessage.getData().get("user_Name"),(String)requestMessage.getData().get("passwd"),userPojo);
        if (userVO != null){
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("设置用户名和密码失败");
    }

    /**
     * 上传头像
     *
     * @param requestMessage 头像base64编码data.get("user_Head")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/uploadingUserHead.api")
    public ResponMessage uploadingUserHead(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.uploadingUserHead((String)requestMessage.getData().get("user_Head"),userPojo);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("上传头像失败");
    }

    /**
     * 上传背景图
     *
     * @param requestMessage 背景base64编码data.get("user_Background")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/uploadingUserBackgroundUrl.api")
    public ResponMessage uploadingUserBackgroundUrl(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.uploadingBackground((String)requestMessage.getData().get("user_Background"),userPojo);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("上传背景图失败");
    }

    /**
     * 修改昵称
     *
     * @param requestMessage 用户昵称data.get("user_NickName")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserNickName.api")
    public ResponMessage updateUserNickName(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.updateUserNickName((String)requestMessage.getData().get("user_Sex"),userPojo);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改昵称失败");
    }

    /**
     * 修改性别
     * @author Jason_Jane
     * @param requestMessage 用户性别data.get("user_Sex")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserSex.api")
    public ResponMessage updateUserSex(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.updateUserSex((String)requestMessage.getData().get("user_Sex"),userPojo);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改性别失败");
    }

    /**
     * 修改密码
     * @author Jason_Jane
     * @param requestMessage 用户旧密码data.get("oldPasswd")，用户新密码data.get("newPasswd")
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/updatePasswd.api")
    public ResponMessage updatePasswd(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String updatePasswd = userService.updatePasswd((String)requestMessage.getData().get("oldPasswd"),(String)requestMessage.getData().get("newPasswd"),userPojo);
        if (updatePasswd != null){
            return ResponMessage.ok();
        }
        return ResponMessage.error("修改密码失败");
    }

    /**
     * 找回密码
     * @author Jason_Jane
     * @param requestMessage 用户新密码data.get("newPasswd")，手机验证码data.get("code")
     * @return 请求状态码status，失败信息message
     */
    @PostMapping("/open/retrievePasswd.api")
    public ResponMessage retrievePasswd(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String retrievePasswd = userService.retrievePasswd((String)requestMessage.getData().get("newPasswd"),(String)requestMessage.getData().get("code"),userPojo);
        if (retrievePasswd != null){
            return ResponMessage.ok();
        }
        return ResponMessage.error("找回密码失败");
    }

    /**
     * 修改手机号码
     *
     * @param requestMessage 用户新手机号码data.get("user_Phone")，手机验证码data.get("code")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserPhone.api")
    public ResponMessage updateUserPhone(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String oldPhone = userPojo.getUser_Phone();
        UserVO userVO = userService.updateUserPhone((String)requestMessage.getData().get("user_Phone"),(String)requestMessage.getData().get("code"),oldPhone);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改手机号码失败");
    }

    /**
     * 请求电子邮箱验证码
     *
     * @param requestMessage 用户电子邮箱data.get("user_Email")
     * @return 请求状态码status，失败信息message
     */
    @GetMapping("/open/getEmailCode.api")
    public ResponMessage getEmailCode(RequestMessage requestMessage,HttpSession session) {
        String eCode = userService.getEmailCode((String) requestMessage.getData().get("user_Email"));
        if (eCode != null) {
            session.setAttribute("eCode", eCode);
            Map<String, String> map = new HashMap<>();
            map.put("sessionId", session.getId());
            return ResponMessage.ok(map);
        }
        return ResponMessage.error("获取电子邮箱验证码失败");
    }

    /**
     * 绑定电子邮箱
     *
     * @param requestMessage 用户邮箱data.get("user_Email")，邮箱验证码data.get("code")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/bindUserEmail.api")
    public ResponMessage bindUserEmail(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.bindUserEmail((String) requestMessage.getData().get("user_Email"),(String) requestMessage.getData().get("code"),userPojo);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("绑定电子邮箱失败");
    }

    /**
     * 修改电子邮箱
     *
     * @param requestMessage 用户新邮箱data.get("user_Email")，新邮箱验证码data.get("newCode")，旧邮箱验证码data.get("oldCode")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserEmail.api")
    public ResponMessage updateUserEmail(RequestMessage requestMessage,HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        UserVO userVO = userService.updateUserEmail((String) requestMessage.getData().get("user_Email"),(String) requestMessage.getData().get("newCode"),(String) requestMessage.getData().get("oldCode"),userPojo);
        if (userVO != null){
            userPojo = userMapper.getUserByUserName(userVO.getUser_Name());
            session.setAttribute("userPojo",userPojo);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改电子邮箱失败");
    }

}
