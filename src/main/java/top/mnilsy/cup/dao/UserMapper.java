package top.mnilsy.cup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.mnilsy.cup.VO.UserVO;
import top.mnilsy.cup.pojo.PasswdPojo;
import top.mnilsy.cup.pojo.UserPojo;

import java.util.List;


/**
 * Created by mnilsy on 19-4-20 上午12:22.
 */
@Repository("userMapper")
@Mapper
public interface UserMapper {
    /**
     * 根据user_Id获取用户基本信息
     *
     * @param user_Id 用户账号id
     * @return 用户基本资料
     */
    @Select("select * from user where user_Id = #{user_Id}")
    UserPojo getUserByIdInfo(String user_Id);

    /**
     * 根据user_Phone获取用户基本信息
     *
     * @param user_Phone 用户手机号
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Phone = #{user_Phone}")
    UserPojo getUserByPhoneInfo(String user_Phone);

    /**
     * 根据user_Phone获取用户基本信息
     *
     * @param user_Phone 用户手机号
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Phone = #{user_Phone}")
    UserVO getUserByPhone(String user_Phone);

    /**
     * 根据user_Name获取用户基本信息
     *
     * @param user_Name 用户名
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Name = #{user_Name}")
    UserVO getUserByName(String user_Name);

    /**
     * 设置用户名
     *
     * @param userPojo 用户
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Update("update user set user_Name = #{user_Name} where user_Phone = #{user_Phone}")
    int setUserNameByPhoneInfo(UserPojo userPojo);

    /**
     * 设置密码
     *
     * @param passwdPojo 密码
     * @return passwdPojo
     * @author Jason_Jane
     */
    @Insert("insert into passwd (user_Id,passwd_Normal) values(#{pser_Id},#{passwd})")
    int setPasswd(PasswdPojo passwdPojo);

    /**
     * 根据user_Name\\user_Phone\\user_email查询用户信息
     *
     * @param user 用户名||用户手机号码||用户电子邮箱
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Select("select * from user where user_Name = #{user} or user_Phone = #{user} or user_email = #{user}")
    UserPojo getUserByNamePhoneEmail(String user);

    /**
     * 根据user_Id获取用户密码
     *
     * @param user_Id 用户账号id
     * @return passwd_Normal
     * @author Jason_Jane
     */
    @Select("select * from passwd where user_Id = #{user_Id}")
    PasswdPojo getPasswdById(String user_Id);

    /**
     * 根据user_Name查询是否重名
     *
     * @param user_Name 用户名
     * @return 用户名
     * @author Jason_Jane
     */
    @Select("select * from user where user_Name = #{user_Name}")
    UserPojo getUserByUserName(String user_Name);

    /**
     * 根据用户id获取用户名
     *
     * @param user_Id 用户id
     * @return 用户名
     * @author mnilsy
     */
    @Select("select user_Name from user where user_Id=#{user_Id}")
    String getUserName(String user_Id);

    /**
     * 根据用户名获取用户id
     *
     * @param user_Name 用户名
     * @return 用户id
     * @author mnilsy
     */
    @Select("select user_Id from user where user_Name=#{user_Name}")
    String getUser_Id(String user_Name);

    /**
     * 获取所有的可用id
     *
     * @return 所有可用的用户id
     * @author mnilsy
     */
    @Select("select user_Id from user where user_Condition=0")
    List<String> getAllUserId();


    /**
     * 修改性别
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Sex = #{user_Sex} where user_Name = #{user_Name}")
    int updateUserSex(UserVO userVO);

    /**
     * 修改密码
     *
     * @param passwdPojo 用户密码信息
     * @return passwdPojo
     * @author Jason_Jane
     */
    @Update("update passwd set passwd_Old3 = #{psswd_Old3} and passwd_Old2 = #{psswd_Old2} and passwd_Old1 = #{psswd_Old1} and passwd_Normal = #{psswd_Normal} where user_Id = #{user_Id}")
    int updatePasswd(PasswdPojo passwdPojo);


    /**
     * 增加用户
     *
     * @param userPojo 用户info
     * @return 用户基本资料
     * @author Jason_Jane
     */
    @Insert("insert into user (user_Id,user_Name,user_NickName,user_Phone) values(?,?,?,?)")
    int addUserByPhoneInfo(UserPojo userPojo);

    /**
     * 找回密码
     *
     * @param passwdPojo 用户密码信息
     * @return passwdPojo
     * @author Jason_Jane
     */
    @Update("update passwd set passwd_Old3 = #{passwd_Old3} and passwd_Old2 = #{passwd_Old2} and passwd_Old1 = #{psswd_Old1} and passwd_Normal = #{psswd_Normal} where user_Id = #{user_Id}")
    int findPasswd(PasswdPojo passwdPojo);

    /**
     * 修改手机号
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Phone = #{User_Phone} where user_Phone = #{oldPhone}")
    int updatePhone(UserVO userVO, String oldPhone);

    /**
     * 上传头像
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_HeadUrl_max = #{user_HeadUrl_max} and user_HeadUrl_min = #{user_HeadUrl_min} where user_Name = #{user_Name}")
    int updateUserHead(UserVO userVO);

    /**
     * 上传背景图
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_Background = #{user_Background} where user_Name = #{user_Name}")
    int updateBackground(UserVO userVO);

    /**
     * 绑定电子邮箱
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_email = #{user_Email} where user_Name = #{user_Name}")
    int bindUserEmail(UserVO userVO);

    /**
     * 修改电子邮箱
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_email = #{user_Email} where user_Name = #{user_Name}")
    int updateUserEmail(UserVO userVO);

    /**
     * 修改昵称
     *
     * @param userVO 用户信息
     * @return userVO
     * @author Jason_Jane
     */
    @Update("update user set user_NickName = #{user_NickName} where user_Name = #{user_Name}")
    int updateUserNickName(UserVO userVO);
}
