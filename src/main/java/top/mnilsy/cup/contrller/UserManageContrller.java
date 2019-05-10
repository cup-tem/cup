package top.mnilsy.cup.contrller;


import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.UserPojo;
import top.mnilsy.cup.service.UserService;
import top.mnilsy.cup.utils.FileUtil;
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

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    /**
     * 密码登录，不需要带sessionid
     *
     * @param requestMessage 用户名||手机号码||电子邮箱data.get("user")，密码data.get("passwd")
     * @return 请求状态码status，失败信息message，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/open/passwdLogin.api")
    public ResponMessage passwdLogin(@RequestBody RequestMessage requestMessage, HttpSession session) {
        String user = (String) requestMessage.getData().get("user");
        String passwd = (String) requestMessage.getData().get("passwd");
        if (user == null) return ResponMessage.error("账号不能为空");
        if (passwd == null) return ResponMessage.error("密码不能为空");
        UserPojo userPojo = userService.getPasswdLogin(user, passwd);
        if (userPojo == null) return ResponMessage.error("登录失败");
        session.setAttribute("userInfo", userPojo);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userPojo, userVO);
        userVO.setSessionId(session.getId());
        return ResponMessage.ok(userVO);
    }

    /**
     * 请求手机验证码
     *
     * @param requestMessage 手机号码data.get("user_Phone“）
     * @return 请求状态码status，失败信息message，会话data.sessionid
     * @author mnilsy
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
     *
     * @param requestMessage 手机号码data.get("user_Phone")，验证码data.get("code")
     * @return 请求状态码status，失败信息message，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/open/codeLogin.api")
    public ResponMessage codeLogin(RequestMessage requestMessage, HttpSession session) {
        String user_Phone = (String) requestMessage.getData().get("user_Phone");
        String code = (String) requestMessage.getData().get("code");
        String rCode = userService.getPhoneCode(user_Phone);
        if (user_Phone == null) return ResponMessage.error("请输入手机号码！");
        if (!code.equals(rCode)) return ResponMessage.error("验证码输入有误！");
        UserPojo userPojo = userService.codeLogin(user_Phone);
        if (userPojo != null) {
            session.setAttribute("userPojo", userPojo);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userPojo, userVO);
            userVO.setSessionId(session.getId());
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("验证码登陆失败");
    }

    /**
     * 登出
     *
     * @return message
     */
    @PostMapping("/logout.api")
    public ResponMessage logout(HttpSession session) {
        if (session.getAttribute("userInfo") != null) {
            session.invalidate();
            return ResponMessage.ok();
        }
        return ResponMessage.error("无需登出");
    }

    /**
     * 账号注册
     *
     * @param requestMessage 手机号码data.get("user_Phone")，验证码data.get("code")
     * @return 请求状态码status，失败信息message
     * @author Jason_Jane
     */
    @PostMapping("/open/register.api")
    public ResponMessage register(RequestMessage requestMessage, HttpSession session) {
        String user_Phone = (String) requestMessage.getData().get("user_Phone");
        String telRegex = "^[1](([3|5|8][\\\\d])|([4][5-9])|([6][5,6])|([7][3-8])|([9][8,9]))[\\\\d]{8}$";
        String code = (String) requestMessage.getData().get("code");
        String rCode = userService.getPhoneCode(user_Phone);
        if (user_Phone == null) return ResponMessage.error("手机号不能为空！");
        if (!user_Phone.matches(telRegex)) return ResponMessage.error("请输入正确的手机号码！");
        if (!code.equals(rCode)) return ResponMessage.error("手机验证码输入有误！");
        int message = userService.register(user_Phone);
        if (message == 0) return ResponMessage.error("手机号已注册！");
        if (message == 1) return ResponMessage.error("账号注册失败！");
        return ResponMessage.ok("账号注册成功！");
    }

    /**
     * 检测用户名是否唯一
     *
     * @param requestMessage 用户名data.get("user_Name")
     * @return 请求状态码status
     * @author Jason_Jane
     */
    @PostMapping("/open/checkUserName.api")
    public ResponMessage checkUserName(RequestMessage requestMessage) {
        String userName = (String) requestMessage.getData().get("user_Name");
        if (userName == null) return ResponMessage.error("用户名为空！");
        String checkUserName = userService.checkUserName(userName);
        if (checkUserName != null) {
            return ResponMessage.ok("用户名可用！");
        }
        return ResponMessage.error("用户名重复！");
    }

    /**
     * 设置用户名和密码
     *
     * @param requestMessage 用户名data.get("user_Name")，密码data.get("passwd")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/setUserNamePasswd.api")
    public ResponMessage setUserNamePasswd(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String user_Name = (String) requestMessage.getData().get("user_Name");
        String passwd = (String) requestMessage.getData().get("passwd");
        String passwdRegex = "(?=.*[a-z])(?=.*\\d)(?=.*[#@!~%^&*])[a-z\\d#@!~%^&*]{6,18}";
        String usernameRegex = "^[a-zA-Z0-9]{2,14}$";
        UserVO userVO = userService.setUserNamePasswd(user_Name, passwd, userPojo);
        if (user_Name == null) return ResponMessage.error("用户名不能为空！");
        if (passwd == null) return ResponMessage.error("密码不能为空！");
        if (!user_Name.matches(usernameRegex)) return ResponMessage.error("用户名为2-14位字母和数字,不区分大小写！");
        if (!passwd.matches(passwdRegex)) return ResponMessage.error("密码必须包含字母数字符号且为6-18位！");
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userPojo");
            userPojo1.setUser_Name(user_Name);
            session.setAttribute("userPojo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("设置用户名和密码失败！");
    }

    /**
     * 上传头像
     *
     * @param requestMessage 头像base64编码data.get("user_Head")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/uploadingUserHead.api")
    public ResponMessage uploadingUserHead(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String user_Head = (String) requestMessage.getData().get("user_Name");
        String base = FileUtil.fileToBase64(user_Head);

        return null;
    }

    /**
     * 上传背景图
     *
     * @param requestMessage 背景base64编码data.get("user_Background")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/uploadingUserBackgroundUrl.api")
    public ResponMessage uploadingUserBackgroundUrl(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        return null;
    }

    /**
     * 修改昵称
     *
     * @param requestMessage 用户昵称data.get("user_NickName")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserNickName.api")
    public ResponMessage updateUserNickName(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String user_NickName = (String) requestMessage.getData().get("user_NickName");
        if (user_NickName == null) return ResponMessage.error("修改昵称不能为空！");
        UserVO userVO = userService.updateUserNickName(user_NickName, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userPojo");
            userPojo1.setUser_NickName(user_NickName);
            session.setAttribute("userPojo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改昵称失败！");
    }

    /**
     * 修改性别
     *
     * @param requestMessage 用户性别data.get("user_Sex")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     * @author Jason_Jane
     */
    @PostMapping("/updateUserSex.api")
    public ResponMessage updateUserSex(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String user_Sex = (String) requestMessage.getData().get("user_Sex");
        if (user_Sex == null) return ResponMessage.error("修改性别不能为空！");
        UserVO userVO = userService.updateUserSex(user_Sex, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userPojo");
            userPojo1.setUser_Sex(user_Sex);
            session.setAttribute("userPojo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改性别失败！");
    }

    /**
     * 修改密码
     *
     * @param requestMessage 用户旧密码data.get("oldPasswd")，用户新密码data.get("newPasswd")
     * @return 请求状态码status，失败信息message
     * @author Jason_Jane
     */
    @PostMapping("/updatePasswd.api")
    public ResponMessage updatePasswd(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String oldPasswd = (String) requestMessage.getData().get("oldPasswd");
        String newPasswd = (String) requestMessage.getData().get("newPasswd");
        String passwdRegex = "(?=.*[a-z])(?=.*\\d)(?=.*[#@!~%^&*?$(){};:'])[a-z\\d#@!~%^&*?$(){};:']{6,18}";
        if (oldPasswd == null) return ResponMessage.error("请输入旧密码！");
        if (newPasswd == null) return ResponMessage.error("请输入新密码！");
        if (oldPasswd.equals(newPasswd)) return ResponMessage.error("新旧密码不能一样！");
        if (!newPasswd.matches(passwdRegex)) return ResponMessage.error("密码必须包含字母数字符号且为6-18位！");
        int message = userService.updatePasswd(oldPasswd, newPasswd, userPojo);
        if (message == 0) {
            return ResponMessage.error("旧密码不正确！");
        } else if (message == 1) {
            return ResponMessage.error("修改密码失败！");
        } else {
            return ResponMessage.ok("修改密码成功！");
        }
    }

    /**
     * 找回密码
     *
     * @param requestMessage 用户新密码data.get("newPasswd")，手机验证码data.get("code")
     * @return 请求状态码status，失败信息message
     * @author Jason_Jane
     */
    @PostMapping("/open/retrievePasswd.api")
    public ResponMessage retrievePasswd(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String newPasswd = (String) requestMessage.getData().get("newPasswd");
        String user_Phone = userPojo.getUser_Phone();
        String rCode = userService.getPhoneCode(user_Phone);
        String code = (String) requestMessage.getData().get("code");
        if (!code.equals(rCode)) return ResponMessage.error("手机验证码输入有误！");
        int message = userService.retrievePasswd(newPasswd, userPojo);
        if (message == 0) return ResponMessage.error("新旧密码一样！");
        if (message == 1) return ResponMessage.error("修改密码失败！");
        return ResponMessage.ok("找回密码成功！");
    }

    /**
     * 修改手机号码
     *
     * @param requestMessage 用户新手机号码data.get("user_Phone")，手机验证码data.get("code")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserPhone.api")
    public ResponMessage updateUserPhone(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String oldPhone = userPojo.getUser_Phone();
        String user_Phone = (String) requestMessage.getData().get("user_Phone");
        String rCode = userService.getPhoneCode(user_Phone);
        String code = (String) requestMessage.getData().get("code");
        if (oldPhone.equals(user_Phone)) return ResponMessage.error("新旧手机号码一样！");
        if (user_Phone == null) return ResponMessage.error("请输入新手机号码！");
        if (!code.equals(rCode)) return ResponMessage.error("输入的手机验证码有误！");
        UserVO userVO = userService.updateUserPhone(user_Phone, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userPojo");
            userPojo1.setUser_Phone(user_Phone);
            session.setAttribute("userPojo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改手机号码失败！");
    }

    /**
     * 请求电子邮箱验证码
     *
     * @param requestMessage 用户电子邮箱data.get("user_Email")
     * @return 请求状态码status，失败信息message
     */
    @GetMapping("/open/getEmailCode.api")
    public ResponMessage getEmailCode(RequestMessage requestMessage, HttpSession session) {
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
    public ResponMessage bindUserEmail(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String user_Email = (String) requestMessage.getData().get("user_Email");
        String code = (String) requestMessage.getData().get("code");
        String rCode = userService.getEmailCode(user_Email);
        if (user_Email == null) return ResponMessage.error("请输入电子邮箱！");
        if (code == null) return ResponMessage.error("请输入验证码！");
        if (!code.equals(rCode)) return ResponMessage.error("验证码不正确！");
        UserVO userVO = userService.bindUserEmail(user_Email, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userPojo");
            userPojo1.setUser_Email(user_Email);
            session.setAttribute("userPojo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("绑定电子邮箱失败！");
    }

    /**
     * 修改电子邮箱
     *
     * @param requestMessage 用户新邮箱data.get("user_Email")，新邮箱验证码data.get("newCode")，旧邮箱验证码data.get("oldCode")
     * @return 请求状态码status，用户信息data.userVO
     */
    @PostMapping("/updateUserEmail.api")
    public ResponMessage updateUserEmail(RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userPojo");
        String user_Email = (String) requestMessage.getData().get("user_Email");
        String oldEmail = userPojo.getUser_Email();
        String oldCode = (String) requestMessage.getData().get("oldCode");
        String rOldCode = userService.getEmailCode(oldEmail);
        String newCode = (String) requestMessage.getData().get("newCode");
        String rNewCode = userService.getEmailCode(user_Email);
        if (oldCode == null) return ResponMessage.error("请输入旧邮箱验证码！");
        if (!oldCode.equals(rOldCode)) return ResponMessage.error("旧邮箱验证码不正确！");
        if (user_Email == null) return ResponMessage.error("请输入新邮箱！");
        if (newCode == null) return ResponMessage.error("请输入新邮箱验证码！");
        if (!newCode.equals(rNewCode)) return ResponMessage.error("新邮箱验证码不正确！");
        UserVO userVO = userService.updateUserEmail(user_Email, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userPojo");
            userPojo1.setUser_Email(user_Email);
            session.setAttribute("userPojo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改电子邮箱失败");
    }

}
