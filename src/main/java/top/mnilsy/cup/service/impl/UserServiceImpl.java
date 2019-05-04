package top.mnilsy.cup.service.impl;

import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;

import top.mnilsy.cup.service.UserService;
import top.mnilsy.cup.utils.SendMailUtil;

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

    /**
     *获取验证码
     */
    @Override
    public String getPhoneCode(String user_Phone) {
        System.out.println("输出一个验证码");
        return "TestCode";
    }

    /**
     *获取邮箱验证码
     */
    @Override
    public String getEmailCode(String user_Email) {
        String ecode = "TestEcode";
        SendMailUtil sendMailUtil = new SendMailUtil();
        sendMailUtil.send(user_Email,ecode);
        return ecode;
    }

    /**
     *密码登录
     *
     * @author Jason_Jane
     */
    @Override
    public UserPojo getPasswdLogin(String user,String passwd) {
        UserMapper userMapper = null;
        UserPojo userPojo = null;
        PasswdPojo passwdPojo = null;
        if (user != null || passwd != null){
           userPojo = userMapper.getUserByNamePhoneEmail(user);
           String name = userPojo.getUser_Name();
           String id = userPojo.getUser_Id();
           passwdPojo = userMapper.getPasswdById(id);
           String pw = passwdPojo.getPasswd_Normal();
           if (pw.equals(passwd)){
               return userPojo;
           }else {
               return null;
           }
        }
        return null;
    }

    @Override
    public UserVO getUserByUsername(String user_Name) {
        UserVO userVO = null;
        UserMapper userMapper = null;
        userVO = userMapper.getUserByName(user_Name);
        if (userVO != null){
            return userVO;
        }
        return null;
    }

    /**
     *验证码登录
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO codeLogin(String user_Phone,String code) {
        UserVO userVO = null;
        UserMapper userMapper = null;
        if (this.getPhoneCode(user_Phone).equals(code)){
            userVO = userMapper.getUserByPhone(user_Phone);
            if (userVO != null){
                return userVO;
            }
            return null;
        }
        return null;
    }

    /**
     *检测用户名是否唯一
     *
     * @author Jason_Jane
     */
    @Override
    public String checkUserName(String user_Name) {
        UserPojo userPojo = null;
        UserMapper userMapper = null;
        userPojo = userMapper.getUserByUserName(user_Name);
        if (userPojo != null){
            return null;
        }
        return "用户名可用";
    }

    /**
     *账号注册
     *
     * @author Jason_Jane
     */
    @Override
    public String register(String user_Phone, String code) {
        UserVO userVO = null;
        UserPojo userPojo = null;
        UserMapper userMapper = null;
        String telRegex = "^[1](([3|5|8][\\\\d])|([4][5-9])|([6][5,6])|([7][3-8])|([9][8,9]))[\\\\d]{8}$";
        String thiscode = this.getPhoneCode(user_Phone);
        userPojo = userMapper.getUserByPhoneInfo(user_Phone);
        if (user_Phone != null){
            if (userPojo != null){
                if (user_Phone.matches(telRegex)){
                    if (thiscode.equals(code)){
                        userPojo.setUser_Phone(user_Phone);
                        userMapper.addUserByPhoneInfo(userPojo);
                        return userPojo.toString();
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
    public UserVO setUserNamePasswd(String user_Name, String passwd,HttpSession session) {
        if (user_Name != null && passwd != null){
            UserPojo userPojo = null;
            UserVO userVO = null;
            PasswdPojo passwdPojo = null;
            UserMapper userMapper = null;
            userPojo = userMapper.getUserByPhoneInfo(session.getAttribute("user_Phone").toString());
            userPojo.setUser_Name(user_Name);
            passwdPojo.setPasswd_Normal(passwd);
            userMapper.setUserNameByPhoneInfo(userPojo);
            String userId = userPojo.getUser_Id();
            passwdPojo.setUser_Id(userId);
            passwdPojo.setPasswd_Normal(passwd);
            userMapper.setPasswd(passwdPojo);
            userVO = userMapper.getUserByName(userPojo.getUser_Name());
            return userVO;
        }
        return null;
    }

    /**
     * 上传头像
     *
     * @author Jason_Jane
     */
    @Override
    public String uploadingUserHead(String user_Head) {
        return null;
    }

    /**
     * 修改性别
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserSex(String user_Sex,UserVO userVO) {
       UserMapper userMapper = null;
       String userSex = userVO.getUser_Sex();
       if (userSex.equals(user_Sex)){
           return null;
       }
       userVO.setUser_Sex(user_Sex);
       userMapper.updateUserSex(userVO);
       return userVO;
    }

    /**
     * 修改密码
     *
     * @author Jason_Jane
     */
    @Override
    public String updatePasswd(String oldPasswd, String newPasswd,PasswdPojo passwdPojo) {
        UserMapper userMapper = null;
        if (oldPasswd.equals(newPasswd)){
            return null;
        }
        String passwdOld2 = passwdPojo.getPasswd_Old2();
        String passwdOld1 = passwdPojo.getPasswd_Old1();
        passwdPojo.setPasswd_Old3(passwdOld2);
        passwdPojo.setPasswd_Old2(passwdOld1);
        passwdPojo.setPasswd_Old1(oldPasswd);
        passwdPojo.setPasswd_Normal(newPasswd);
        userMapper.updatePasswd(passwdPojo);
        return "seccess";
    }

    /**
     * 找回密码
     *
     * @author Jason_Jane
     */
    @Override
    public String retrievePasswd(String newPasswd, String code,UserVO userVO) {
        UserPojo userPojo = null;
        PasswdPojo passwdPojo = null;
        UserMapper userMapper = null;
        String userPhone = userVO.getUser_Phone();
        if (this.getPhoneCode(userPhone).equals(code)){
            userPojo = userMapper.getUserByPhoneInfo(userPhone);
            String userId = userPojo.getUser_Id();
            passwdPojo = userMapper.getPasswdById(userId);
            String passwdOld2 = passwdPojo.getPasswd_Old2();
            String passwdOld1 = passwdPojo.getPasswd_Old1();
            String passwdNormal = passwdPojo.getPasswd_Normal();
            passwdPojo.setPasswd_Old3(passwdOld2);
            passwdPojo.setPasswd_Old2(passwdOld1);
            passwdPojo.setPasswd_Old1(passwdNormal);
            passwdPojo.setPasswd_Normal(newPasswd);
            userMapper.findPasswd(passwdPojo);
            return "success";
        }
        return null;
    }

    /**
     * 修改手机号
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserPhone(String user_Phone, String code,String oldPhone) {
        UserVO userVO = null;
        UserMapper userMapper = null;
        if (this.getPhoneCode(user_Phone).equals(code)){
            userVO = userMapper.getUserByPhone(oldPhone);
            userVO.setUser_Phone(user_Phone);
            userMapper.updatePhone(userVO,oldPhone);
            return userVO;
        }
        return null;
    }

    /**
     * 绑定邮箱
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO bindUserEmail(String user_Email, String code, UserVO userVO) {
        UserMapper userMapper = null;
        if (this.getEmailCode(user_Email).equals(code)){
            userVO.setUser_Email(user_Email);
            userMapper.bindUserEmail(userVO);
            return userVO;
        }
        return null;
    }

    /**
     * 修改邮箱
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserEmail(String user_Email, String newCode, String oldCode, UserVO userVO) {
        UserMapper userMapper =null;
        String oldEmail = userVO.getUser_Email();
        if (this.getEmailCode(oldEmail).equals(oldCode)){
            if (this.getEmailCode(user_Email).equals(newCode)){
                userVO.setUser_Email(user_Email);
                userMapper.updateUserEmail(userVO);
                return userVO;
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
    public UserVO updateUserNickName(String user_NickName, UserVO userVO) {
        UserMapper userMapper = null;
        String userName = userVO.getUser_Name();
        if (user_NickName != null){
            userVO.setUser_NickName(user_NickName);
            userMapper.updateUserNickName(userVO);
            return userVO;
        }
        return null;
    }

    @Override
    public UserVO codeLogin() {
        return null;
    }
}
