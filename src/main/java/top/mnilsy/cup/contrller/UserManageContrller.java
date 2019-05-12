package top.mnilsy.cup.contrller;


import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.mnilsy.cup.VO.UserVO;
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
        if (user == null) return ResponMessage.error("用户不能为空");
        if (passwd == null) return ResponMessage.error("密码不能为空");
        UserPojo userPojo = userService.getPasswdLogin(user, passwd);
        if (userPojo != null) {
            session.setAttribute("userInfo", userPojo);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userPojo, userVO);
            userVO.setSessionId(session.getId());
            userService.RedundanceLogin(userVO.getUser_Name());
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("登录失败");
    }


    /**
     * 请求手机验证码
     *
     * @param requestMessage 手机号码data.get("user_Phone“）
     * @return 请求状态码status，失败信息message，会话data.sessionid
     * @author mnilsy
     */
    @PostMapping("/open/getPhoneCode.api")
    public ResponMessage getPhoneCode(@RequestBody RequestMessage requestMessage, HttpSession session) {
        String code = userService.getPhoneCode((String) requestMessage.getData().get("user_Phone"));
        if (code != null) {
            session.setAttribute("user_Phone", requestMessage.getData().get("user_Phone"));
            session.setAttribute("phoneCode", code);
            Map<String, String> map = new HashMap<>();
            map.put("sessionId", session.getId());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (session.getAttribute("user_Phone") == null || session.getAttribute("phoneCode") == null) return;
                    try {
                        Thread.sleep(300000);
                        if (session.getAttribute("user_Phone") == null || session.getAttribute("phoneCode") == null)
                            return;
                        session.removeAttribute("user_Phone");
                        session.removeAttribute("phoneCode");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
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
    public ResponMessage codeLogin(@RequestBody RequestMessage requestMessage, HttpSession session) {
        String user_Phone = (String) requestMessage.getData().get("user_Phone");
        if (user_Phone == null) return ResponMessage.error("请输入手机号码");
        String thisPhone = (String) session.getAttribute("user_Phone");
        if (!user_Phone.equals(thisPhone)) return ResponMessage.error("登录手机号与验证手机号不对应");
        String code = (String) requestMessage.getData().get("code");
        String rCode = (String) session.getAttribute("phoneCode");
        if (!code.equals(rCode)) return ResponMessage.error("验证码输入有误");
        UserPojo userPojo = userService.codeLogin(user_Phone);
        if (userPojo != null) {
            session.setAttribute("userInfo", userPojo);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userPojo, userVO);
            userVO.setSessionId(session.getId());
            //登录成功，删除验证码
            session.removeAttribute("user_Phone");
            session.removeAttribute("phoneCode");
            userService.RedundanceLogin(userVO.getUser_Name());
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("验证码登陆失败");
    }

    /**
     * 登出
     *
     * @return message
     * @author Jason_Jane
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
    public ResponMessage register(@RequestBody RequestMessage requestMessage, HttpSession session) {
        String telRegex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8-9]))[0-9]{8}$";
        String user_Phone = (String) requestMessage.getData().get("user_Phone");
        String thisPhone = (String) session.getAttribute("user_Phone");
        if (user_Phone == null) return ResponMessage.error("手机号不能为空");
        if (!user_Phone.matches(telRegex)) return ResponMessage.error("请输入正确的手机号码");
        if (!user_Phone.equals(thisPhone)) return ResponMessage.error("注册手机号与填写手机号不对应");
        String code = (String) requestMessage.getData().get("code");
        String rCode = (String) session.getAttribute("phoneCode");
        if (!code.equals(rCode)) return ResponMessage.error("手机验证码输入有误");
        int message = userService.register(user_Phone);
        if (message == 0) return ResponMessage.error("手机号已注册");
        if (message == 1) return ResponMessage.error("账号注册失败");
        return ResponMessage.ok();
    }

    /**
     * 检测用户名是否唯一
     *
     * @param requestMessage 用户名data.get("user_Name")
     * @return 请求状态码status
     * @author Jason_Jane
     */
    @PostMapping("/open/checkUserName.api")
    public ResponMessage checkUserName(@RequestBody RequestMessage requestMessage) {
        String userName = (String) requestMessage.getData().get("user_Name");
        if (userName == null) return ResponMessage.error("用户名为空");
        String checkUserName = userService.checkUserName(userName);
        if (checkUserName != null) {
            return ResponMessage.ok();
        }
        return ResponMessage.error("用户名重复");
    }

    /**
     * 设置用户名和密码
     *
     * @param requestMessage 用户名data.get("user_Name")，密码data.get("passwd")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/setUserNamePasswd.api")
    public ResponMessage setUserNamePasswd(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String user_Name = (String) requestMessage.getData().get("user_Name");
        String passwd = (String) requestMessage.getData().get("passwd");
        String passwdRegex = "(?=.*[a-z])(?=.*\\d)(?=.*[#@!~%^&*])[a-z\\d#@!~%^&*]{6,18}";
        String usernameRegex = "^[a-zA-Z0-9]{2,14}$";
        UserVO userVO = userService.setUserNamePasswd(user_Name, passwd, userPojo);
        if (user_Name == null) return ResponMessage.error("用户名不能为空");
        if (passwd == null) return ResponMessage.error("密码不能为空");
        if (!user_Name.matches(usernameRegex)) return ResponMessage.error("用户名为2-14位字母和数字,不区分大小写");
        if (!passwd.matches(passwdRegex)) return ResponMessage.error("密码必须包含字母数字符号且为6-18位");
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            userPojo1.setUser_Name(user_Name);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("设置用户名和密码失败");
    }

    /**
     * 上传头像
     *
     * @param requestMessage 头像base64编码data.get("user_Head")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/uploadingUserHead.api")
    public ResponMessage uploadingUserHead(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String user_Head = (String) requestMessage.getData().get("user_Name");
        if (user_Head == null) return ResponMessage.error("请选择上传头像图片");
        UserVO userVO = userService.uploadingUserHead(user_Head, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            BeanUtils.copyProperties(userVO, userPojo1);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("上传头像失败");
    }

    /**
     * 上传背景图
     *
     * @param requestMessage 背景base64编码data.get("user_Background")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/uploadingUserBackgroundUrl.api")
    public ResponMessage uploadingUserBackgroundUrl(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String user_Background = (String) requestMessage.getData().get("user_Background");
        if (user_Background == null) return ResponMessage.error("请选择上传的背景图图片");
        UserVO userVO = userService.uploadingBackground(user_Background, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            BeanUtils.copyProperties(userVO, userPojo1);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("上传背景图失败");
    }

    /**
     * 修改昵称
     *
     * @param requestMessage 用户昵称data.get("user_NickName")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/updateUserNickName.api")
    public ResponMessage updateUserNickName(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String user_NickName = (String) requestMessage.getData().get("user_NickName");
        String NickNameRegex = "^.{1,14}$";
        if (user_NickName == null) return ResponMessage.error("修改昵称不能为空");
        if (!user_NickName.matches(NickNameRegex)) return ResponMessage.error("昵称不能超过14位！");
        UserVO userVO = userService.updateUserNickName(user_NickName, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            userPojo1.setUser_NickName(user_NickName);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改昵称失败");
    }

    /**
     * 修改性别
     *
     * @param requestMessage 用户性别data.get("user_Sex")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/updateUserSex.api")
    public ResponMessage updateUserSex(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String userOldSex = userPojo.getUser_Sex();
        String user_Sex = (String) requestMessage.getData().get("user_Sex");
        String userMan = "男";
        String userLady = "女";
        if (user_Sex == null) return ResponMessage.error("修改性别不能为空");
        if (!user_Sex.equals(userMan) || !user_Sex.equals(userLady))
            return ResponMessage.error("输入的性别不是“男”或“女”，请重新输入！");
        if (user_Sex.equals(userOldSex)) return ResponMessage.error("性别一样！修改失败！");
        UserVO userVO = userService.updateUserSex(user_Sex, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            userPojo1.setUser_Sex(user_Sex);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改性别失败");
    }

    /**
     * 修改密码
     *
     * @param requestMessage 用户旧密码data.get("oldPasswd")，用户新密码data.get("newPasswd")
     * @return 请求状态码status，失败信息message
     * @author Jason_Jane
     */
    @PostMapping("/updatePasswd.api")
    public ResponMessage updatePasswd(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String oldPasswd = (String) requestMessage.getData().get("oldPasswd");
        String newPasswd = (String) requestMessage.getData().get("newPasswd");
        String passwdRegex = "(?=.*[a-z])(?=.*\\d)(?=.*[#@!~%^&*?$(){};:'])[a-z\\d#@!~%^&*?$(){};:']{6,18}";
        if (oldPasswd == null) return ResponMessage.error("请输入旧密码");
        if (newPasswd == null) return ResponMessage.error("请输入新密码");
        if (oldPasswd.equals(newPasswd)) return ResponMessage.error("新旧密码不能一样");
        if (!newPasswd.matches(passwdRegex)) return ResponMessage.error("密码必须包含字母数字符号且为6-18位");
        int message = userService.updatePasswd(oldPasswd, newPasswd, userPojo);
        if (message == 0) {
            return ResponMessage.error("旧密码不正确");
        } else if (message == 1) {
            return ResponMessage.error("修改密码失败");
        } else {
            return ResponMessage.ok();
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
    public ResponMessage retrievePasswd(@RequestBody RequestMessage requestMessage, HttpSession session) {
        String newPasswd = (String) requestMessage.getData().get("newPasswd");
        String passwdRegex = "(?=.*[a-z])(?=.*\\d)(?=.*[#@!~%^&*?$(){};:'])[a-z\\d#@!~%^&*?$(){};:']{6,18}";
        String user_Phone = (String) session.getAttribute("user_Phone");
        String rCode = (String) session.getAttribute("phoneCode");
        String code = (String) requestMessage.getData().get("code");
        if (!code.equals(rCode)) return ResponMessage.error("手机验证码输入有误");
        if(!newPasswd.matches(passwdRegex))return ResponMessage.error("密码必须包含字母数字符号且为6-18位");
        int message = userService.retrievePasswd(newPasswd, user_Phone);
        if (message == 0) return ResponMessage.error("不存在该手机账号");
        if (message == 1) return ResponMessage.error("新旧密码一样");
        if (message == 2) return ResponMessage.error("修改密码失败");
        return ResponMessage.ok();
    }

    /**
     * 修改手机号码
     *
     * @param requestMessage 用户新手机号码data.get("user_Phone")，手机验证码data.get("code")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/updateUserPhone.api")
    public ResponMessage updateUserPhone(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String telRegex = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8-9]))[0-9]{8}$";
        String oldPhone = userPojo.getUser_Phone();
        String user_Phone = (String) requestMessage.getData().get("user_Phone");
        String rCode = userService.getPhoneCode(user_Phone);
        String code = (String) requestMessage.getData().get("code");
        if (oldPhone.equals(user_Phone)) return ResponMessage.error("新旧手机号码一样");
        if (user_Phone == null) return ResponMessage.error("请输入新手机号码");
        if (!user_Phone.matches(telRegex)) return ResponMessage.error("手机格式不正确！请重新输入正确的手机号码！");
        if (!code.equals(rCode)) return ResponMessage.error("输入的手机验证码有误");
        UserVO userVO = userService.updateUserPhone(user_Phone, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            userPojo1.setUser_Phone(user_Phone);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改手机号码失败");
    }

    /**
     * 请求电子邮箱验证码
     *
     * @param requestMessage 用户电子邮箱data.get("user_Email")
     * @return 请求状态码status，失败信息message
     * @author Jason_Jane
     */
    @GetMapping("/open/getEmailCode.api")
    public ResponMessage getEmailCode(@RequestBody RequestMessage requestMessage, HttpSession session) {
        String eCode = userService.getEmailCode((String) requestMessage.getData().get("user_Email"));
        if (eCode != null) {
            session.setAttribute("user_Email", requestMessage.getData().get("user_Email"));
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
     * @author Jason_Jane
     */
    @PostMapping("/bindUserEmail.api")
    public ResponMessage bindUserEmail(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String emailRegex = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
        String user_Email = (String) requestMessage.getData().get("user_Email");
        if (user_Email == null) return ResponMessage.error("请输入电子邮箱");
        if (!user_Email.matches(emailRegex)) return ResponMessage.error("电子邮箱格式不对！请重新输入正确的电子邮箱");
        String thisEmail = (String) session.getAttribute("user_Email");
        if (!user_Email.equals(thisEmail)) return ResponMessage.error("输入的电子邮箱和申请验证的电子邮箱不对应");
        String code = (String) requestMessage.getData().get("code");
        if (code == null) return ResponMessage.error("请输入验证码");
        String rCode = (String) session.getAttribute("eCode");
        if (!code.equals(rCode)) return ResponMessage.error("验证码不正确");
        UserVO userVO = userService.bindUserEmail(user_Email, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            userPojo1.setUser_Email(user_Email);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("绑定电子邮箱失败");
    }

    /**
     * 修改电子邮箱
     *
     * @param requestMessage 用户新邮箱data.get("user_Email")，新邮箱验证码data.get("newCode")，旧邮箱验证码data.get("oldCode")
     * @return 请求状态码status，用户信息data.userVO
     * @author Jason_Jane
     */
    @PostMapping("/updateUserEmail.api")
    public ResponMessage updateUserEmail(@RequestBody RequestMessage requestMessage, HttpSession session) {
        UserPojo userPojo = (UserPojo) session.getAttribute("userInfo");
        String emailRegex = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
        String oldEmail = userPojo.getUser_Email();
        String thisOldEmail = (String) session.getAttribute("user_Email");
        if (!oldEmail.equals(thisOldEmail)) return ResponMessage.error("填写的旧邮箱与申请验证的旧邮箱不对应");
        String oldCode = (String) requestMessage.getData().get("oldCode");
        if (oldCode == null) return ResponMessage.error("请输入旧邮箱验证码");
        String rOldCode = (String) session.getAttribute("eCode");
        if (!oldCode.equals(rOldCode)) return ResponMessage.error("旧邮箱验证码不正确");
        String user_Email = (String) requestMessage.getData().get("user_Email");
        if (user_Email == null) return ResponMessage.error("请输入新邮箱");
        if (!user_Email.matches(emailRegex)) return ResponMessage.error("电子邮箱格式不对！请重新输入正确的电子邮箱");
        String newCode = (String) requestMessage.getData().get("newCode");
        if (newCode == null) return ResponMessage.error("请输入新邮箱验证码");
        String rNewCode = (String) session.getAttribute("eCode");
        if (!newCode.equals(rNewCode)) return ResponMessage.error("新邮箱验证码不正确");
        UserVO userVO = userService.updateUserEmail(user_Email, userPojo);
        if (userVO != null) {
            UserPojo userPojo1 = (UserPojo) session.getAttribute("userInfo");
            userPojo1.setUser_Email(user_Email);
            session.setAttribute("userInfo", userPojo1);
            return ResponMessage.ok(userVO);
        }
        return ResponMessage.error("修改电子邮箱失败");
    }

}
