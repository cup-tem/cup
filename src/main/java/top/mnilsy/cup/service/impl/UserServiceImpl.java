package top.mnilsy.cup.service.impl;

import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;

import top.mnilsy.cup.service.UserService;
import top.mnilsy.cup.utils.SendMailUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mnilsy on 19-4-20 下午7:28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    /**
     * 获取验证码
     */
    @Override
    public String getPhoneCode(String user_Phone) {
        String code=(int)(Math.random()*10)+""+(int)(Math.random()*10)+""+(int)(Math.random()*10)+""+(int)(Math.random()*10);
        System.out.println("输出一个验证码");
        return "TestCode";
    }

    /**
     * 获取邮箱验证码
     */
    @Override
    public String getEmailCode(String user_Email) {
        String ecode = "TestEcode";
        SendMailUtil sendMailUtil = new SendMailUtil();
        sendMailUtil.send(user_Email, ecode);
        return ecode;
    }

    /**
     * 密码登录
     *
     * @author Jason_Jane
     */
    @Override
    public UserPojo getPasswdLogin(String user, String passwd) {
        return userMapper.loginBypasswd(user, passwd);
    }

    @Override
    public UserVO getUserByUsername(String user_Name) {
        UserVO userVO = userMapper.getUserByName(user_Name);
        if (userVO != null) {
            return userVO;
        }
        return null;
    }

    /**
     * 验证码登录
     *
     * @author Jason_Jane
     */
    @Override
    public UserPojo codeLogin(String user_Phone, String code) {
        if (this.getPhoneCode(user_Phone).equals(code)) {
            UserPojo userPojo = userMapper.getUserByPhoneInfo(user_Phone);
            if (userPojo != null) {
                return userPojo;
            }
            return null;
        }
        return null;
    }

    /**
     * 检测用户名是否唯一
     *
     * @author Jason_Jane
     */
    @Override
    public String checkUserName(String user_Name) {
        UserPojo userPojo = userMapper.getUserByUserName(user_Name);
        if (userPojo != null) {
            return null;
        }
        return "用户名可用";
    }

    /**
     * 账号注册
     *
     * @author Jason_Jane
     */
    @Override
    public UserPojo register(String user_Phone, String code) {
        UserPojo userPojo = new UserPojo();
        PasswdPojo passwdPojo = new PasswdPojo();
        String telRegex = "^[1](([3|5|8][\\\\d])|([4][5-9])|([6][5,6])|([7][3-8])|([9][8,9]))[\\\\d]{8}$";
        String thiscode = this.getPhoneCode(user_Phone);
        userPojo = userMapper.getUserByPhoneInfo(user_Phone);
        if (user_Phone != null) {
            if (user_Phone.matches(telRegex)) {
                if (thiscode.equals(code)) {
                    userPojo.setUser_Phone(user_Phone);
                    int adduser = userMapper.addUserByPhoneInfo(userPojo);
                    if (adduser == 1) {
                        passwdPojo.setUser_Id(userPojo.getUser_Id());
                        int addpasswd = userMapper.setPasswd(passwdPojo);
                        if (addpasswd == 1) {
                            return userPojo;
                        }
                        return null;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /**
     * 设置用户名和密码
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO setUserNamePasswd(String user_Name, String passwd, UserPojo userPojo) {
        PasswdPojo passwdPojo = new PasswdPojo();
        UserVO userVO = new UserVO();
        if (user_Name != null && passwd != null) {
            userPojo.setUser_Name(user_Name);
            int number = userMapper.setUserNameByPhoneInfo(userPojo);
            if (number == 1) {
                String userId = userPojo.getUser_Id();
                passwdPojo.setUser_Id(userId);
                passwdPojo.setPasswd_Normal(passwd);
                int setPasswd = userMapper.setPasswd(passwdPojo);
                if (setPasswd == 1) {
                    userVO = userMapper.getUserByName(userPojo.getUser_Name());
                    if (userVO != null) {
                        return userVO;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /**
     * 上传头像
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO uploadingUserHead(String user_Head, UserPojo userPojo) {
        UserVO userVO = userMapper.getUserByName(userPojo.getUser_Name());
        userVO.setUser_HeadUrl_max(user_Head);
        userVO.setUser_HeadUrl_min(user_Head);
        int updateHead = userMapper.updateUserHead(userVO);
        if (updateHead == 1) {
            return userVO;
        }
        return null;
    }

    /**
     * 上传背景图
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO uploadingBackground(String user_Background, UserPojo userPojo) {
        UserVO userVO = userMapper.getUserByName(userPojo.getUser_Name());
        userVO.setUser_BackgroundUrl(user_Background);
        int updateBackground = userMapper.updateBackground(userVO);
        if (updateBackground == 1) {
            return userVO;
        }
        return null;
    }

    /**
     * 修改性别
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserSex(String user_Sex, UserPojo userPojo) {
        UserVO userVO = new UserVO();
        String userSex = userPojo.getUser_Sex();
        if (userSex.equals(user_Sex)) {
            return null;
        }
        userVO = userMapper.getUserByName(userPojo.getUser_Name());
        if (userVO != null) {
            userVO.setUser_Sex(user_Sex);
            int updateSex = userMapper.updateUserSex(userVO);
            if (updateSex == 1) {
                return userVO;
            }
            return null;
        }
        return null;
    }

    /**
     * 修改密码
     *
     * @author Jason_Jane
     */
    @Override
    public String updatePasswd(String oldPasswd, String newPasswd, UserPojo userPojo) {
        PasswdPojo passwdPojo = new PasswdPojo();
        if (oldPasswd.equals(newPasswd)) {
            return null;
        }
        passwdPojo = userMapper.getPasswdById(userPojo.getUser_Id());
        if (passwdPojo != null) {
            String passwdOld2 = passwdPojo.getPasswd_Old2();
            String passwdOld1 = passwdPojo.getPasswd_Old1();
            passwdPojo.setPasswd_Old3(passwdOld2);
            passwdPojo.setPasswd_Old2(passwdOld1);
            passwdPojo.setPasswd_Old1(oldPasswd);
            passwdPojo.setPasswd_Normal(newPasswd);
            int updatePasswd = userMapper.updatePasswd(passwdPojo);
            if (updatePasswd == 1) {
                return "seccess";
            }
            return null;
        }
        return null;
    }

    /**
     * 找回密码
     *
     * @author Jason_Jane
     */
    @Override
    public String retrievePasswd(String newPasswd, String code, UserPojo userPojo) {
        PasswdPojo passwdPojo = new PasswdPojo();
        String userPhone = userPojo.getUser_Phone();
        if (this.getPhoneCode(userPhone).equals(code)) {
            String userId = userPojo.getUser_Id();
            passwdPojo = userMapper.getPasswdById(userId);
            if (passwdPojo != null) {
                String passwdOld2 = passwdPojo.getPasswd_Old2();
                String passwdOld1 = passwdPojo.getPasswd_Old1();
                String passwdNormal = passwdPojo.getPasswd_Normal();
                passwdPojo.setPasswd_Old3(passwdOld2);
                passwdPojo.setPasswd_Old2(passwdOld1);
                passwdPojo.setPasswd_Old1(passwdNormal);
                passwdPojo.setPasswd_Normal(newPasswd);
                int findPasswd = userMapper.findPasswd(passwdPojo);
                if (findPasswd == 1) {
                    return "success";
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /**
     * 修改手机号
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserPhone(String user_Phone, String code, String oldPhone) {
        UserVO userVO = new UserVO();
        if (this.getPhoneCode(user_Phone).equals(code)) {
            userVO = userMapper.getUserByPhone(oldPhone);
            if (userVO != null) {
                userVO.setUser_Phone(user_Phone);
                int updatePhone = userMapper.updatePhone(userVO, oldPhone);
                if (updatePhone == 1) {
                    return userVO;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /**
     * 绑定邮箱
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO bindUserEmail(String user_Email, String code, UserPojo userPojo) {
        UserVO userVO = new UserVO();
        if (this.getEmailCode(user_Email).equals(code)) {
            userVO = userMapper.getUserByName(userPojo.getUser_Name());
            if (userVO != null) {
                userVO.setUser_Email(user_Email);
                int bindEmail = userMapper.bindUserEmail(userVO);
                if (bindEmail == 1) {
                    return userVO;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /**
     * 修改邮箱
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserEmail(String user_Email, String newCode, String oldCode, UserPojo userPojo) {
        UserVO userVO = new UserVO();
        String oldEmail = userPojo.getUser_Email();
        if (this.getEmailCode(oldEmail).equals(oldCode)) {
            if (this.getEmailCode(user_Email).equals(newCode)) {
                userVO = userMapper.getUserByName(userPojo.getUser_Name());
                if (userVO != null) {
                    userVO.setUser_Email(user_Email);
                    int updateEmail = userMapper.updateUserEmail(userVO);
                    if (updateEmail == 1) {
                        return userVO;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /**
     * 修改昵称
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserNickName(String user_NickName, UserPojo userPojo) {
        UserVO userVO = new UserVO();
        String userName = userPojo.getUser_Name();
        if (user_NickName != null) {
            userVO = userMapper.getUserByName(userName);
            if (userVO != null) {
                userVO.setUser_NickName(user_NickName);
                int updateNickName = userMapper.updateUserNickName(userVO);
                if (updateNickName == 1) {
                    return userVO;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public void RedundanceLogin(String user_Name) {

    }
}
