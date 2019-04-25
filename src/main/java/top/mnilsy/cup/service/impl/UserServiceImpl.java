package top.mnilsy.cup.service.impl;

import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;

import top.mnilsy.cup.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

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
     *密码登录
     */
    @Override
    public String getPasswdLogin(String user,String passwd) {
        UserMapper userMapper = null;
        UserVO userVO = null;
        UserPojo userPojo = null;
        PasswdPojo passwdPojo = null;
        if (user != null || passwd != null){
           userPojo = userMapper.getUserByNamePhoneEmail(user);
           String name = userPojo.getUser_Name();
           String id = userPojo.getUser_Id();
           userVO = userMapper.getUserByNameInfo(name);
           passwdPojo = userMapper.getPasswdById(id);
           String pw = passwdPojo.getPasswd_Normal();
           if (pw.equals(passwd)){
               return userVO.toString();
           }else {
               return null;
           }
        }
        return null;
    }

    /**
     *验证码登录
     */
    @Override
    public String codeLogin(String user_Phone, String code, String sessionId) {
        return null;
    }

    /**
     *检测用户名是否唯一
     */
    @Override
    public String checkUserName(String user_Name) {
        UserPojo userPojo = null;
        UserMapper userMapper = null;
        userPojo = userMapper.getUserNameByUserName(user_Name);
        if (userPojo != null){
            return null;
        }
        return "用户名可用";
    }

    /**
     *账号注册
     */
    @Override
    public String register(String user_Phone, String code) {
        return null;
    }

    /**
     * 设置用户名和密码
     */
    @Override
    public String setUserNamePasswd(String user_Name, String passwd) {
        return null;
    }

    /**
     * 上传头像
     */
    @Override
    public String uploadingUserHead(String user_Head) {
        return null;
    }

    /**
     * 修改性别
     */
    @Override
    public String updateUserSex(String user_Sex) {
        return null;
    }

    /**
     * 修改密码
     */
    @Override
    public String updatePasswd(String oldPasswd, String newPasswd) {
        return null;
    }

    @Override
    public UserVO codeLogin() {
        return null;
    }
}
