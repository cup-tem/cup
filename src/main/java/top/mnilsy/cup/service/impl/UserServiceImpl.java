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
        UserPojo userPojo = userMapper.getUserByNamePhoneEmail(user,passwd);
        if (userPojo != null){
            return userPojo;
        }
        return null;
    }

    @Override
    public UserVO getUserByUsername(String user_Name) {
        UserVO userVO = userMapper.getUserByName(user_Name);
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
    public UserPojo codeLogin(String user_Phone) {
        UserPojo userPojo = userMapper.getUserByPhoneInfo(user_Phone);
        if (userPojo != null){
            return userPojo;
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
        UserPojo userPojo = userMapper.getUserByUserName(user_Name);
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
    public int register(String user_Phone) {
        UserPojo userPojo1 = userMapper.getUserByPhoneInfo(user_Phone);
        if (userPojo1 == null){
            UserPojo userPojo = new UserPojo(user_Phone);
            int status = userMapper.addUserByPhoneInfo(userPojo);
            if (status == 1){
                return 2;
            }
            return 1;
        }
        return 0;
    }

    /**
     * 设置用户名和密码
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO setUserNamePasswd(String user_Name, String passwd,UserPojo userPojo) {
        String user_Id = userPojo.getUser_Id();
        int name = userMapper.setUserNameById(user_Name,user_Id);
        int pass = userMapper.setPasswd(passwd,user_Id);
        if (name == 1 && pass ==1){
            UserVO userVO = userMapper.getUserByName(user_Name);
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
    public UserVO uploadingUserHead(String user_Head,UserPojo userPojo) {
        UserVO userVO = userMapper.getUserByName(userPojo.getUser_Name());
        userVO.setUser_HeadUrl_max(user_Head);
        userVO.setUser_HeadUrl_min(user_Head);
        int updateHead = userMapper.updateUserHead(userVO);
        if (updateHead == 1){
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
        if (updateBackground == 1){
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
    public UserVO updateUserSex(String user_Sex,UserPojo userPojo) {
        String user_Id = userPojo.getUser_Id();
       int sex = userMapper.updateUserSex(user_Sex,user_Id);
       if (sex == 1){
           String user_Name = userPojo.getUser_Name();
           UserVO userVO = userMapper.getUserByName(user_Name);
           return userVO;
       }
       return null;
    }

    /**
     * 修改密码
     *
     * @author Jason_Jane
     */
    @Override
    public int updatePasswd(String oldPasswd, String newPasswd,UserPojo userPojo) {
        String user_Id = userPojo.getUser_Id();
        PasswdPojo passwdPojo = userMapper.getPasswdById(user_Id);
        if (oldPasswd.equals(passwdPojo.getPasswd_Normal())){
            String passwd_Old3 = passwdPojo.getPasswd_Old2();
            String passwd_Old2 = passwdPojo.getPasswd_Old1();
            String passwd_Old1 = oldPasswd;
            String passwd_Normal = newPasswd;
            System.out.println(passwd_Normal);
            System.out.println(passwd_Old1);
            System.out.println(passwd_Old2);
            System.out.println(passwd_Old3);
            int updatePasswd = userMapper.updatePasswd(passwd_Normal,passwd_Old1,passwd_Old2,passwd_Old3,user_Id);
            if (updatePasswd == 1){
                return 2;
            }
            return 1;
        }
        return 0;
    }

    /**
     * 找回密码
     *
     * @author Jason_Jane
     */
    @Override
    public int retrievePasswd(String newPasswd,UserPojo userPojo) {
        String user_Id = userPojo.getUser_Id();
        PasswdPojo passwdPojo = userMapper.getPasswdById(user_Id);
        if (!newPasswd.equals(passwdPojo.getPasswd_Normal())){
            String passwd_Old3 = passwdPojo.getPasswd_Old2();
            String passwd_Old2 = passwdPojo.getPasswd_Old1();
            String passwd_Old1 = passwdPojo.getPasswd_Normal();
            String passwd_Normal = newPasswd;
            int status = userMapper.findPasswd(passwd_Normal,passwd_Old1,passwd_Old2,passwd_Old3,user_Id);
            if (status == 1){
                return 2;
            }
            return 1;
        }
        return 0;
    }

    /**
     * 修改手机号
     *
     * @author Jason_Jane
     */
    @Override
    public UserVO updateUserPhone(String user_Phone,UserPojo userPojo) {
        String user_Id = userPojo.getUser_Id();
        int status = userMapper.updatePhone(user_Phone,user_Id);
        if (status == 1){
            String user_Name = userPojo.getUser_Name();
            UserVO userVO = userMapper.getUserByName(user_Name);
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
    public UserVO bindUserEmail(String user_Email,UserPojo userPojo) {
            String user_Id = userPojo.getUser_Id();
            int status = userMapper.bindUserEmail(user_Email,user_Id);
            if (status == 1){
                String user_Name = userPojo.getUser_Name();
                UserVO userVO = userMapper.getUserByName(user_Name);
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
    public UserVO updateUserEmail(String user_Email,UserPojo userPojo) {
        String user_Id = userPojo.getUser_Id();
        int status = userMapper.updateUserEmail(user_Email,user_Id);
        if (status == 1){
            String user_Name = userPojo.getUser_Name();
            UserVO userVO = userMapper.getUserByName(user_Name);
            return userVO;
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
        int nickName = userMapper.updateUserNickName(user_NickName,userPojo.getUser_Id());
        if (nickName == 1){
            String user_Name = userPojo.getUser_Name();
            UserVO userVO = userMapper.getUserByName(user_Name);
            return userVO;
        }
        return null;
    }

    @Override
    public void RedundanceLogin(String user_Name) {

    }
}
