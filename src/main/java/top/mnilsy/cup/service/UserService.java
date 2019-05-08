package top.mnilsy.cup.service;

import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.pojo.UserPojo;

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
     * 获取邮箱验证码
     *
     * @param user_Email 用户邮箱
     * @return 返回是否获取成功
     * @author Jason_Jane
     */
    String getEmailCode(String user_Email);

    /**
     * 密码登录
     *
     * @param user   用户
     * @param passwd 密码
     * @return 返回是否成功密码登录成功
     * @author Jason_Jane
     */
    UserPojo getPasswdLogin(String user, String passwd);

    /**
     * 获取用户
     *
     * @param user_Name 用户名
     * @return userVO
     * @author Jason_Jane
     */
    UserVO getUserByUsername(String user_Name);

    /**
     * 验证码登录
     *
     * @param user_Phone 用户手机号码
     * @param code       验证码
     * @return 返回是否验证码登录成功
     * @author Jason_Jane
     */
    UserPojo codeLogin(String user_Phone, String code);

    /**
     * 检测用户名是否唯一
     *
     * @param user_Name 用户名
     * @return 返回用户名是否唯一
     * @author Jason_Jane
     */
    String checkUserName(String user_Name);

    /**
     * 账号注册
     *
     * @param user_Phone 用户手机号码
     * @param code       验证码
     * @return 是否注册成功
     * @author Jason_Jane
     */
    UserPojo register(String user_Phone, String code);

    /**
     * 设置用户名和密码
     *
     * @param user_Name 用户名
     * @param passwd    密码
     * @return userVO
     * @author Jason_Jane
     */
    UserVO setUserNamePasswd(String user_Name, String passwd, UserPojo userPojo);

    /**
     * 上传头像
     *
     * @param user_Head 用户头像
     * @return 是否上传头像成功
     * @author Jason_Jane
     */
    String uploadingUserHead(String user_Head);

    /**
     * 修改性别
     *
     * @param user_Sex 性别
     * @param userPojo 用户信息
     * @return 是否修改成功
     * @author Jason_Jane
     */
    UserVO updateUserSex(String user_Sex, UserPojo userPojo);

    /**
     * 修改密码
     *
     * @param oldPasswd 用户旧密码
     * @param newPasswd 用户新密码
     * @param userPojo  用户信息
     * @return 是否修改成功
     * @author Jason_Jane
     */
    String updatePasswd(String oldPasswd, String newPasswd, UserPojo userPojo);

    /**
     * 找回密码
     *
     * @param newPasswd 用户新密码
     * @param code      手机验证码
     * @return 是否找回成功
     * @author Jason_Jane
     */
    String retrievePasswd(String newPasswd, String code, UserPojo userPojo);

    /**
     * 找回密码
     *
     * @param user_Phone 用户手机号
     * @param code       手机验证码
     * @return userVO
     * @author Jason_Jane
     */
    UserVO updateUserPhone(String user_Phone, String code, String oldPhone);

    /**
     * 绑定电子邮箱
     *
     * @param user_Email 电子邮箱
     * @param code       邮箱验证码
     * @param userPojo   用户
     * @return userVO
     */
    UserVO bindUserEmail(String user_Email, String code, UserPojo userPojo);

    /**
     * 修改电子邮箱
     *
     * @param user_Email 电子邮箱
     * @param newCode    新邮箱验证码
     * @param oldCode    旧邮箱验证码
     * @param userPojo   用户
     * @return userVO
     */
    UserVO updateUserEmail(String user_Email, String newCode, String oldCode, UserPojo userPojo);

    /**
     * 修改昵称
     *
     * @param user_NickName 昵称
     * @param userPojo      用户
     * @return userVO
     */
    UserVO updateUserNickName(String user_NickName, UserPojo userPojo);

    /**
     * 判断该账户是否已经在线，如是，通知其下线
     *
     * @param user_Name 用户名
     * @author mnilsy
     */
    void RedundanceLogin(String user_Name);
}
