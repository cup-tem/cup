package top.mnilsy.cup.service;

import org.springframework.stereotype.Service;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.dao.UserMapper;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by mnilsy on 19-4-20 下午7:15.
 */
public interface UserService {
    /**
     * 获取验证码
     *
     * @param user_Phone 用户手机号码
     * @return 返回是否获取成功
     */
    String getPhoneCode(String user_Phone);

    /**
     * 密码登录
     *
     * @param user 用户
     * @param passwd 密码
     * @return 返回是否成功密码登录成功
     */
    String getPasswdLogin(String user,String passwd);

    /**
     * 验证码登录
     *
     * @param user_Phone 用户手机号码
     * @param code 验证码
     * @return 返回是否验证码登录成功
     */
    String codeLogin(String user_Phone,String code,String sessionId);

    /**
     * 检测用户名是否唯一
     *
     * @param user_Name 用户名
     * @return 返回用户名是否唯一
     */
    String checkUserName(String user_Name);

    /**
     * 账号注册
     *
     * @param user_Phone 用户手机号码
     * @param code 验证码
     * @return 是否注册成功
     */
    String register(String user_Phone,String code);

    /**
     *设置用户名和密码
     *
     * @param user_Name 用户名
     * @param passwd 密码
     * @return userVO
     */
    String setUserNamePasswd(String user_Name,String passwd);

    /**
     *上传头像
     *
     * @param user_Head 用户头像
     * @return 是否上传头像成功
     */
    String uploadingUserHead(String user_Head);

    /**
     * 修改性别
     *
     * @param user_Sex 性别
     * @return 是否修改成功
     */
    String updateUserSex(String user_Sex);

    /**
     * 修改密码
     *
     * @param oldPasswd 用户旧密码
     * @param newPasswd 用户新密码
     * @return 是否修改成功
     */
    String updatePasswd(String oldPasswd, String newPasswd);

    UserVO codeLogin();
}
